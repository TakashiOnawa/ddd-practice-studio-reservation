package org.taonaw.reservation.domain.model.members;

import lombok.NonNull;

public class Member {

    private final MemberId memberId;
    private final String name;
    private final String phoneNumber;

    private Member(@NonNull MemberId memberId,
                   @NonNull String name,
                   @NonNull String phoneNumber) {
        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public MemberId memberId() {
        return  this.memberId;
    }

    public String name() {
        return this.name;
    }

    public String phoneNumber() {
        return this.phoneNumber;
    }
}
