package org.taonaw.reservation.domain.model.member;

import lombok.NonNull;

import java.util.Optional;

public interface IMemberRepository {
    Optional<Member> findBy(@NonNull MemberId memberId);
}
