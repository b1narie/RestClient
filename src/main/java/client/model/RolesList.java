package client.model;

import java.util.ArrayList;
import java.util.List;

public class RolesList {

    private List<Role> roles;

    public RolesList() {
        roles = new ArrayList<>();
    }

    public RolesList(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
