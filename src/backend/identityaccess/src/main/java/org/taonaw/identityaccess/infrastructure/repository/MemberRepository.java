package org.taonaw.identityaccess.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.common.DeepCopy;
import org.taonaw.identityaccess.domain.model.member.IMemberRepository;
import org.taonaw.identityaccess.domain.model.member.Member;
import org.taonaw.identityaccess.domain.model.member.MemberId;
import org.taonaw.identityaccess.domain.model.shared.EmailAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemberRepository implements IMemberRepository {
    private static Map<MemberId, Member> values = new HashMap<>();

    @Override
    public List<Member> findAll() {
        return values.values().stream()
                .map(item -> DeepCopy.clone(item, Member.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Member> findBy(@NonNull MemberId memberId) {
        var member = values.get(memberId);
        if (member == null) {
            return Optional.empty();
        }
        return Optional.of(DeepCopy.clone(member, Member.class));
    }

    @Override
    public Optional<Member> findBy(@NonNull EmailAddress emailAddress) {
        var member = values.values().stream()
                .filter(item -> item.getDetail().getEmailAddress().equals(emailAddress))
                .findFirst();
        if (member.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(DeepCopy.clone(member.get(), Member.class));
    }

    @Override
    public void add(@NonNull Member member) {
        values.put(member.getMemberId(), member);
    }
}
