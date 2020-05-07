package com.picturesque.profile.databaseModels;
import com.picturesque.profile.helperModels.GroupID;
import org.springframework.data.annotation.Id;
import java.util.Date;

// Group MetaData

public class GroupMD {

    public @Id
    int id;
    public GroupID groupID;
    public Date createDate;

    public GroupMD(int id, GroupID groupID, Date createDate) {
        this.id = id;
        this.groupID = groupID;
        this.createDate = createDate;
    }

    public GroupID getGroupID() {
        return groupID;
    }

    public void setGroupID(GroupID groupID) {
        this.groupID = groupID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public java.lang.String toString() {
        return "GroupMD{" +
                "groupID='" + groupID + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}