package com.magicmart.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.magicmart.common.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findTopUserByEmail(String email);
}
