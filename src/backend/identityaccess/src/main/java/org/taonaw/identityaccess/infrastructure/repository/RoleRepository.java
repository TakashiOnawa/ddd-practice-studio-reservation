package org.taonaw.identityaccess.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.domain.model.roles.IRoleRepository;
import org.taonaw.identityaccess.domain.model.roles.Role;

@Repository
public class RoleRepository implements IRoleRepository {

    public void save(@NonNull Role role) {
        throw new IllegalCallerException();
    }
}
