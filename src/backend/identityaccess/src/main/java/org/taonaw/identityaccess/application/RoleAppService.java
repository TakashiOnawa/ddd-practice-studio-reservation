package org.taonaw.identityaccess.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.taonaw.identityaccess.application.commands.RegisterRoleRequest;
import org.taonaw.identityaccess.domain.model.roles.IRoleRepository;
import org.taonaw.identityaccess.domain.model.roles.Role;
import org.taonaw.identityaccess.domain.model.roles.RoleName;

@Service
@AllArgsConstructor
public class RoleAppService {

    @Autowired
    private final IRoleRepository roleRepository;

//    @Transactional
    public void registerRole(@NonNull RegisterRoleRequest request) {

        var role = Role.newRole(new RoleName(request.getRoleName()));

        role.permit(request.getOperationTypeList());

        roleRepository.save(role);
    }
}
