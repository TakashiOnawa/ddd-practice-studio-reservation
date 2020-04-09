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


    public RegisterMemberResponse handle(RegisterMemberRequest request) {
        var memberDetail = new MemberDetail(
                new FullName(request.getFirstName(), request.getLastName()),
                new PhoneNumber(request.getTelephoneAreaCode(), request.getTelephoneLocalNumber(), request.getTelephoneSubscriberNumber()),
                new EmailAddress(request.getEmailAddress()));

        var member = Member.newMember(
                memberDetail,
                new PlainTextPassword(request.getPassword()).encode(passwordEncoder));

        new CheckDuplicateMemberService(memberRepository).validate(member);

        return RegisterMemberResponse.builder()
                .memberId(member.getMemberId().getValue())
                .build();
    }
}
