package com.logicify.d2g.repositories;

import com.logicify.d2g.models.implementations.StoreImpl;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by twilight on 09.05.17.
 */
public interface StoreRepository extends CrudRepository<StoreImpl, UUID> {

    List<StoreImpl> findByNameContaining(String name);

}
