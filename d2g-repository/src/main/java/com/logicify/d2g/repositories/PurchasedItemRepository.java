package com.logicify.d2g.repositories;

import com.logicify.d2g.interfaces.Item;
import com.logicify.d2g.interfaces.User;
import com.logicify.d2g.models.implementations.PurchasedItemImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by twilight on 10.05.17.
 */
public interface PurchasedItemRepository extends CrudRepository<PurchasedItemImpl, UUID> {

    List<PurchasedItemImpl> findByOwner(User user);

    List<PurchasedItemImpl> findByOwnerAndItem(User user, Item item);

    List<PurchasedItemImpl> findByOwnerAndDateOfPurchaseGreaterThan(User user, ZonedDateTime time);

    @Query(value = "SELECT sum(table.amount) FROM PurchasedItemImpl table WHERE table.owner = :owner")
    BigDecimal getOwnerExpenses(@Param("owner") User owner);

    @Query(value = "SELECT sum(table.amount) FROM PurchasedItemImpl table WHERE table.owner = :owner AND table.dateOfPurchase > :date")
    BigDecimal getOwnerExpensesForLastMonth(@Param("owner") User owner, @Param("date") ZonedDateTime date);
}
