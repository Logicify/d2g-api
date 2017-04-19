package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.userpayload.UserPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.userpayload.UsersListPayload;
import com.logicify.d2g.models.exceptions.D2GBaseException;
import com.logicify.d2g.models.interfaces.usermodel.User;
import com.logicify.d2g.utils.PasswordStorage;

import java.security.Principal;
import java.util.UUID;

/**
 * @author knorr
 */
public interface UserService {

    String createPasswordHash(String password) throws PasswordStorage.CannotPerformOperationException;

    void createUser(UserCreateIncomingDto userCreateIncomingDto, Principal principal) throws D2GBaseException;

    UsersListPayload findAll();

    UserPayload findOne(UUID id) throws D2GBaseException;

    void delete(UUID id) throws D2GBaseException;

    void updateUser(UUID id, UserUpdateIncomingDto userUpdateIncomingDto, Principal principal) throws D2GBaseException;

    void updateStatus(UUID id, UserUpdateStatusIncomingDto userUpdateStatusIncomingDto) throws D2GBaseException;

    User findUserByEmail(String email);

;    }
