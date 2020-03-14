package org.taonaw.managementsite.controller.reservation.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    private String id;
    private String typeName;
    private String name;
    private int quantity;

    public List<String> getIds() {
        var ids = new ArrayList<String>();
        for (var i = 0; i < quantity; i++) {
            ids.add(id);
        }
        return ids;
    }
}
