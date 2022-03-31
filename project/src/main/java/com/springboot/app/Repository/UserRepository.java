package com.springboot.app.Repository;


import com.springboot.app.Entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User getUserById(@Param("id") Long id);

    @Query("SELECT COUNT(u.id) FROM User u WHERE u.username=:username and u.id<>:user_id")
    public Long countUsersWithUsername(@Param("username") String username, Long user_id);

}
