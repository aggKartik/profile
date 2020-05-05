package com.picturesque.profile.databaseModels;
import org.springframework.data.annotation.Id;

/*
Person

Name
User_name (specified by user)
User_id - generated
Token - login info
Password (encrypted?)

 */

//@Entity
public class Group {



    public @Id int id;
    public String name;
    public com.picturesque.profile.databaseModels.Person[] members;
    public String bio;

//    @Lob
//    @Basic(fetch = FetchType.LAZY)
    public byte[] pic;

    public Group(int id, String name, com.picturesque.profile.databaseModels.Person[] members, String bio) {
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

    public com.picturesque.profile.databaseModels.Person[] getMembers() {
        return members;
    }

    public void setMembers(com.picturesque.profile.databaseModels.Person[] members) {
        this.members = members;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", members=" + java.util.Arrays.toString(members) +
                ", pic=" + java.util.Arrays.toString(pic) +
                '}';
    }


}
