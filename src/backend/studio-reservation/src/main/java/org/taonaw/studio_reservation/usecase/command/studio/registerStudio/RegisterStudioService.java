package org.taonaw.studio_reservation.usecase.command.studio.registerStudio;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.studio.Studio;
import org.taonaw.studio_reservation.domain.model.studio.StudioRepository;

@AllArgsConstructor
public class RegisterStudioService {
    @Autowired
    private final StudioRepository studioRepository;

    public void handle(RegisterStudioCommand command) {
        var studio = Studio.create(
                command.getStudioName(),
                command.getStartTimes(),
                command.getEquipmentMaxUsableCounts());

        studioRepository.add(studio);
    }
}
