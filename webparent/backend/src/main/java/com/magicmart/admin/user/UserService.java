package com.magicmart.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.magicmart.common.entity.Role;
import com.magicmart.common.entity.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> listAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public List<Role> listAllRoles(){
        return (List<Role>) roleRepository. findAll();
    }
    public void saveUser(User user){
        encodeUserPassword(user);
        userRepository.save(user);
    }

    private void encodeUserPassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public Boolean isEmailUnique(String email){
        return userRepository.findTopUserByEmail(email) == null;
    }
    
}
