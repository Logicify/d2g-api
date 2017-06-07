package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdatePasswordIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.userpayload.UserPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.userpayload.UsersListPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.exceptions.D2GBaseExceptionCodes;
import com.logicify.d2g.interfaces.Role;
import com.logicify.d2g.interfaces.User;
import com.logicify.d2g.interfaces.UserStatus;
import com.logicify.d2g.models.implementations.UserImpl;
import com.logicify.d2g.repositories.UserRepository;
import com.logicify.d2g.utils.PasswordStorage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void createUser(UserCreateIncomingDto dto)
            throws D2GBaseException {
        UserImpl user = modelMapper.map(dto, UserImpl.class);
        try {
            user.setPasswordHash(createPasswordHash(dto.getPassword()));
        } catch (PasswordStorage.CannotPerformOperationException e) {
            throw new D2GBaseException(D2GBaseExceptionCodes.UNCORRECTED_PASSWORD);
        }
        user.setRole(Role.USER);
        user.setCreatedDate(ZonedDateTime.now(ZoneOffset.UTC));
        user.setStatus(UserStatus.NEW);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new D2GBaseException(D2GBaseExceptionCodes.EMAIL_ALREADY_IN_USE);
        }
    }

    @Override
    public UsersListPayload findAll() {
        return getListUserFromRepository(userRepository.findAll());
    }

    @Override
    public UserPayload findOne(UUID id) throws D2GBaseException {
        if (!userRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        UserImpl user = userRepository.findOne(id);
        return modelMapper.map(user, UserPayload.class);
    }

    @Override
    public void delete(UUID id) throws D2GBaseException {
        if (!userRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public void updateUser(UUID id, UserUpdateIncomingDto userUpdateIncomingDto, String principalName) throws D2GBaseException {

        if (!userRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        UserImpl user = userRepository.findOne(id);
        if (userUpdateIncomingDto.getFirstName() != null) {
            user.setFirstName(userUpdateIncomingDto.getFirstName());
        }
        if (userUpdateIncomingDto.getLastName() != null) {
            user.setLastName(userUpdateIncomingDto.getLastName());
        }
        if (userUpdateIncomingDto.getAvatarUrl() != null) {
            user.setAvatarUrl(userUpdateIncomingDto.getAvatarUrl());
        }
        if (userUpdateIncomingDto.getEmail() != null) {
            user.setEmail(userUpdateIncomingDto.getEmail());
        }
        user.setVersion(user.getVersion() + 1);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new D2GBaseException(D2GBaseExceptionCodes.EMAIL_ALREADY_IN_USE);
        }
    }

    @Override
    @Transactional
    public void updateStatus(UUID id, UserUpdateStatusIncomingDto incomingDto) throws D2GBaseException {
        if (!userRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        UserImpl user = userRepository.findOne(id);
        user.setVersion(user.getVersion() + 1);
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserPayload findUserDTO(String email) throws D2GBaseException {
        if (userRepository.findByEmail(email) == null) throw
                new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        UserImpl user = userRepository.findByEmail(email);
        return modelMapper.map(user, UserPayload.class);
    }

    @Override
    @Transactional
    public void updateCurrentUser(UserUpdateIncomingDto incomingDto, String email) throws D2GBaseException {
        UserImpl user = userRepository.findByEmail(email);
        if (user == null) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        if (incomingDto.getFirstName() != null) {
            user.setFirstName(incomingDto.getFirstName());
        }
        if (incomingDto.getLastName() != null) {
            user.setLastName(incomingDto.getLastName());
        }
        if (incomingDto.getAvatarUrl() != null) {
            user.setAvatarUrl(incomingDto.getAvatarUrl());
        }
        if (incomingDto.getEmail() != null) {
            user.setEmail(incomingDto.getEmail());
        }
        user.setVersion(user.getVersion() + 1);
        try {
            userRepository.save(user);
        }
        catch (Exception e){
            throw new D2GBaseException(D2GBaseExceptionCodes.EMAIL_ALREADY_IN_USE);
        }

    }

    @Override
    @Transactional
    public void updateCurrentUserPassword(UserUpdatePasswordIncomingDto incomingDto, String email) throws D2GBaseException  { //TODO:Rewrite this method
        UserImpl user = userRepository.findByEmail(email);
        try {
            if (PasswordStorage.verifyPassword(incomingDto.getOldPassword(), user.getPasswordHash())){
                if (!incomingDto.getNewPassword().equals(incomingDto.getRepeatPassword()))
                    throw new D2GBaseException(D2GBaseExceptionCodes.PASSWORD_NOT_THE_SAME);
                try {
                    user.setPasswordHash(PasswordStorage.createHash(incomingDto.getNewPassword()));
                } catch (PasswordStorage.CannotPerformOperationException e) {
                    throw new D2GBaseException(D2GBaseExceptionCodes.UNCORRECTED_PASSWORD);
                }
                user.setVersion(user.getVersion() + 1);
                userRepository.save(user);
            }
            else{
                throw new D2GBaseException(D2GBaseExceptionCodes.EXISTENT_PASSWORD_IS_INVALID);
            }
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException e) {
            throw new D2GBaseException(D2GBaseExceptionCodes.EXISTENT_PASSWORD_IS_INVALID);
        }
    }

    //This method only for UserServiceImpl. It can not be used anywhere else.
    private UsersListPayload getListUserFromRepository(Iterable<UserImpl> userIterable) {
        List<UserImpl> users = new ArrayList<>();
        userIterable.forEach(users::add);
        List<UserPayload> payloads = new ArrayList<>();
        users.forEach(user -> payloads.add(modelMapper.map(user, UserPayload.class)));
        UsersListPayload listPayload = new UsersListPayload();
        listPayload.setUsersList(payloads);
        return listPayload;
    }


}

