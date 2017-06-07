package com.logicify.d2g.dtos.domain.incomingdtos.categoryincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by twilight on 11.05.17.
 */
public class CategoryCreateIncomingDto implements IncomingDto {

    @NotNull(message = "CATEGORY_NAME_IS_NULL")
    @Pattern(regexp = "^[\\w\\s-=\\[\\]\\\\;,./~!@#$%^&*()_+{}|:<>?]*$", message = "CATEGORY_NAME_IS_INVALID")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String category) {
        this.name = category;
    }
}
