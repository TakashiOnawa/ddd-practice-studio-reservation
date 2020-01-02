package org.taonaw.reservation.application;

import org.springframework.transaction.annotation.Transactional;
import org.taonaw.common.date.CurrentDate;
import org.taonaw.common.domain.exception.DomainException;
import org.taonaw.common.domain.exception.DomainExceptionCodes;
import org.taonaw.reservation.application.command.ReserveStudioRequest;
import org.taonaw.reservation.domain.model.members.MemberId;
import org.taonaw.reservation.domain.model.practicetypes.IPracticeTypeRepository;
import org.taonaw.reservation.domain.model.practicetypes.PracticeTypes;
import org.taonaw.reservation.domain.model.rentalequipments.IRentalEquipmentRepository;
import org.taonaw.reservation.domain.model.reservations.*;
import org.taonaw.reservation.domain.model.studios.IStudioRepository;
import org.taonaw.reservation.domain.model.studios.StudioId;

public class ReserveStudioAppService {

    private final CurrentDate currentDate;
    private final IReservationRepository reservationRepository;
    private final IRentalEquipmentRepository rentalEquipmentRepository;
    private final IStudioRepository studioRepository;
    private final IPracticeTypeRepository practiceTypeRepository;

    public ReserveStudioAppService(CurrentDate currentDate,
                                   IReservationRepository reservationRepository,
                                   IRentalEquipmentRepository rentalEquipmentRepository,
                                   IStudioRepository studioRepository,
                                   IPracticeTypeRepository practiceTypeRepository) {
        this.currentDate = currentDate;
        this.reservationRepository = reservationRepository;
        this.rentalEquipmentRepository = rentalEquipmentRepository;
        this.studioRepository = studioRepository;
        this.practiceTypeRepository = practiceTypeRepository;
    }

    @Transactional
    public void ReserveStudio(ReserveStudioRequest request) {

        // 予約を作成する。
        var reservation = Reservation.newReservation(
                new MemberId(request.getMemberId()),
                PracticeTypes.of(request.getPracticeType()),
                new StudioId(request.getStudioId()),
                new TimePeriodOfUsage(request.getStartDateTime(), request.getEndDateTime()),
                new NumberOfUsers(request.getNumberOfUsers()));

        // TODO:予約権限があるかどうか確認する。

        /** 予約内容が正しいかを確認する **/
        var practiceType = practiceTypeRepository.findby(reservation.practiceType());

        if (practiceType.beforeReservation(reservation.timePeriodOfUsage())) {
            throw new DomainException(DomainExceptionCodes.BeforeReservation);
        }

        if (practiceType.overMaxNumberOfUsers(reservation.numberOfUsers())) {
            throw new DomainException(DomainExceptionCodes.OverMaxNumberOfUsers);
        }

        var studio = studioRepository.findBy(reservation.studioId());

        if (!studio.startTimeSatisfied(reservation.timePeriodOfUsage())) {
            throw new DomainException(DomainExceptionCodes.aaa);
        }

        /** 予約できるかを確認する **/
        var reservationService = new ReservationService(reservationRepository, rentalEquipmentRepository);

        // 利用機材が開いているかを確認する。
        if (reservationService.equipmentOutOfStock(reservation)) {
            throw new DomainException(DomainExceptionCodes.EquipmentOutOfStock);
        }

        // スタジオが既に予約されているかを確認する。
        if (reservationService.alreadyReserved(reservation)) {
            throw new DomainException(DomainExceptionCodes.ReservationAlreadyReservedDuplication);
        }

        /** 予約を登録する **/
        reservationRepository.save(reservation);
    }
}
