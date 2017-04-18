package com.logicify.d2g.services;

import com.logicify.d2g.models.exceptions.D2GBaseException;
import com.logicify.d2g.models.exceptions.D2GBaseExceptionCodes;
import com.logicify.d2g.utils.PasswordStorage;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by olegchigirin on 19.04.17.
 */
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        try {
            return PasswordStorage.createHash(charSequence.toString());
        } catch (PasswordStorage.CannotPerformOperationException e) {
            return null;
        }
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        try {
            return PasswordStorage.verifyPassword(charSequence.toString(), s);
        } catch (PasswordStorage.CannotPerformOperationException |
                PasswordStorage.InvalidHashException e) {
            return false;
        }
    }
}
