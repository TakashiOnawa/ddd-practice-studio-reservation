package org.taonaw.studio_reservation.usecase.command.reservation.changeReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountId;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeTypes;
import org.taonaw.studio_reservation.domain.model.reservation.ReservationId;
import org.taonaw.studio_reservation.domain.model.reservation.UsageEquipment;
import org.taonaw.studio_reservation.domain.model.reservation.UsageTime;
import org.taonaw.studio_reservation.domain.model.reservation.UserCount;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class ChangeReservationByMemberCommand {
    private ReservationId reservationId;
    private MemberAccountId memberAccountId;
    private StudioId studioId;
    private UsageTime usageTime;
    private UserCount userCount;
    private PracticeTypes practiceType;
    private List<UsageEquipment> usageEquipments;

    public List<EquipmentId> getUsageEquipmentIds() {
        if (usageEquipments == null)
            return new ArrayList<>();
        else
            return usageEquipments.stream().map(UsageEquipment::getEquipmentId).collect(Collectors.toList());
    }
}
