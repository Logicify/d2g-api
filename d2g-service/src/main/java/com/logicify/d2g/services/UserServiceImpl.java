package com.logicify.d2g.services;

import com.logicify.d2g.domain.UserStatus;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;
import com.logicify.d2g.dtos.domain.dtos.UserDto;
import com.logicify.d2g.dtos.domain.exceptions.ControllerException;
import com.logicify.d2g.dtos.domain.exceptions.ControllerExceptionCodes;
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
    public String createPasswordHash(String password) throws ControllerException {
        try {
            return PasswordStorage.createHash(password);
        } catch (PasswordStorage.CannotPerformOperationException e) {
            throw new ControllerException(ControllerExceptionCodes.UNCORRECTED_PASSWORD);
        }
    }

    @Override
    public void createUser(UserCreateIncomingDto userCreateIncomingDto)
            throws ControllerException {
        try {
            UserImpl user = modelMapper.map(userCreateIncomingDto, UserImpl.class);
            user.setPasswordHash(createPasswordHash(userCreateIncomingDto.getPassword()));
            user.setCreatedBy(user); //TODO: Realise getting creator from current session
            user.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
            user.setStatus(UserStatus.NEW);
            userRepository.save(user);
        } catch (ControllerException e) {
            throw new ControllerException(ControllerExceptionCodes.UNCORRECTED_PASSWORD);
        }
    }

    @Override
    public UsersListOutgoingDto findAll() {
        UsersListOutgoingDto usersListOutgoingDto = getListUserFromRepository(userRepository.findAll());
        usersListOutgoingDto.setService(new ServiceInformationDto());
        return usersListOutgoingDto;
    }

    @Override
    public UserOutgoingDto findOne(UUID id) throws ControllerException {
        if (!userRepository.exists(id)) throw new ControllerException(ControllerExceptionCodes.USER_NOT_EXIST);
        UserImpl user = userRepository.findOne(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        UserOutgoingDto userOutgoingDto = new UserOutgoingDto();
        userOutgoingDto.setUserDto(userDto);
        userOutgoingDto.setService(new ServiceInformationDto());
        return userOutgoingDto;
    }

    @Override
    public void delete(UUID id) throws ControllerException {
        if (!userRepository.exists(id)) throw new ControllerException(ControllerExceptionCodes.USER_NOT_EXIST);
        userRepository.delete(id); //TODO: Throwing exception if user not exist
    }

    @Override
    public void updateUser(UUID id, UserUpdateIncomingDto userUpdateIncomingDto) throws ControllerException {
        try {
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
        } catch (ControllerException e) {
            throw new ControllerException(ControllerExceptionCodes.UNCORRECTED_PASSWORD);
        }
    }

    @Override
    public void updateStatus(UUID id, UserUpdateStatusIncomingDto userUpdateStatusIncomingDto) {
        UserImpl user = userRepository.findOne(id);
        UserStatus status = UserStatus.valueOf(userUpdateStatusIncomingDto.getStatus());
        user.setStatus(status);
        userRepository.save(user);
    }

    //This method only for UserServiceImpl. It can not be used anywhere else.
    private UsersListOutgoingDto getListUserFromRepository(Iterable<UserImpl> userIterable) {
        List<UserImpl> users = new ArrayList<>();
        userIterable.forEach(users::add);
        List<UserDto> outgoingDtos = new ArrayList<>();
        users.forEach(user -> outgoingDtos.add(modelMapper.map(user, UserDto.class)));
        UsersListOutgoingDto usersListOutgoingDto = new UsersListOutgoingDto();
        usersListOutgoingDto.setUserDtoList(outgoingDtos);
        usersListOutgoingDto.setService(new ServiceInformationDto());
        return usersListOutgoingDto;
    }


}

