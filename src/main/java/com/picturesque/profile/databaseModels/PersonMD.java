package com.picturesque.profile.databaseModels;
import org.springframework.data.annotation.Id;
//import javax.persistence.Entity;
import java.util.Date;





//@Entity
public class PersonMD {


    public @Id
    int id;
    public String userId;
    public Date dob;
    public Date dateJoined;
    public Date lastLogin;
    // how do I want to implement location??
    // https://stackoverflow.com/questions/8677707/data-type-for-storing-ip-addresses
    public long lastIP;
    public int[] groupIds;

    public PersonMD(int id, String userId, Date dob, Date dateJoined, Date lastLogin, long lastIP, int[] groupIds) {
        this.id = id;
        this.userId = userId;
        this.dob = dob;
        this.dateJoined = dateJoined;
        this.lastLogin = lastLogin;
        this.lastIP = lastIP;
        this.groupIds = groupIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public long getLastIP() {
        return lastIP;
    }

    public void setLastIP(long lastIP) {
        this.lastIP = lastIP;
    }

    public int[] getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(int[] groupIds) {
        this.groupIds = groupIds;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PersonMD{" +
                "userId='" + userId + '\'' +
                ", dob=" + dob +
                ", dateJoined=" + dateJoined +
                ", lastLogin=" + lastLogin +
                ", lastIP=" + lastIP +
                ", groupIds=" + java.util.Arrays.toString(groupIds) +
                '}';
    }
}