package com.logicify.d2g.dtos.domain.incomingdtos.categoryincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

import javax.validation.constraints.Pattern;

/**
 * Created by twilight on 11.05.17.
 */
public class CategoryUpdateIncomingDto implements IncomingDto {

    @Pattern(regexp = "^[\\w\\s-=\\[\\]\\\\;,./~!@#$%^&*()_+{}|:<>?]*$", message = "CATEGORY_NAME_IS_INVALID")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String category) {
        this.name = category;
    }
}
