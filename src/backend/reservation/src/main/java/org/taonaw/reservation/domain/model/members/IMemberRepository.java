package org.taonaw.reservation.domain.model.members;

import java.util.Optional;

public interface IMemberRepository {

    Optional<Member> findBy(MemberId memberId);
}
