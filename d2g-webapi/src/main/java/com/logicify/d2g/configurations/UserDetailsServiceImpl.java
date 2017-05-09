package com.logicify.d2g.configurations;

import com.logicify.d2g.interfaces.User;
import com.logicify.d2g.interfaces.UserStatus;
import com.logicify.d2g.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        if (user.getStatus()== UserStatus.BLOCKED) throw new AccessDeniedException("Your account was blocked. Please ask support");
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(String.format("ROLE_%s", user.getRole())));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPasswordHash(), true,
                true, true, true,
                grantedAuthorityList);
    }
}
