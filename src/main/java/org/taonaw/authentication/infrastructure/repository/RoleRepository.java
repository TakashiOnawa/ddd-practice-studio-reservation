package org.taonaw.authentication.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.authentication.domain.model.roles.IRoleRepository;
import org.taonaw.authentication.domain.model.roles.Role;

@Repository
public class RoleRepository implements IRoleRepository {
    public void save(@NonNull Role role) {
        throw new IllegalCallerException();
    }
}
