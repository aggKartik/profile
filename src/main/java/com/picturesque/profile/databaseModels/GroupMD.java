package com.picturesque.profile.databaseModels;

//import javax.persistence.Entity;
//import javax.persistence.Id;

import org.springframework.data.annotation.Id;

import java.util.Date;


//@Entity
public class GroupMD {


    public @Id
    int id;

    public String groupID;
    public Date createDate;

    public GroupMD(int id, String groupID, Date createDate) {
        this.id = id;
        this.groupID = groupID;
        this.createDate = createDate;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "GroupMD{" +
                "groupID='" + groupID + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}