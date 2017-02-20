package com.logicify.d2g.services;

import com.logicify.d2g.domain.User;
import com.logicify.d2g.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author knorr
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id);
        //return userRepository.findById(id);
    }
}

