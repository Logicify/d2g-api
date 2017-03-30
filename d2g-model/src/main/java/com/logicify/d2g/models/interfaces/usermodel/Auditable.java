package com.logicify.d2g.models.interfaces.usermodel;

import java.time.ZonedDateTime;

/**
 * @author knorr
 */
public interface Auditable extends Identifiable {

    User getCreatedBy();

    void setCreatedBy(User creator);

    ZonedDateTime getCreatedOn();

    void setCreatedOn(ZonedDateTime createdOn);

    User getUpdatedBy();

    void setUpdatedBy(User updater);

    ZonedDateTime getUpdatedOn();

    void setUpdatedOn(ZonedDateTime updatedOn);

}
