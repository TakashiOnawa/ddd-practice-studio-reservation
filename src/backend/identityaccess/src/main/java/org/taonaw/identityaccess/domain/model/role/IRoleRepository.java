package org.taonaw.identityaccess.domain.model.role;

import lombok.NonNull;

public interface IRoleRepository {
    void save(@NonNull Role role);
}
