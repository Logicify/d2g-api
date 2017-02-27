package com.logicify.d2g.services;

import com.logicify.d2g.domain.UserStatus;
import com.logicify.d2g.dtos.incomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.incomingdtos.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.incomingdtos.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.outgoingdtos.UserOutgoingDto;
import com.logicify.d2g.dtos.outgoingdtos.UsersListOutgoingDto;
import com.logicify.d2g.models.UserImpl;
import com.logicify.d2g.repositories.UserRepository;
import com.logicify.d2g.utils.PasswordStorage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        UserImpl user = modelMapper.map(userCreateIncomingDto, UserImpl.class);
        user.setPasswordHash(createPasswordHash(userCreateIncomingDto.getPassword()));
        user.setCreatedBy(user); //TODO: Realise getting creator from current session
        user.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        user.setStatus(UserStatus.NEW);
        userRepository.save(user);
    }

    @Override
    public UsersListOutgoingDto findAll() {
        UsersListOutgoingDto usersListOutgoingDto = new UsersListOutgoingDto();
        usersListOutgoingDto.setUserOutgoingDtoList(getAllUsersFromRepository());
        return usersListOutgoingDto;
    }

    @Override
    public UserOutgoingDto findOne(UUID id) {
        //if (!userRepository.exists(id)) throw new Exception(); TODO:Throwing exception if user not exist
        UserImpl user = userRepository.findOne(id);
        return modelMapper.map(user, UserOutgoingDto.class);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id); //TODO: Throwing exception if user not exist
    }

    @Override
    public void updateUser(UUID id, UserUpdateIncomingDto userUpdateIncomingDto) throws PasswordStorage.CannotPerformOperationException {
        UserImpl user = userRepository.findOne(id);
        if (userUpdateIncomingDto.getFirstName() != null)
            user.setFirstName(userUpdateIncomingDto.getFirstName());
        if (userUpdateIncomingDto.getLastName() != null)
            user.setLastName(userUpdateIncomingDto.getLastName());
        if (userUpdateIncomingDto.getAvatarUrl() != null)
            user.setAvatarUrl(userUpdateIncomingDto.getAvatarUrl());
        if (userUpdateIncomingDto.getEmail() != null)
            user.setEmail(userUpdateIncomingDto.getEmail());
        if (userUpdateIncomingDto.getPassword() != null)
            user.setPasswordHash(createPasswordHash(userUpdateIncomingDto.getPassword()));
        user.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        user.setUpdatedBy(user); //TODO: Realise getting updater from current session
        userRepository.save(user);
    }

    @Override
    public void updateStatus(UUID id, UserUpdateStatusIncomingDto userUpdateStatusIncomingDto) {
        UserImpl user = userRepository.findOne(id);
        UserStatus status = UserStatus.valueOf(userUpdateStatusIncomingDto.getStatus());
        user.setStatus(status);
        userRepository.save(user);
    }

    //This method only for UserServiceImpl. It can not use anywhere else.
    private List<UserOutgoingDto> getAllUsersFromRepository() {
        List<UserImpl> users = (List<UserImpl>) userRepository.findAll();
        List<UserOutgoingDto> outgoingDtos = new ArrayList<>();
        for (UserImpl user : users) {
            outgoingDtos.add(modelMapper.map(user, UserOutgoingDto.class));
        }
        return outgoingDtos;
    }


}

