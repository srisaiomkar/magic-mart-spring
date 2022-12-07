package com.magicmart.admin.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.magicmart.common.entity.Role;
import com.magicmart.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void testCreateUserWithOneRole(){
        Role role = entityManager.find(Role.class, 1);
        User newUser = new User("omkar2@test.com","test","omkar2", "a");
        newUser.addRole(role);
        User savedUser = this.userRepository.save(newUser);
        assertTrue(savedUser.getId() > 0);
    }

    @Test
    public void testCreateUserWithTwoRoles(){
        Role roleOne = new Role(2);
        Role roleTwo = new Role(3);
        Role roleThree = new Role(3);
        User newUser = new User("gopal2@test.com","test","gopal2", "k");
        newUser.addRole(roleOne);
        newUser.addRole(roleTwo);
        // duplicate will not be added since we overrided equals and hashcode, refer the link in Role entity
        newUser.addRole(roleThree);
        User savedUser = this.userRepository.save(newUser);
        assertTrue(savedUser.getId() > 0);
    }

    @Test
    public void TestListAllUsers(){
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void TestGetUserById(){
        User user = userRepository.findById(1).get();
        assertNotNull(user);
    }

    @Test
    public void TestUpdateUser(){
        User user = userRepository.findById(1).get();
        user.setEmail("omkara@test.com");
        User savedUser = userRepository.save(user);
        assertEquals(savedUser.getEmail(), "omkara@test.com");
    }

    @Test
    public void TestUpdateUserRoles(){
        Role role2 = new Role(2);
        Role role4 = new Role(4);
        User user = userRepository.findById(2).get();
        user.getRoles().remove(role2);
        user.addRole(role4);
        assertTrue(user.getRoles().contains(role4));
        assertFalse(user.getRoles().contains(role2));
    }

    @Test
    public void TestDeleteUser(){
        userRepository.deleteById(6);
        assertFalse(userRepository.findById(6).isPresent());
    }
}
