package org.taonaw.identityaccess.query.member;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class MemberDto {
    @NonNull private String memberId;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String telephoneAreaCode;
    @NonNull private String telephoneLocalNumber;
    @NonNull private String telephoneSubscriberNumber;
    @NonNull private String emailAddress;
}
