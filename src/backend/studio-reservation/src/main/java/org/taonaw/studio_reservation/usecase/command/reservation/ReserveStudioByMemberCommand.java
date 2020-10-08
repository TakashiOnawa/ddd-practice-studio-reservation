package org.taonaw.studio_reservation.usecase.command.reservation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountId;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeTypes;
import org.taonaw.studio_reservation.domain.model.reservation.UsageTime;
import org.taonaw.studio_reservation.domain.model.reservation.UserCount;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class ReserveStudioByMemberCommand {
    private StudioId studioId;
    private UsageTime usageTime;
    private MemberAccountId memberAccountId;
    private UserCount userCount;
    private PracticeTypes practiceType;
    private List<UsageEquipmentDto> usageEquipments;
}
