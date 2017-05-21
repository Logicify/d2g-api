package com.logicify.d2g.repositories;

import com.logicify.d2g.models.implementations.ItemImpl;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by twilight on 10.05.17.
 */
public interface ItemRepository extends CrudRepository<ItemImpl,UUID> {

    List<ItemImpl> findByNameContaining(String name);
}
