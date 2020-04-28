package org.taonaw.identityaccess.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.identityaccess.api.error.ErrorCode;
import org.taonaw.identityaccess.api.error.ErrorInformation;
import org.taonaw.identityaccess.api.error.ErrorResponse;
import org.taonaw.identityaccess.application.command.login_member.LoginMemberAppService;
import org.taonaw.identityaccess.application.command.login_member.LoginMemberCommand;
import org.taonaw.identityaccess.application.command.login_member.LoginMemberResult;
import org.taonaw.identityaccess.application.command.register_member.RegisterMemberAppService;
import org.taonaw.identityaccess.application.command.register_member.RegisterMemberCommand;
import org.taonaw.identityaccess.application.query.member.IMemberQuery;
import org.taonaw.identityaccess.application.query.member.MemberDto;
import org.taonaw.identityaccess.domain.exception.LoginMemberNotFoundException;
import org.taonaw.identityaccess.domain.exception.LoginMemberUnAuthenticatedException;
import org.taonaw.identityaccess.domain.exception.MemberDuplicatedException;

@RestController
@AllArgsConstructor
@ControllerAdvice
public class MemberController {
    @Autowired
    private final LoginMemberAppService loginMemberAppService;
    @Autowired
    private final RegisterMemberAppService registerMemberAppService;
    @Autowired
    private final IMemberQuery memberQuery;

    @PostMapping("/members/login")
    public ResponseEntity<LoginMemberResult> login(@RequestBody LoginMemberCommand request) {
        var response = loginMemberAppService.handle(request);
        return ResponseEntity.ok(response);
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

    @ExceptionHandler(LoginMemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(LoginMemberNotFoundException exception) {
        var error = ErrorInformation.builder()
                .code(ErrorCode.LoginMemberNotFound.getCode())
                .message("会員が存在しません。")
                .build();
        var response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(LoginMemberUnAuthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleException(LoginMemberUnAuthenticatedException exception) {
        var error = ErrorInformation.builder()
                .code(ErrorCode.LoginMemberUnAuthenticated.getCode())
                .message("会員のログインパスワードが一致しません。")
                .build();
        var response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(MemberDuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleException(MemberDuplicatedException exception) {
        var error = ErrorInformation.builder()
                .code(ErrorCode.MemberDuplicated.getCode())
                .message("会員が重複しています。")
                .build();
        var response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
