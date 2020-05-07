package com.picturesque.profile.helperModels;

public class GroupID {
    public String groupID;

    public GroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    @Override
    public String toString() {
        return "GroupID{" +
                "groupID='" + groupID + '\'' +
                '}';
    }
}
