package com.nattav.repository;
import org.springframework.data.repository.CrudRepository;

import com.nattav.model.UserDao;
public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
}