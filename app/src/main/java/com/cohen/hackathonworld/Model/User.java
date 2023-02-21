package com.cohen.hackathonworld.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cohen.hackathonworld.Exception.DateFormatException;

import java.util.ArrayList;

public abstract class User implements Cloneable{
    public int userId = 0;
    public String fullName = "";
    public MyDate registerDate = null;
    public String status = "";

    public String avatar = "";
    public String phoneNumber = "";
    public String emailAddress = "";
    public ArrayList <TEAM_RULE>  TeamRule = null;
    public IDsList teamList;
    public IDsList myStoredFilesList;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public MyDate getRegisterDate() {
        return registerDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setRegisterDate() throws DateFormatException {
        this.registerDate = new MyDate();
        this.registerDate.withDate(this.registerDate.CurrentDate());
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ArrayList<TEAM_RULE>  getRule() {
        return TeamRule;
    }
    public void setRule(ArrayList<TEAM_RULE> TeamRule) {
        this.TeamRule = TeamRule;
    }
    public IDsList getTeamList() {
        return teamList;
    }
    public IDsList getMyStoredFilesList() {
        return myStoredFilesList;
    }

    public void setTeamList( ArrayList<Integer> teamsList) {
        this.teamList = new IDsList();
        this.teamList.withIds(teamsList);
    }

    public void setMyStoredFilesList() {
        this.myStoredFilesList = new IDsList()
                .withIds();
    }
    public void setMyStoredFilesList(ArrayList<Integer> storedFileList) {
        this.myStoredFilesList
                .withIds(storedFileList);
    }

}
