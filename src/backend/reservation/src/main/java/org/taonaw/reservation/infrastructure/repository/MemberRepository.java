package org.taonaw.reservation.infrastructure.repository;

import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.members.IMemberRepository;
import org.taonaw.reservation.domain.model.members.Member;
import org.taonaw.reservation.domain.model.members.MemberId;

import java.util.Optional;

@Repository
public class MemberRepository implements IMemberRepository {

    @Override
    public Optional<Member> findBy(MemberId memberId) {
        throw new IllegalCallerException();
    }
}
