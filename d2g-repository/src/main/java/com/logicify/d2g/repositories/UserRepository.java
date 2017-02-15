package com.logicify.d2g.repositories;

import com.logicify.d2g.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author knorr
 */
public interface UserRepository extends CrudRepository<User, UUID> {

    User findById(UUID id);

}
