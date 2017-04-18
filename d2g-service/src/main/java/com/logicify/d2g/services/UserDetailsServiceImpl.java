package com.logicify.d2g.services;

import com.logicify.d2g.models.implementation.userimplementation.UserImpl;
import com.logicify.d2g.models.interfaces.usermodel.User;
import com.logicify.d2g.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by olegchigirin on 18.04.17.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(s);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPasswordHash(), true,
                true, true, true,
                grantedAuthorityList);
    }
}
