package com.example.pelikirjasto.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pelikirjasto.domain.PeliUser;
import com.example.pelikirjasto.domain.PeliUserRepository;

@Service
public class PeliUserDetailService implements UserDetailsService {

    @Autowired
    PeliUserRepository peliuserrepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PeliUser curruser = peliuserrepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }
}
