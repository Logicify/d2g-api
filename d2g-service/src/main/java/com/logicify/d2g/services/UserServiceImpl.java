package com.logicify.d2g.services;

import com.logicify.d2g.domain.User;
import com.logicify.d2g.domain.UserStatus;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;
import com.logicify.d2g.dtos.userdto.domain.UserDto;
import com.logicify.d2g.dtos.domain.exceptions.ControllerException;
import com.logicify.d2g.dtos.domain.exceptions.ControllerExceptionCodes;
import com.logicify.d2g.dtos.userdto.incomingdto.UserCreateIncomingDto;
import com.logicify.d2g.dtos.userdto.incomingdto.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.userdto.incomingdto.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.userdto.outcomingdto.UserOutgoingDto;
import com.logicify.d2g.dtos.userdto.outcomingdto.UsersListOutgoingDto;
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
    public void createUser(UserCreateIncomingDto userCreateIncomingDto)
            throws ControllerException {
        UserImpl user = modelMapper.map(userCreateIncomingDto, UserImpl.class);
        if (user.getEmail().length() > User.MAX_EMAIL_LENGTH)
            throw new ControllerException(ControllerExceptionCodes.EMAIL_TO_LONG);
        if (user.getAvatarUrl().length() > User.AVATAR_URL_LENGTH)
            throw new ControllerException(ControllerExceptionCodes.AVATAR_URL_TO_LONG);
        if (user.getFirstName().length() > User.MAX_NAME_LENGTH)
            throw new ControllerException(ControllerExceptionCodes.FIRST_NAME_TO_LONG);
        if (user.getLastName().length() > User.MAX_NAME_LENGTH)
            throw new ControllerException(ControllerExceptionCodes.LAST_NAME_TO_LONG);
        try {
            user.setPasswordHash(createPasswordHash(userCreateIncomingDto.getPassword()));
        } catch (PasswordStorage.CannotPerformOperationException e) {
            throw new ControllerException(ControllerExceptionCodes.UNCORRECTED_PASSWORD);
        }
        user.setCreatedBy(user); //TODO: Realise getting creator from current session
        user.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        user.setStatus(UserStatus.NEW);
        userRepository.save(user);
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
        userOutgoingDto.setUser(userDto);
        userOutgoingDto.setService(new ServiceInformationDto());
        return userOutgoingDto;
    }

    @Override
    public void delete(UUID id) throws ControllerException {
        if (!userRepository.exists(id)) throw new ControllerException(ControllerExceptionCodes.USER_NOT_EXIST);
        userRepository.delete(id);
    }

    @Override
    public void updateUser(UUID id, UserUpdateIncomingDto userUpdateIncomingDto) throws ControllerException {

        if (!userRepository.exists(id)) throw new ControllerException(ControllerExceptionCodes.USER_NOT_EXIST);
        UserImpl user = userRepository.findOne(id);
        if (userUpdateIncomingDto.getFirstName() != null) {
            if (userUpdateIncomingDto.getFirstName().length() > User.MAX_NAME_LENGTH)
                throw new ControllerException(ControllerExceptionCodes.FIRST_NAME_TO_LONG);
            user.setFirstName(userUpdateIncomingDto.getFirstName());
        }
        if (userUpdateIncomingDto.getLastName() != null) {
            if (userUpdateIncomingDto.getLastName().length() > User.MAX_NAME_LENGTH)
                throw new ControllerException(ControllerExceptionCodes.LAST_NAME_TO_LONG);
            user.setLastName(userUpdateIncomingDto.getLastName());
        }
        if (userUpdateIncomingDto.getAvatarUrl() != null) {
            if (userUpdateIncomingDto.getAvatarUrl().length() > User.AVATAR_URL_LENGTH)
                throw new ControllerException(ControllerExceptionCodes.AVATAR_URL_TO_LONG);
            user.setAvatarUrl(userUpdateIncomingDto.getAvatarUrl());
        }
        if (userUpdateIncomingDto.getEmail() != null) {
            if (userUpdateIncomingDto.getEmail().length() > User.MAX_EMAIL_LENGTH)
                throw new ControllerException(ControllerExceptionCodes.EMAIL_TO_LONG);
            user.setEmail(userUpdateIncomingDto.getEmail());
        }
        if (userUpdateIncomingDto.getPassword() != null)
            try {
                user.setPasswordHash(createPasswordHash(userUpdateIncomingDto.getPassword()));
            } catch (PasswordStorage.CannotPerformOperationException e) {
                throw new ControllerException(ControllerExceptionCodes.UNCORRECTED_PASSWORD);
            }
        user.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        user.setUpdatedBy(user); //TODO: Realise getting updater from current session
        userRepository.save(user);
    }

    @Override
    public void updateStatus(UUID id, UserUpdateStatusIncomingDto incomingDto) throws ControllerException {
        if (!userRepository.exists(id)) throw new ControllerException(ControllerExceptionCodes.USER_NOT_EXIST);
        UserImpl user =
                userRepository.findOne(id);
        try {
            UserStatus status = UserStatus.valueOf(incomingDto.getStatus());
            user.setStatus(status);
        } catch (IllegalArgumentException e) {
            throw new ControllerException(ControllerExceptionCodes.WRONG_STATUS);
        }
        userRepository.save(user);
    }

    //This method only for UserServiceImpl. It can not be used anywhere else.
    private UsersListOutgoingDto getListUserFromRepository(Iterable<UserImpl> userIterable) {
        List<UserImpl> users = new ArrayList<>();
        userIterable.forEach(users::add);
        List<UserDto> outgoingDtos = new ArrayList<>();
        users.forEach(user -> outgoingDtos.add(modelMapper.map(user, UserDto.class)));
        UsersListOutgoingDto usersListOutgoingDto = new UsersListOutgoingDto();
        usersListOutgoingDto.setUserList(outgoingDtos);
        usersListOutgoingDto.setService(new ServiceInformationDto());
        return usersListOutgoingDto;
    }


}

