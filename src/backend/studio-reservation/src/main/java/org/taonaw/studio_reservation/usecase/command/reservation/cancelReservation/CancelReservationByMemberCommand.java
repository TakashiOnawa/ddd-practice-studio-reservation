package org.taonaw.studio_reservation.usecase.command.reservation.cancelReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountId;
import org.taonaw.studio_reservation.domain.model.reservation.ReservationId;

@Getter
@Builder
@AllArgsConstructor
public class CancelReservationByMemberCommand {
    private ReservationId reservationId;
    private MemberAccountId memberAccountId;
    private long version;
}
