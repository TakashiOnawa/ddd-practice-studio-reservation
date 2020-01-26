package org.taonaw.identityaccess.domain.model.roles;

import lombok.NonNull;

public interface IRoleRepository {
    void save(@NonNull Role role);
}
