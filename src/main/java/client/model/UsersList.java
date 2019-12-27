package client.model;

import java.util.ArrayList;
import java.util.List;

public class UsersList {

    private List<User> users;

    public UsersList() {
        users = new ArrayList<>();
    }

    public UsersList(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
