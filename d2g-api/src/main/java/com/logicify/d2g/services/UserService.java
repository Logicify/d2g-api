package com.logicify.d2g.services;

import com.logicify.d2g.domain.User;
import com.logicify.d2g.dtos.domain.exceptions.ControllerException;
import com.logicify.d2g.dtos.userdto.incomingdto.UserCreateIncomingDto;
import com.logicify.d2g.dtos.userdto.incomingdto.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.userdto.incomingdto.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.userdto.outcomingdto.UserOutgoingDto;
import com.logicify.d2g.dtos.userdto.outcomingdto.UsersListOutgoingDto;
import com.logicify.d2g.utils.PasswordStorage;

import java.util.UUID;

/**
 * @author knorr
 */
public interface UserService {

    String createPasswordHash(String password) throws PasswordStorage.CannotPerformOperationException;

    void createUser(UserCreateIncomingDto userCreateIncomingDto) throws ControllerException;

    UsersListOutgoingDto findAll();

    UserOutgoingDto findOne(UUID id) throws ControllerException;

    void delete(UUID id) throws ControllerException;

    void updateUser(UUID id, UserUpdateIncomingDto userUpdateIncomingDto) throws ControllerException;

    void updateStatus(UUID id, UserUpdateStatusIncomingDto userUpdateStatusIncomingDto) throws ControllerException;

    /**
     * Returns user by it's UUID
     *
     * @param id user uuif
     * @return requested {@link User}
     */
     //TODO: Implement finding user by its uuid


}
