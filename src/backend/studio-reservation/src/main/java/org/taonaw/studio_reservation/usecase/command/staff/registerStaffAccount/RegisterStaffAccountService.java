package org.taonaw.studio_reservation.usecase.command.staff.registerStaffAccount;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.studio_reservation.domain.model.shared.PasswordEncoder;
import org.taonaw.studio_reservation.domain.model.staffAccount.StaffAccount;
import org.taonaw.studio_reservation.domain.model.staffAccount.StaffAccountRepository;
import org.taonaw.studio_reservation.usecase.command.exception.StaffAccountDuplicatedException;

@Service
@AllArgsConstructor
public class RegisterStaffAccountService {
    @Autowired
    private final StaffAccountRepository staffAccountRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    private void handle(@NonNull RegisterStaffAccountCommand command) {
        var staffAccount = StaffAccount.create(
                command.getName(),
                command.getLoginId(),
                command.getPlainTextPassword().createHashedPassword(passwordEncoder));

        var addResult = staffAccountRepository.add(staffAccount);

        if (addResult == StaffAccountRepository.AddResults.DUPLICATED) {
            throw new StaffAccountDuplicatedException();
        }

        if (!addResult.isSucceeded()) {
            throw new IllegalStateException("スタッフアカウントの永続化に失敗しました。");
        }
    }
}
