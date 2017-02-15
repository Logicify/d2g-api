package com.logicify.d2g.models;

import com.logicify.d2g.domain.User;

import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

/**
 * @author knorr
 */
@MappedSuperclass
public class BaseIdentifiableAuditable extends BaseIdentifiable {

    User createdBy;

    User updatedBy;

    ZonedDateTime createdOn;

    ZonedDateTime updatedOn;

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public ZonedDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(ZonedDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}

