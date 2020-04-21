package org.taonaw.identityaccess.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.identityaccess.application.command.login_member.LoginMemberAppService;
import org.taonaw.identityaccess.application.command.login_member.LoginMemberCommand;
import org.taonaw.identityaccess.application.command.login_member.LoginMemberResult;
import org.taonaw.identityaccess.application.command.register_member.RegisterMemberAppService;
import org.taonaw.identityaccess.application.command.register_member.RegisterMemberCommand;
import org.taonaw.identityaccess.domain.shared.exception.DomainException;
import org.taonaw.identityaccess.domain.shared.exception.DomainExceptionCodes;
import org.taonaw.identityaccess.application.query.member.IMemberQuery;
import org.taonaw.identityaccess.application.query.member.MemberDto;

@RestController
@AllArgsConstructor
public class MemberController {
    @Autowired
    private final LoginMemberAppService loginMemberAppService;
    @Autowired
    private final RegisterMemberAppService registerMemberAppService;
    @Autowired
    private final IMemberQuery memberQuery;

    @PostMapping("/members/login")
    public ResponseEntity<LoginMemberResult> login(@RequestBody LoginMemberCommand request) {
        try {
            var response = loginMemberAppService.handle(request);
            return ResponseEntity.ok(response);
        } catch (DomainException ex) {
            if (ex.in(DomainExceptionCodes.LoginMemberNotFound, DomainExceptionCodes.LoginMemberPasswordNotMatched)) {
                // TODO:レスポンスボディーに詳細なエラーを詰める。
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            throw ex;
        }
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("memberId") String memberId) {
        var member = memberQuery.getByMemberId(memberId);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/members")
    public ResponseEntity<Void> registerMember(
            @RequestBody RegisterMemberCommand request,
            UriComponentsBuilder uriComponentsBuilder) {
        var response = registerMemberAppService.handle(request);
        var uri = uriComponentsBuilder.path("/members/{memberId}").buildAndExpand(response.getMemberId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
