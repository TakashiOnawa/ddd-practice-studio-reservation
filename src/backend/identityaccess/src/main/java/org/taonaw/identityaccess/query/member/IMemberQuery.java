package org.taonaw.identityaccess.query.member;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IMemberQuery {
    List<MemberDto> getAll();
    Optional<MemberDto> getByMemberId(@NonNull String memberId);
}
