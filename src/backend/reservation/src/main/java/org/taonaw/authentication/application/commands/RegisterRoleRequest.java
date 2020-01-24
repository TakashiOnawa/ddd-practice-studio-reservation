package org.taonaw.authentication.application.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.authentication.domain.model.roles.OperationTypes;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class RegisterRoleRequest {
    @NonNull
    private String roleName;
    @NonNull
    private List<Integer> operationTypes;

    public List<OperationTypes> getOperationTypeList() {
        return operationTypes.stream().map(OperationTypes::of).collect(Collectors.toList());
    }
}
