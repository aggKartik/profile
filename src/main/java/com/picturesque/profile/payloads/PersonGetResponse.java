package com.picturesque.profile.payloads;

import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * This class allows for a Person's information to be queried and returned in appropriate amounts by
 * using the Builder Pattern.
 */
public class PersonGetResponse {

  private String userName;
  private Integer numFollowers;
  private Integer numFollowing;
  private String picture;
  private String name;
  private String bio;
  private Integer points;
  private List<GroupID> listOfGroups;
  private Person.PROFILE_PRIVACY privacy;
  private List<UserID> followerInvite;
  private List<GroupID> groupInvite;
  private Date dob;
  private Date dateJoined;
  private Date lastLogin;
  private String lastIP;
  private List<GroupID> groupIds;

  private PersonGetResponse() {}

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getNumFollowers() {
    return numFollowers;
  }

  public void setNumFollowers(Integer numFollowers) {
    this.numFollowers = numFollowers;
  }

  public Integer getNumFollowing() {
    return numFollowing;
  }

  public void setNumFollowing(Integer numFollowing) {
    this.numFollowing = numFollowing;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

  public List<GroupID> getListOfGroups() {
    return listOfGroups;
  }

  public void setListOfGroups(List<GroupID> listOfGroups) {
    this.listOfGroups = listOfGroups;
  }

  public Person.PROFILE_PRIVACY getPrivacy() {
    return privacy;
  }

  public void setPrivacy(Person.PROFILE_PRIVACY privacy) {
    this.privacy = privacy;
  }

  public List<UserID> getFollowerInvite() {
    return followerInvite;
  }

  public void setFollowerInvite(List<UserID> followerInvite) {
    this.followerInvite = followerInvite;
  }

  public List<GroupID> getGroupInvite() {
    return groupInvite;
  }

  public void setGroupInvite(List<GroupID> groupInvite) {
    this.groupInvite = groupInvite;
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

  public String getLastIP() {
    return lastIP;
  }

  public void setLastIP(String lastIP) {
    this.lastIP = lastIP;
  }

  public List<GroupID> getGroupIds() {
    return groupIds;
  }

  public void setGroupIds(List<GroupID> groupIds) {
    this.groupIds = groupIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonGetResponse response = (PersonGetResponse) o;
    return Objects.equals(userName, response.userName)
        && Objects.equals(numFollowers, response.numFollowers)
        && Objects.equals(numFollowing, response.numFollowing)
        && Objects.equals(picture, response.picture)
        && Objects.equals(name, response.name)
        && Objects.equals(bio, response.bio)
        && Objects.equals(points, response.points)
        && Objects.equals(listOfGroups, response.listOfGroups)
        && privacy == response.privacy
        && Objects.equals(followerInvite, response.followerInvite)
        && Objects.equals(groupInvite, response.groupInvite)
        && Objects.equals(dob, response.dob)
        && Objects.equals(dateJoined, response.dateJoined)
        && Objects.equals(lastLogin, response.lastLogin)
        && Objects.equals(lastIP, response.lastIP)
        && Objects.equals(groupIds, response.groupIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        userName,
        numFollowers,
        numFollowing,
        picture,
        name,
        bio,
        points,
        listOfGroups,
        privacy,
        followerInvite,
        groupInvite,
        dob,
        dateJoined,
        lastLogin,
        lastIP,
        groupIds);
  }

  @Override
  public String toString() {
    return "PersonGetResponse{"
        + "userName='"
        + userName
        + '\''
        + ", numFollowers="
        + numFollowers
        + ", numFollowing="
        + numFollowing
        + ", picture='"
        + picture
        + '\''
        + ", name='"
        + name
        + '\''
        + ", bio='"
        + bio
        + '\''
        + ", points="
        + points
        + ", listOfGroups="
        + listOfGroups
        + ", privacy="
        + privacy
        + ", followerInvite="
        + followerInvite
        + ", groupInvite="
        + groupInvite
        + ", dob="
        + dob
        + ", dateJoined="
        + dateJoined
        + ", lastLogin="
        + lastLogin
        + ", lastIP='"
        + lastIP
        + '\''
        + ", groupIds="
        + groupIds
        + '}';
  }

  public static class Builder {

    private String userName;
    private Integer numFollowers;
    private Integer numFollowing;
    private String picture;
    private String name;
    private String bio;
    private Integer points;
    private List<GroupID> listOfGroups;
    private Person.PROFILE_PRIVACY privacy;

    private List<UserID> followerInvite;
    private List<GroupID> groupInvite;
    private Date dob;
    private Date dateJoined;
    private Date lastLogin;
    private String lastIP;

    /**
     * At minimum include the following fields for a get response.
     *
     * @param userName
     * @param numFollowers
     * @param numFollowing
     * @param picture
     * @param name
     * @param bio
     */
    public Builder(
        String userName,
        Integer numFollowers,
        Integer numFollowing,
        String picture,
        String name,
        String bio) {
      this.userName = userName;
      this.numFollowers = numFollowers;
      this.numFollowing = numFollowing;
      this.picture = picture;
      this.name = name;
      this.bio = bio;
    }

    public PersonGetResponse build() {
      PersonGetResponse response = new PersonGetResponse();
      response.userName = this.userName;
      response.name = this.name;
      response.numFollowers = this.numFollowers;
      response.numFollowing = this.numFollowing;
      response.bio = this.bio;
      response.picture = this.picture;
      response.points = this.points;
      response.listOfGroups = this.listOfGroups;
      response.privacy = this.privacy;
      response.followerInvite = this.followerInvite;
      response.groupInvite = this.groupInvite;
      response.dob = this.dob;
      response.dateJoined = this.dateJoined;
      response.lastLogin = this.lastLogin;
      response.lastIP = this.lastIP;
      return response;
    }

    public Builder withFollowerInvite(List<UserID> followerInvite) {
      this.followerInvite = followerInvite;
      return this;
    }

    public Builder withGroupInvite(List<GroupID> groupInvite) {
      this.groupInvite = groupInvite;
      return this;
    }

    public Builder withDob(Date dob) {
      this.dob = dob;
      return this;
    }

    public Builder withDateJoined(Date dateJoined) {
      this.dateJoined = dateJoined;
      return this;
    }

    public Builder withLastLogin(Date lastLogin) {
      this.lastLogin = lastLogin;
      return this;
    }

    public Builder withLastIP(String lastIP) {
      this.lastIP = lastIP;
      return this;
    }

    public Builder withPoints(Integer points) {
      this.points = points;
      return this;
    }

    public Builder withListOfGroups(List<GroupID> logs) {
      this.listOfGroups = logs;
      return this;
    }

    public Builder withPrivacy(Person.PROFILE_PRIVACY privacy) {
      this.privacy = privacy;
      return this;
    }
  }
}
