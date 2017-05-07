package com.logicify.d2g.models.implementations;

import com.logicify.d2g.interfaces.Auditable;
import com.logicify.d2g.interfaces.User;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @author knorr
 */
@MappedSuperclass
public abstract class BaseIdentifiableAuditable extends BaseIdentifiable implements Auditable {

    private User createdBy;

    private User updatedBy;

    private ZonedDateTime createdOn;

    private ZonedDateTime updatedOn;

    @ManyToOne(targetEntity = UserImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", updatable = false)
    @Override
    public User getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @ManyToOne(targetEntity = UserImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    @Override
    public User getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "created_on", updatable = false, nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Override
    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_on", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Override
    public ZonedDateTime getUpdatedOn() {
        return updatedOn;
    }

    @Override
    public void setUpdatedOn(ZonedDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}

