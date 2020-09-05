package org.taonaw.studio_reservation.usecase.command.staffLogin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.studio_reservation.domain.model.shared.PasswordEncoder;
import org.taonaw.studio_reservation.domain.model.staffAccount.StaffAccountRepository;
import org.taonaw.studio_reservation.usecase.exception.StaffAccountNotFoundException;
import org.taonaw.studio_reservation.usecase.exception.StaffAccountUnAuthenticatedException;

@Service
@AllArgsConstructor
public class StaffLoginService {
    @Autowired
    private final StaffAccountRepository staffAccountRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public void handle(StaffLoginCommand command) {
        var staffAccount = staffAccountRepository.findBy(command.getLoginId())
                .orElseThrow(StaffAccountNotFoundException::new);

        if (!staffAccount.authenticate(command.getLoginId(), command.getPlainTextPassword(), passwordEncoder)) {
            throw new StaffAccountUnAuthenticatedException();
        }
    }
}
