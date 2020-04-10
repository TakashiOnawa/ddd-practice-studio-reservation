package org.taonaw.reservation.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;
import org.taonaw.reservation.domain.model.member.*;

import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MemberRepository implements IMemberRepository {
    @Autowired
    @Qualifier("identityAccessRestOptions")
    private final RestOperations identityAccessRestOptions;

    @Override
    public Optional<Member> findBy(@NonNull MemberId memberId) {
        var uri = "/members/{memberId}";
        var response = identityAccessRestOptions.getForEntity(uri, MemberDto.class, memberId.getValue());
        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return Optional.empty();
        }
        var dto = Objects.requireNonNull(response.getBody());
        return Optional.of(new Member(
                new MemberId(dto.memberId),
                new MemberName(dto.firstName, dto.lastName),
                new MemberPhoneNumber(dto.telephoneAreaCode, dto.telephoneLocalNumber, dto.telephoneSubscriberNumber)));
    }

    @Getter
    @AllArgsConstructor
    private static class MemberDto {
        @NonNull private String memberId;
        @NonNull private String firstName;
        @NonNull private String lastName;
        @NonNull private String telephoneAreaCode;
        @NonNull private String telephoneLocalNumber;
        @NonNull private String telephoneSubscriberNumber;
        @NonNull private String emailAddress;
    }
}
