package com.logicify.d2g.services;

import com.logicify.d2g.domain.User;
import com.logicify.d2g.domain.UserStatus;
import com.logicify.d2g.dtos.incomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.models.UserImpl;
import com.logicify.d2g.repositories.UserRepository;
import com.logicify.d2g.utils.PasswordStorage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * @author knorr
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createPasswordHash(String password) throws PasswordStorage.CannotPerformOperationException {
        return PasswordStorage.createHash(password);
    }

    @Override
    public void createUser(UserCreateIncomingDto userCreateIncomingDto) throws PasswordStorage.CannotPerformOperationException {
        User user = modelMapper.map(userCreateIncomingDto,User.class);
        user.setPasswordHash(createPasswordHash(userCreateIncomingDto.getPassword()));
        user.setCreatedBy(user);
        user.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        user.setStatus(UserStatus.NEW);
        //userRepository.save(user); TODO: need to solve problem between interfaces and entities
    }
}

