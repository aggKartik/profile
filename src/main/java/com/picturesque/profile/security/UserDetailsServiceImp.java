package com.picturesque.profile.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.picturesque.profile.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.picturesque.profile.databaseModels.Person;

public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    public PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Person person = personRepository.findByUserName(username);

        if (person == null) {
            throw new UsernameNotFoundException("Invalid credentials. Username not found.");
        }
        UserDetails user = User.withUsername(person.getUserName()).password(person.getPass()).authorities("USER").build();
        return user;
    }
}
