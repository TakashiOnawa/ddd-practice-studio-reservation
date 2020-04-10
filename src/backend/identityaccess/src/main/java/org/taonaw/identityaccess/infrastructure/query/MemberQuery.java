package org.taonaw.identityaccess.infrastructure.query;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.domain.model.member.IMemberRepository;
import org.taonaw.identityaccess.query.member.IMemberQuery;
import org.taonaw.identityaccess.query.member.MemberDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MemberQuery implements IMemberQuery {
    @Autowired
    private final IMemberRepository memberRepository;

    @Override
    public List<MemberDto> getAll() {
        var members = new ArrayList<MemberDto>();
        for (var member : memberRepository.findAll()) {
            var memberDto = MemberDto.builder()
                    .memberId(member.getMemberId().getValue())
                    .firstName(member.getDetail().getName().getFirstName())
                    .lastName(member.getDetail().getName().getLastName())
                    .telephoneAreaCode(member.getDetail().getPhoneNumber().getAreaCode())
                    .telephoneLocalNumber(member.getDetail().getPhoneNumber().getLocalNumber())
                    .telephoneSubscriberNumber(member.getDetail().getPhoneNumber().getSubscriberNumber())
                    .emailAddress(member.getDetail().getEmailAddress().getValue())
                    .build();
            members.add(memberDto);
        }
        return members;
    }

    @Override
    public Optional<MemberDto> getByMemberId(@NonNull String memberId) {
        return getAll().stream()
                .filter(item -> item.getMemberId().equals(memberId))
                .findFirst();
    }
}
