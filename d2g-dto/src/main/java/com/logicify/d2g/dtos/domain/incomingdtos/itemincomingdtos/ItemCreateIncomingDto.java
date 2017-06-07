package com.logicify.d2g.dtos.domain.incomingdtos.itemincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.interfaces.Category;
import com.logicify.d2g.interfaces.Item;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by twilight on 14.05.17.
 */
public class ItemCreateIncomingDto implements IncomingDto {

    @NotNull(message = "ITEM_NAME_IS_NULL")
    @Pattern(regexp = "^[\\w\\s\\p{Punct}']*$", message = "ITEM_NAME_IS_INVALID")
    @Size(min = Item.ITEM_NAME_MIN_LENGTH, max = Item.ITEM_NAME_MAX_LENGTH, message = "ITEM_NAME_IS_INVALID")
    private String name;

    @NotNull(message = "CATEGORY_REQUIRED")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}+$", message = "WRONG_UUID_FORMAT")
    private UUID category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }
}
