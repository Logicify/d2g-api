package com.logicify.d2g.services;

import com.logicify.d2g.models.UserImpl;
import com.logicify.d2g.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author knorr
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserImpl findById(UUID id) {
        return userRepository.findById(id);
        //return userRepository.findById(id);
    }

    @Override
    public List<UserImpl> getAllUser(){
        return (List<UserImpl>) userRepository.findAll();
    }

    @Override
    public void deleteUserByID(UUID id){
        userRepository.deleteById(id);
    }

    @Override
    public void createUser(UserImpl user){
        userRepository.save(user);
    }

    @Override
    public void saveUser(UserImpl user){
        userRepository.save(user);
    }
}

