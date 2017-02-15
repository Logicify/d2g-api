package com.logicify.d2g.services;

import com.logicify.d2g.domain.User;

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
    User findById(UUID id);

}
