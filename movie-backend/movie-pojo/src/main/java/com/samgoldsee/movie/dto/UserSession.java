package com.samgoldsee.movie.dto;

import com.samgoldsee.movie.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserSession implements UserDetails {

    private final Integer id;

    private final String email;

    private final String password;

    private final Boolean type;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserSession(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.type = user.getType();
        this.authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
