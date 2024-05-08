package ma.enset.jpaenset.service;

import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;

public interface UserService {

    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String userName);
    Role findRoleByRolename(String roneName);
    void addRoleToUser(String username, String roleName);

     User authenticate(String Username, String Password);
}
