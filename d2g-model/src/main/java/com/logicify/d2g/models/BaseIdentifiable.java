package com.logicify.d2g.models;

import com.logicify.d2g.domain.Identifiable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Generates a 128-bit UUID based on a custom algorithm. The value generated is represented as a string of 32 hexidecimal digits.
 *
 * @author knorr
 */
@MappedSuperclass
public abstract class BaseIdentifiable implements Identifiable {

    private UUID id;

    private long version;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Version
    @Override
    public long getVersion() {
        return version;
    }

    @Override
    public void setVersion(long version) {
        this.version = version;
    }
}
