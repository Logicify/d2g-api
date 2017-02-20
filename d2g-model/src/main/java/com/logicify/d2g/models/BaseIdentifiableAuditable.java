package com.logicify.d2g.models;

import com.logicify.d2g.domain.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;

/**
 * @author knorr
 */
@MappedSuperclass
public abstract class BaseIdentifiableAuditable extends BaseIdentifiable {

    private User createdBy;

    private User updatedBy;

    private ZonedDateTime createdOn;

    private ZonedDateTime updatedOn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    @Column(name = "created_by", updatable = false, nullable = false)
    public User getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    @Column(name = "updated_by")
    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "created_on", nullable = false)
    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_on")
    public ZonedDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(ZonedDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}

