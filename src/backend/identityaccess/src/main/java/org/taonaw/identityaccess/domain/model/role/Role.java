package org.taonaw.identityaccess.domain.model.role;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Role {
    private final RoleId roleId;
    private final List<OperationTypes> permission = new ArrayList<>();
    private RoleName roleName;

    private Role(@NonNull RoleId roleId) {
        this.roleId = roleId;
    }

    public static  Role newRole(@NonNull RoleName roleName) {
        var role = new Role(new RoleId());
        role.roleName = roleName;
        return role;
    }

    public static Role reconstruct(@NonNull RoleId roleId,
                                   @NonNull RoleName roleName,
                                   @NonNull List<OperationTypes> permission) {
        var role = new Role(roleId);
        role.roleName = roleName;
        role.permission.addAll(permission);
        return role;
    }

    public void permit(@NonNull List<OperationTypes> operationTypes) {
        operationTypes.forEach(this::permit);
    }

    public void permit(@NonNull OperationTypes operationType) {
        if (permission.stream().anyMatch(item -> item.equals(operationType))) {
            return;
        }
        permission.add(operationType);
    }

    public void prohibit(@NonNull List<OperationTypes> operationTypes) {
        operationTypes.forEach(this::prohibit);
    }

    public void prohibit(@NonNull OperationTypes operationType) {
        this.permission.remove(operationType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId.equals(role.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }
}
