package org.taonaw.identityaccess.domain.model.member;

import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.shared.EmailAddress;

import java.util.List;
import java.util.Optional;

public interface IMemberRepository {
    List<Member> findAll();
    Optional<Member> findBy(@NonNull EmailAddress emailAddress);
    void add(@NonNull Member member);
}
