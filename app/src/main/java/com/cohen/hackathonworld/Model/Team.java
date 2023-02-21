package com.cohen.hackathonworld.Model;

import java.util.ArrayList;

/**
 * Team Class - Create new Team
 * team contains WorkSpace that save all 'team projects'-> SPRINT MANAGEMENT BOARD
 */
public class Team {

    public static int NUMBER_OF_TEAMS = 3000000;


    public int teamId = 0;
    public String teamName = "";
    public int teamClassroomId = 0;
    public WorkSpace teamWorkSpace = null;
    public IDsList chatList;
    public IDsList teamIdsList = null;
    public  int teamAccompanyId = 0;

    public Team(){}

    public Team withTeamId(){
        this.teamId = NUMBER_OF_TEAMS++;
        return this;
    }
    public Team withTeamName(String name){
        this.teamName = name;
        return this;
    }
    public Team withClassroomId(int classroomId){
        this.teamClassroomId = classroomId;
        return this;
    }
    public Team withTeamWorkSpace() {
        if (teamWorkSpace == null) {
            teamWorkSpace = new WorkSpace(this.teamName + "WorkSpace");
        }
        return this;
    }
    public Team withTeamChatsList(int chatId){
        this.chatList = new IDsList();
        this.chatList.withIds(chatId);
        return this;
    }
    public Team withTeamIdsList(ArrayList<Integer> teamIdsList) {
        this.teamIdsList = new IDsList();
        this.teamIdsList
                .withIds(teamIdsList);
        return this;
    }
    public Team withTeamTeamAccompanyId(int teamAccompanyId){
        this.teamAccompanyId = teamAccompanyId;
        return this;
    }

    public int getTeamId() {
        return teamId;
    }
    public String getTeamName() {
        return teamName;
    }
    public int getTeamClassroomId() {
        return teamClassroomId;
    }
    public WorkSpace getTeamWorkSpace() {
        return teamWorkSpace;
    }
    public IDsList getChatList() {
        return chatList;
    }
    public IDsList getTeamIdsList() {
        return teamIdsList;
    }

    public int getTeamAccompanyId() {
        return teamAccompanyId;
    }

    //UserPremissionCheck
    public void setTeamClassroomId(int teamClassroomId) {
        this.teamClassroomId = teamClassroomId;
    }

    //UserPremissionCheck
    public void addTeamMembersIdsList(ArrayList<Integer> teamMembersIdsList) {
        this.teamIdsList.addIds(teamMembersIdsList);
    }
    public void addTeamMemberIdList(int teamMemberId) {
        this.teamIdsList.addId(teamMemberId);
    }
    public void addChatList(ArrayList<Integer> chatsList) {
        this.chatList.addIds(chatsList);
    }
    public void addProjectsToWS(ArrayList<Integer> projectsIds) {
        this.teamWorkSpace.addIds(projectsIds);
    }

    //UserPremissionCheck
    public void removeTeamMemberIdList(int teamMemberId) {
        this.teamIdsList.removeId(teamMemberId);
    }
    public void removeChatIdList(int chatMemberId) {
        this.chatList.removeId(chatMemberId);
    }

}
