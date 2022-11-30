package com.magicmart.admin.user;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.magicmart.common.entity.Role;

@DataJpaTest
/* BY default, spring uses in memory database, 
we want to use real database so overrididng,
spring rollbacks the transaction once test is complete*/
// @AutoConfigureTestDatabase(replace = Replace.NONE)
// rollback falls does not rollback the transaction after test is complete 
// @Rollback(false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateFirstRole(){
        Role adminRole = new Role("admin","access to manage everything");
        Role savedRole = roleRepository.save(adminRole);
        assertTrue(savedRole.getId() > 0);
    }

    @Test
    public void testCreateOtherRoles(){
        Role salesPersonRole = new Role("salesperson","access to manage product prices, " +
        "customers, shipping, orders and sales report");
        Role editorRole = new Role("editor", "access to manage categories, " +
        "brands, products, articles and menus");
        Role shipperRole = new Role("shipper","access to view products, " +
        "view orders and update order status");
        Role assistantRole = new Role("assistant","access to manage questions and reviews");

        roleRepository.saveAll(List.of(salesPersonRole,editorRole,shipperRole, assistantRole));
    }
}
