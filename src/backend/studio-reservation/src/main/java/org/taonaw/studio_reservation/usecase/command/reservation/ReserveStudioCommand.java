package org.taonaw.studio_reservation.usecase.command.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeTypes;
import org.taonaw.studio_reservation.domain.model.reservation.UsageTime;
import org.taonaw.studio_reservation.domain.model.reservation.UserCount;
import org.taonaw.studio_reservation.domain.model.reservation.UserInformation;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioCommand {
    private StudioId studioId;
    private UsageTime usageTime;
    private UserInformation userInformation;
    private UserCount userCount;
    private PracticeTypes practiceType;
    private List<UsageEquipmentDto> usageEquipments;
}
