package org.taonaw.studio_reservation.usecase.command.reservation.changeReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountId;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeType;
import org.taonaw.studio_reservation.domain.model.reservation.ReservationId;
import org.taonaw.studio_reservation.domain.model.reservation.UsageEquipments;
import org.taonaw.studio_reservation.domain.model.reservation.UsageTime;
import org.taonaw.studio_reservation.domain.model.reservation.UserCount;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;

@Getter
@Builder
@AllArgsConstructor
public class ChangeReservationByMemberCommand {
    private ReservationId reservationId;
    private MemberAccountId memberAccountId;
    private StudioId studioId;
    private UsageTime usageTime;
    private UserCount userCount;
    private PracticeType practiceType;
    private UsageEquipments usageEquipments;
}
