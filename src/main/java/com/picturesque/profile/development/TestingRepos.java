package com.picturesque.profile.development;

import com.picturesque.profile.databaseModels.Group;
import com.picturesque.profile.databaseModels.GroupMD;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.databaseModels.PersonMD;
import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;
import com.picturesque.profile.repos.GroupMDRepository;
import com.picturesque.profile.repos.GroupRepository;
import com.picturesque.profile.repos.PersonMDRepository;
import com.picturesque.profile.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TestingRepos {




    @Autowired
    public PersonRepository personRepository;

    @Autowired
    public GroupRepository groupRepository;

    @Autowired
    public PersonMDRepository personMDRepository;

    @Autowired
    public GroupMDRepository groupMDRepository;


    public void testRepositories() {
        Group exampleGroup = new Group(new GroupID("1"), "Rohil", new ArrayList<>(), "This is a bio");
        groupRepository.save(exampleGroup);
        System.out.println(groupRepository.findByGroupID(new GroupID("1")));

        Person examplePerson = new Person("Kartik", "rhjaveri", new UserID("abc"),
                "123", "password", 100, "", Person.PROFILE_PRIVACY.PUBLIC,
                new ArrayList<>(), new ArrayList<>());
        personRepository.save(examplePerson);
        System.out.println(personRepository.findByUserName("rhjaveri"));

        GroupMD exampleGroupMD = new GroupMD(new GroupID("1"), new Date());
        groupMDRepository.save(exampleGroupMD);
        System.out.println(groupMDRepository.findByGroupID(new GroupID("1")));

        PersonMD examplePersonMD = new PersonMD(new UserID("1"),new Date(),
                new Date(), new Date(), "100", new ArrayList<>());
        personMDRepository.save(examplePersonMD);
        System.out.println(personMDRepository.findByUserId(new UserID("1")));

    }







}
