package com.logicify.d2g.repositories;

import com.logicify.d2g.models.implementations.CurrencyImpl;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by twilight on 10.05.17.
 */
public interface CurrencyRepository extends CrudRepository<CurrencyImpl,UUID> {

    List<CurrencyImpl> findByNameContaining(String name);
}
