package com.cohen.hackathonworld.Model;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.cohen.hackathonworld.Exception.DateFormatException;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TeamAccompany Class - Create new TeamAccompany
 * TeamAccompany Unlike TeamMember has classroom
 * more than that , TeamAccompany could be accompany for several teams - i resolve that differently
 */
public class TeamAccompany extends User {

    public static int NUMBER_OF_TEAM_ACCOMPANY = 1000000;

    public int classroomId;

    public TeamAccompany(){}

    public TeamAccompany withTeamAccompanyId(){
        setUserId(NUMBER_OF_TEAM_ACCOMPANY++);
        return this;
    }
    public TeamAccompany withTeamAccompanyName(String name){
        setFullName(name);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TeamAccompany withTeamAccompanyRegisterDate() throws DateFormatException {
        setRegisterDate();
        return this;
    }
    public TeamAccompany withTeamAccompanyStatus(String status){
        setStatus(status);
        return this;
    }
    public TeamAccompany withTeamAccompanyAvatar(String avatar){
        setAvatar(avatar);
        return this;
    }
    public TeamAccompany withTeamAccompanyPhoneNumber(String phoneNumber){
        setPhoneNumber(phoneNumber);
        return this;
    }
    public TeamAccompany withTeamAccompanyEmailAddress(String emailAddress){
        setEmailAddress(emailAddress);
        return this;
    }
    public TeamAccompany withTeamAccompanyRule(){
        setMyRule();
        return this;
    }
    public TeamAccompany withTeamAccompanyTeamsList(ArrayList<Integer> teamsList){
        setTeamList( teamsList);
        return this;
    }
    public TeamAccompany withTeamAccompanyStoredFilesList() {
        setMyStoredFilesList();
        return this;
    }
    public TeamAccompany withTeamAccompanyMotherClassroom(int classroomId){
        setClassroomId(classroomId);
        return this;
    }

    public void setMyRule() {
        ArrayList<TEAM_RULE> teamRules = new ArrayList<TEAM_RULE>(1);
        teamRules.add(TEAM_RULE.ACCOMPANY);
        setRule(teamRules);
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

}
