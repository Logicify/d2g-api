package com.logicify.d2g.models.interfaces.usermodel;

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