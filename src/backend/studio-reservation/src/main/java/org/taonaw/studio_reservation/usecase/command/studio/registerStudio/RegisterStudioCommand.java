package org.taonaw.studio_reservation.usecase.command.studio.registerStudio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;
import org.taonaw.studio_reservation.domain.model.studio.StartTimes;
import org.taonaw.studio_reservation.domain.model.studio.StudioName;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class RegisterStudioCommand {
    private StudioName studioName;
    private StartTimes startTimes;
    private Set<EquipmentMaxUsableCount> equipmentMaxUsableCounts;
}
