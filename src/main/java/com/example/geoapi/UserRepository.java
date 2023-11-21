package com.example.geoapi;

import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Integer> {
    User findUserById(int id);

    User findUserByUserName(String name);
}