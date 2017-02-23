package com.logicify.d2g.repositories;

import com.logicify.d2g.models.UserImpl;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author knorr
 */
public interface UserRepository extends CrudRepository<UserImpl, UUID> {


}
