package com.logicify.d2g.domain;

import java.util.UUID;

/**
 * @author knorr
 */
public interface Identifiable {

    UUID getId();

    void setId(UUID id);

    long getVersion();
    
    void setVersion(long version);
}