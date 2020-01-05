package org.taonaw.authentication.domain.model.roles;

import lombok.NonNull;

public interface IRoleRepository {
    void save(@NonNull Role role);
}
