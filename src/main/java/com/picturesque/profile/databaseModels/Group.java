package com.picturesque.profile.databaseModels;

import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;
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


    @Id
    public GroupID groupID;
    public String name;
    public List<UserID> members; // representing the user IDs of the users
    public String bio;
    public String pic;

    public Group(GroupID groupID, String name, List<UserID> members, String bio) {

        this.groupID = groupID;
        this.name = name;
        this.members = members;
        this.bio = bio;
    }

    public GroupID getGroupID() {
        return groupID;
    }

    public void setGroupID(GroupID groupID) {
        this.groupID = groupID;
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

    public List<UserID> getMembers() {
        return members;
    }

    public void setMembers(List<UserID> members) {
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