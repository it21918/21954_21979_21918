package com.springboot.app.Service;

import com.springboot.app.Entity.User;

import java.util.List;

public interface UserServiceInterface {
    public List<User> listAll() ;

    public void save(User user) throws Exception;

    public User get(Long id) ;

    public void delete(Long id) ;
}
