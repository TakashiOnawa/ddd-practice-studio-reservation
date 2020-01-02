package org.taonaw.reservation.domain.model.members;

import lombok.NonNull;

public class Member {
    private final MemberId memberId;

    public Member(@NonNull MemberId memberId) {
        this.memberId = memberId;
    }
}
