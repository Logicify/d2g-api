package com.logicify.d2g.services;

import com.logicify.d2g.domain.User;
import com.logicify.d2g.models.UserImpl;

import java.util.List;
import java.util.UUID;

/**
 * @author knorr
 */
public interface UserService {

    /**
     * Returns user by it's UUID
     *
     * @param id user uuif
     * @return requested {@link User}
     */
    UserImpl findById(UUID id);

    List<UserImpl> getAllUser();

    void deleteUserByID(UUID id);

    void createUser(UserImpl user);

    void saveUser(UserImpl user);
}
