package org.taonaw.reservation.application.reserve_studio;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.reservation.common.CurrentDate;
import org.taonaw.reservation.domain.model.equipment.IEquipmentRepository;
import org.taonaw.reservation.domain.model.reservation.*;
import org.taonaw.reservation.domain.model.reservationsetting.IReservationSettingRepository;
import org.taonaw.reservation.domain.model.studio.StudioId;

//import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReserveStudioAppService {
    @Autowired
    private final IReservationRepository reservationRepository;
    @Autowired
    private final IEquipmentRepository equipmentRepository;
    @Autowired
    private final IReservationSettingRepository reservationSettingRepository;
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
        new ReservationValidator(reservationSettingRepository, currentDate).validate(reservation);
        new CheckDuplicateReservationService(reservationRepository).validate(reservation);
        new CheckEquipmentsOutOfStocksService(reservationRepository, equipmentRepository).validate(reservation);
    }
}
