package org.taonaw.identityaccess.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.identityaccess.api.error.ErrorCode;
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
@RequestMapping("/members")
@AllArgsConstructor
@ControllerAdvice
public class MemberController {
    @Autowired
    private final LoginMemberAppService loginMemberAppService;
    @Autowired
    private final RegisterMemberAppService registerMemberAppService;
    @Autowired
    private final IMemberQuery memberQuery;

    @PostMapping("/login")
    public ResponseEntity<LoginMemberResult> login(@RequestBody LoginMemberCommand request) {
        var response = loginMemberAppService.handle(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("memberId") String memberId) {
        var member = memberQuery.getByMemberId(memberId);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> registerMember(
            @RequestBody RegisterMemberCommand request,
            UriComponentsBuilder uriComponentsBuilder) {
        var response = registerMemberAppService.handle(request);
        var uri = uriComponentsBuilder.path("/members/{memberId}").buildAndExpand(response.getMemberId()).toUri();
        return ResponseEntity.created(uri).build();
    }



    @ExceptionHandler(LoginMemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(LoginMemberNotFoundException e) {
        var response = new ErrorResponse(ErrorCode.LoginMemberNotFound);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(LoginMemberUnAuthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleException(LoginMemberUnAuthenticatedException e) {
        var response = new ErrorResponse(ErrorCode.LoginMemberUnAuthenticated);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(MemberDuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleException(MemberDuplicatedException e) {
        var response = new ErrorResponse(ErrorCode.MemberDuplicated);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
