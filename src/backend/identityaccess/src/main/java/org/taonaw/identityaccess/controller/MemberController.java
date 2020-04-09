package org.taonaw.identityaccess.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.identityaccess.application.login_member.LoginMemberAppService;
import org.taonaw.identityaccess.application.login_member.LoginMemberRequest;
import org.taonaw.identityaccess.application.login_member.LoginMemberResponse;
import org.taonaw.identityaccess.application.register_member.RegisterMemberAppService;
import org.taonaw.identityaccess.application.register_member.RegisterMemberRequest;
import org.taonaw.identityaccess.application.register_member.RegisterMemberResponse;
import org.taonaw.identityaccess.domain.shared.exception.DomainException;
import org.taonaw.identityaccess.domain.shared.exception.DomainExceptionCodes;

@RestController
@AllArgsConstructor
public class MemberController {
    @Autowired
    private final LoginMemberAppService loginMemberAppService;
    @Autowired
    private final RegisterMemberAppService registerMemberAppService;

    @PostMapping("/members/login")
    public ResponseEntity<LoginMemberResponse> login(@RequestBody LoginMemberRequest request) {
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

    @PostMapping("/members")
    public ResponseEntity<RegisterMemberResponse> registerMember(@RequestBody RegisterMemberRequest request,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        var response = registerMemberAppService.handle(request);
        var uri = uriComponentsBuilder.path("/members/{memberId}").buildAndExpand(response.getMemberId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
