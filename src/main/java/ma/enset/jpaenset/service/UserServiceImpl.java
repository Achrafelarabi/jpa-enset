package ma.enset.jpaenset.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.repositories.RoleRepository;
import ma.enset.jpaenset.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String userName) {

        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRolename(String roneName) {
        return roleRepository.findByRoleName(roneName);
    }



    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = findUserByUsername(username);
        Role role = findRoleByRolename(roleName);
        if (user.getRoles()!=null){
            user.getRoles().add(role);
            role.getUsers().add(user);

        }
        // userRepository.save(user);

    }

    @Override
    public User authenticate(String Username, String Password) {
        User user = userRepository.findByUsername(Username);
        if (user==null)throw new RuntimeException("Bad Credentials");
        if (user.getPassword().equals(Password)) {
            return user;
        }
        throw new RuntimeException("Bad Credentials");
    }
}
