package client.controller;

import client.model.Role;
import client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClientController {

    private final String url = "http://localhost:8080/rest";
    private PasswordEncoder passwordEncoder;
    private RestTemplate restTemplate;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/users")
    public User[] getAllUsers() {
//        UsersList usersList = restTemplate.getForObject(url + "/users", UsersList.class);
//        List<User> users = usersList.getUsers();
        ResponseEntity<User[]> response = restTemplate.getForEntity(url + "/users", User[].class);
        return response.getBody();
    }

    @GetMapping("/roles")
    public Role[] getRoles() {
//        RolesList rolesList = restTemplate.getForObject(url + "/roles", RolesList.class);
//        List<Role> roles = rolesList.getRoles();
        ResponseEntity<Role[]> response = restTemplate.getForEntity(url + "/roles", Role[].class);
        return response.getBody();
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        restTemplate.postForObject(url + "/user", user, User.class);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(url + "/user/" + id, User.class);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        restTemplate.put(url + "/user/" + id, user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        restTemplate.delete(url + "/user/" + id);
        return ResponseEntity.ok().build();
    }
}
