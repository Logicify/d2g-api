package com.logicify.d2g.services;

import com.logicify.d2g.domain.User;
import com.logicify.d2g.dtos.incomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.utils.PasswordStorage;

/**
 * @author knorr
 */
public interface UserService {

    String createPasswordHash(String password) throws PasswordStorage.CannotPerformOperationException;

    void createUser(UserCreateIncomingDto userCreateIncomingDto) throws PasswordStorage.CannotPerformOperationException;

    /**
     * Returns user by it's UUID
     *
     * @param id user uuif
     * @return requested {@link User}
     */
     //TODO: Implement finding user by its uuid


}
