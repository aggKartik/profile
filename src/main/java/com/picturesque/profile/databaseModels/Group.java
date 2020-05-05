package com.picturesque.profile.databaseModels;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.List;

/*
Group

name (specified by users)
members (members in the group)
bio (the group bio)
pic (group profile pic)

 */

public class Group {

    public @Id int id;
    public String name;
    public List<Person> members;
    public String bio;
    public byte[] pic;

    public Group(int id, String name, List<Person> members, String bio) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", members=" + members +
                ", bio='" + bio + '\'' +
                ", pic=" + Arrays.toString(pic) +
                '}';
    }
}
