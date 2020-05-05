package com.picturesque.profile.databaseModels;
import org.springframework.data.annotation.Id;

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
    public List<String> members; // representing the user IDs of the users
    public String bio;
    public String pic;

    public Group(int id, String name, List<String> members, String bio) {
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

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", members=" + members +
                ", bio='" + bio + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
