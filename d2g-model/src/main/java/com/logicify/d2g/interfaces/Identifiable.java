package com.logicify.d2g.interfaces;

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