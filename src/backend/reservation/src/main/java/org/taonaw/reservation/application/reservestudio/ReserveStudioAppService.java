package org.taonaw.reservation.application.reservestudio;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.equipment.IEquipmentRepository;
import org.taonaw.reservation.domain.model.practice.IPracticeRepository;
import org.taonaw.reservation.domain.model.practice.PracticeType;
import org.taonaw.reservation.domain.model.reservation.*;
import org.taonaw.reservation.domain.model.studio.IStudioRepository;
import org.taonaw.reservation.domain.model.studio.StudioId;
import org.taonaw.reservation.domain.model.tenant.ITenantRepository;

//import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReserveStudioAppService {
    @Autowired
    private final IReservationRepository reservationRepository;
    @Autowired
    private final IStudioRepository studioRepository;
    @Autowired
    private final IEquipmentRepository equipmentRepository;
    @Autowired
    private final ITenantRepository tenantRepository;
    @Autowired
    private final IPracticeRepository practiceRepository;
    @Autowired
    private final CurrentDate currentDate;

    //    @Transactional
    public ReserveStudioResponse handle(ReserveStudioRequest request) {
        reservationRepository.lock();

        var reservation = Reservation.newReservation(
                new StudioId(request.getStudioId()),
                new UseTime(request.getStartDateTime(), request.getHourQuantity()),
                new UserInformation(request.getUserName(), request.getUserPhoneNumber()),
                new NumberOfUsers(request.getNumberOfUsers()),
                PracticeType.from(request.getPracticeType()),
                UseEquipments.of(request.getEquipmentIds()));

        validate(reservation);

        reservationRepository.add(reservation);

        return ReserveStudioResponse.builder()
                .reservationId(reservation.getReservationId().getValue())
                .build();
    }

    private void validate(Reservation reservation) {
        var validator = new ReservationValidator(studioRepository, practiceRepository, tenantRepository, currentDate);
        validator.validate(reservation);

        var duplicateReservationService = new CheckDuplicateReservationService(reservationRepository);
        duplicateReservationService.validate(reservation);

        var equipmentsOutOfStocksService = new CheckEquipmentsOutOfStocksService(reservationRepository, equipmentRepository);
        equipmentsOutOfStocksService.validate(reservation);
    }
}
