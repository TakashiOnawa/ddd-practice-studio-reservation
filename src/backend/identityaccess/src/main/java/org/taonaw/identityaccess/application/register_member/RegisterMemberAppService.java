package org.taonaw.identityaccess.application.register_member;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.domain.model.member.CheckDuplicateMemberService;
import org.taonaw.identityaccess.domain.model.member.IMemberRepository;
import org.taonaw.identityaccess.domain.model.member.Member;
import org.taonaw.identityaccess.domain.model.member.MemberDetail;
import org.taonaw.identityaccess.domain.model.shared.*;

@Service
@AllArgsConstructor
public class RegisterMemberAppService {
    @Autowired
    private final IMemberRepository memberRepository;
    @Autowired
    private final IPasswordEncoder passwordEncoder;

    public RegisterMemberResult handle(RegisterMemberCommand command) {
        memberRepository.lock();

        var memberDetail = new MemberDetail(
                new FullName(command.getFirstName(), command.getLastName()),
                new PhoneNumber(command.getTelephoneAreaCode(), command.getTelephoneLocalNumber(), command.getTelephoneSubscriberNumber()),
                new EmailAddress(command.getEmailAddress()));

        var member = Member.newMember(
                memberDetail,
                new PlainTextPassword(command.getPassword()).encode(passwordEncoder));

        new CheckDuplicateMemberService(memberRepository).validate(member);

        memberRepository.add(member);

        return RegisterMemberResult.builder()
                .memberId(member.getMemberId().getValue())
                .build();
    }
}
