package com.logicify.d2g.repositories;

import com.logicify.d2g.models.implementations.UserImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author knorr
 */
public interface UserRepository extends CrudRepository<UserImpl, UUID> {

    UserImpl findByEmail(String email);

}
