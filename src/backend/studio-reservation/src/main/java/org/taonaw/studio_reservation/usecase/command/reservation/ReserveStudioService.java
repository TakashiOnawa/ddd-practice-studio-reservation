package org.taonaw.studio_reservation.usecase.command.reservation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentRepository;
import org.taonaw.studio_reservation.domain.model.reservation.ReservationRepository;
import org.taonaw.studio_reservation.domain.model.studio.StudioRepository;
import org.taonaw.studio_reservation.usecase.command.studio.exception.StudioNotFoundException;

@AllArgsConstructor
public class ReserveStudioService {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final StudioRepository studioRepository;
    @Autowired
    private final EquipmentRepository equipmentRepository;

    private void handle(ReserveStudioCommand command) {
        var studio = studioRepository.findBy(command.getStudioId())
                .orElseThrow(StudioNotFoundException::new);

    }

    private void handle(ReserveStudioByMemberCommand command) {
        
    }
}
