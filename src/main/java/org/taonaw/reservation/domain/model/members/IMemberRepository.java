package org.taonaw.reservation.domain.model.members;

public interface IMemberRepository {
    Member findBy(MemberId memberId);
}
