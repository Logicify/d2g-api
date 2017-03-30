package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.userpayload.UserPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.userpayload.UsersListPayload;
import com.logicify.d2g.models.exceptions.D2GBaseException;
import com.logicify.d2g.models.exceptions.D2GBaseExceptionCodes;
import com.logicify.d2g.models.implementation.userimplementation.UserImpl;
import com.logicify.d2g.models.interfaces.usermodel.UserStatus;
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
            throws D2GBaseException {
        UserImpl user = modelMapper.map(userCreateIncomingDto, UserImpl.class);
        try {
            user.setPasswordHash(createPasswordHash(userCreateIncomingDto.getPassword()));
        } catch (PasswordStorage.CannotPerformOperationException e) {
            throw new D2GBaseException(D2GBaseExceptionCodes.UNCORRECTED_PASSWORD);
        }
        user.setCreatedBy(user); //TODO: Realise getting creator from current session
        user.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        user.setStatus(UserStatus.NEW);
        userRepository.save(user);
    }

    @Override
    public UsersListPayload findAll() {
        UsersListPayload payload = getListUserFromRepository(userRepository.findAll());
        return payload;
    }

    @Override
    public UserPayload findOne(UUID id) throws D2GBaseException {
        if (!userRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        UserImpl user = userRepository.findOne(id);
        UserPayload userPayload = modelMapper.map(user, UserPayload.class);
        return userPayload;
    }

    @Override
    public void delete(UUID id) throws D2GBaseException {
        if (!userRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        userRepository.delete(id);
    }

    @Override
    public void updateUser(UUID id, UserUpdateIncomingDto userUpdateIncomingDto) throws D2GBaseException {

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
        if (userUpdateIncomingDto.getPassword() != null)
            try {
                user.setPasswordHash(createPasswordHash(userUpdateIncomingDto.getPassword()));
            } catch (PasswordStorage.CannotPerformOperationException e) {
                throw new D2GBaseException(D2GBaseExceptionCodes.UNCORRECTED_PASSWORD);
            }
        user.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        user.setUpdatedBy(user); //TODO: Realise getting updater from current session
        userRepository.save(user);
    }

    @Override
    public void updateStatus(UUID id, UserUpdateStatusIncomingDto incomingDto) throws D2GBaseException {
        if (!userRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        UserImpl user =
                userRepository.findOne(id);
        try {
            UserStatus status = UserStatus.valueOf(incomingDto.getStatus());
            user.setStatus(status);
        } catch (IllegalArgumentException e) {
            throw new D2GBaseException(D2GBaseExceptionCodes.WRONG_STATUS);
        }
        userRepository.save(user);
    }

    //This method only for UserServiceImpl. It can not be used anywhere else.
    private UsersListPayload getListUserFromRepository(Iterable<UserImpl> userIterable) {
        List<UserImpl> users = new ArrayList<>();
        userIterable.forEach(users::add);
        List<UserPayload> outgoingDtos = new ArrayList<>();
        users.forEach(user -> outgoingDtos.add(modelMapper.map(user, UserPayload.class)));
        UsersListPayload listPayload = new UsersListPayload();
        listPayload.setUsersList(outgoingDtos);
        return listPayload;
    }


}

