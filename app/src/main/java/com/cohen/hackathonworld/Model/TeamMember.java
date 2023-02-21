package com.cohen.hackathonworld.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cohen.hackathonworld.Exception.DateFormatException;

import java.util.ArrayList;

/**
 * Look at TeamAccompany
 */
public class TeamMember extends User {
    public static int NUMBER_OF_TEAM_MEMBER = 2000000;

    public TeamMember() {
    }
    public TeamMember withTeamMemberId() {
        setUserId(NUMBER_OF_TEAM_MEMBER++);
        return this;
    }
    public TeamMember withTeamMemberName(String name) {
        setFullName(name);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TeamMember withTeamMemberRegisterDate() throws DateFormatException {
        setRegisterDate();
        return this;
    }
    public TeamMember withTeamMemberStatus(String status) {
        setStatus(status);
        return this;
    }
    public TeamMember withTeamMemberAvatar(String avatar) {
        setAvatar(avatar);
        return this;
    }
    public TeamMember withTeamMemberPhoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }
    public TeamMember withTeamMemberEmailAddress(String emailAddress){
        setEmailAddress(emailAddress);
        return this;
    }
    public TeamMember withTeamMemberRule(TEAM_RULE TeamRule) {
        ArrayList<TEAM_RULE> teamRules = new ArrayList<TEAM_RULE>();
        if (TeamRule != TEAM_RULE.ACCOMPANY)
            teamRules.add(TeamRule);
        else
            teamRules.add(TEAM_RULE.OTHER);
        setRule(teamRules);
        return this;
    }
    public TeamMember withTeamMemberTeam(int teamId) {
        setTeamId(teamId);
        return this;
    }
    public TeamMember withTeamMemberStoredFilesList() {
        setMyStoredFilesList();
        return this;
    }

    private void setTeamId(int teamId) {
        ArrayList<Integer> teamIdArr = new ArrayList<>();
        teamIdArr.add(teamId);
        setTeamList( teamIdArr);
    }
/*
    public void addTeamMemberRule(TEAM_RULE TeamRule){
        if (TeamRule != TEAM_RULE.ACCOMPANY)
            this.getRule().add(TeamRule);
    }

 */

}