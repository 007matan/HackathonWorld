package com.cohen.hackathonworld.Manager;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cohen.hackathonworld.Exception.DateFormatException;
import com.cohen.hackathonworld.Model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    //private ArrayList<Team> teams;
    public HashMap<String, Team> theTeams = new HashMap<>();
    //private ArrayList<TeamAccompany> teamAccompanies;
    public HashMap<String, TeamAccompany> theTeamAccompanies = new HashMap<>();
    //private ArrayList<TeamMember> teamMembers;
    public HashMap<String, TeamMember> theTeamMembers = new HashMap<>();
    //private ArrayList<Task> tasks;
    public HashMap<String, Task> theTasks = new HashMap<>();
    //private ArrayList<Classroom> classrooms;
    public HashMap<String, Classroom> theClassrooms = new HashMap<>();
    //private ArrayList<Chat> chats;
    public HashMap<String, Chat> theChats = new HashMap<>();
    //private ArrayList<MyAppActivity> activities;
    public HashMap<String, MyAppActivity> theAppActivities = new HashMap<>();
    public HashMap<String, StoredFiles> theStoredFiles = new HashMap<>();
    public HashMap<String, Project> theProjects = new HashMap<>();

    public DataManager(){
        /*
        teams = new ArrayList<>();
        teamAccompanies = new ArrayList<>();
        teamMembers = new ArrayList<>();
        tasks = new ArrayList<>();
        classrooms = new ArrayList<>();
        chats = new ArrayList<>();
        activities = new ArrayList<>();

         */
    }


    //kind of function we will find here
    //add, set, get, edit, remove, find
    //kind of function we wont find here
    //log-in, log-out, ...


    //check permission
    @RequiresApi(api = Build.VERSION_CODES.O)
    public TeamAccompany AddNewTeamAccompany(String Team_Accompany_Name, int classroomId, String status, String avatar, String phoneNumber, String emailAddress, ArrayList<Integer> teamIdsList, ArrayList<Integer> storeFilesList) throws CloneNotSupportedException, DateFormatException {
        TeamAccompany teamAccompany = new TeamAccompany();
        teamAccompany
                    .withTeamAccompanyId()
                    .withTeamAccompanyName(Team_Accompany_Name)
                    .withTeamAccompanyRegisterDate()
                    .withTeamAccompanyStatus(status)
                    .withTeamAccompanyAvatar(avatar)
                    .withTeamAccompanyPhoneNumber(phoneNumber)
                    .withTeamAccompanyEmailAddress(emailAddress)
                    .withTeamAccompanyRule()
                    .withTeamAccompanyTeamsList(teamIdsList)
                    .withTeamAccompanyStoredFilesList()
                    .withTeamAccompanyMotherClassroom(classroomId);


        //teamAccompanies.add(teamAccompany);
        teamAccompany.setMyStoredFilesList(storeFilesList);
        theTeamAccompanies.put(""+teamAccompany.getUserId(), teamAccompany);

        return teamAccompany;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public TeamMember AddNewTeamMember(String Team_Member_Name, String status, String avatar, String phoneNumber, String emailAddress, String Team_Rule, int teamId){
        TEAM_RULE teamRule = TEAM_RULE.valueOf(TEAM_RULE.class, Team_Rule);

        TeamMember teamMember = null;
        try {
            teamMember = new TeamMember()
                    .withTeamMemberId()
                    .withTeamMemberName(Team_Member_Name)
                    .withTeamMemberRegisterDate()
                    .withTeamMemberStatus(status)
                    .withTeamMemberAvatar(avatar)
                    .withTeamMemberPhoneNumber(phoneNumber)
                    .withTeamMemberEmailAddress(emailAddress)
                    .withTeamMemberRule(teamRule)
                    .withTeamMemberTeam(teamId)
                    .withTeamMemberStoredFilesList();
        } catch (DateFormatException e) {
            throw new RuntimeException(e);
        }

        //teamMembers.add(teamMember);
        theTeamMembers.put("" + teamMember.getUserId(), teamMember);

        return teamMember;
    }
    //check permission creator
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Team AddNewTeam(String Team_Name, int creatorId, int classroomId, int teamAccompanyId, ArrayList<Integer> teamIdsList){
        Chat teamChat = new Chat()
                .withChatId()
                .withChatName(Team_Name)
                .withChatType(CHAT_TYPE.TEAM)
                .withChatCreatorId(creatorId);

        Team team = new Team()
                .withTeamId()
                .withTeamName(Team_Name)
                .withClassroomId(classroomId)
                .withTeamWorkSpace()
                .withTeamChatsList(teamChat.getChatId())
                .withTeamIdsList(teamIdsList)
                .withTeamTeamAccompanyId(teamAccompanyId);

        teamChat
                .withChatTeamList(team.getTeamId());

        //teams.add(team);
        //chats.add(teamChat);

        theChats.put("" + teamChat.getChatId(), teamChat);
        theTeams.put("" + team.getTeamId(), team);

        MyAppActivity myAppActivity = new MyAppActivity()
                .withActivityId()
                .withActivityType(ACTIVITY_TYPE.ADD_TEAM_TO_CHAT)
                .withActivityTime()
                .withActivityInvokedById(creatorId);
        //*************************************************************************************
        //CallBack or something that except the fact that something happened                 **
        //specific for to get the goal of realtime po-ups                                    **
        //for recommendation and Undo methods purposes its enough to hold an activities array.*
        //Think of design-pattern that get the maximum from activity
        //*************************************************************************************

        return team;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Team AddNewTeam(String Team_Name, int creatorId, int classroomId, int teamAccompanyId, ArrayList<Integer> teamIdsList, ArrayList<Integer> projectsIds){
        Chat teamChat = new Chat()
                .withChatId()
                .withChatName(Team_Name)
                .withChatType(CHAT_TYPE.TEAM)
                .withChatCreatorId(creatorId);

        Team team = new Team()
                .withTeamId()
                .withTeamName(Team_Name)
                .withClassroomId(classroomId)
                .withTeamWorkSpace()
                .withTeamChatsList(teamChat.getChatId())
                .withTeamIdsList(teamIdsList)
                .withTeamTeamAccompanyId(teamAccompanyId);

        team.addProjectsToWS(projectsIds);

        teamChat
                .withChatTeamList(team.getTeamId());

        //teams.add(team);
        //chats.add(teamChat);

        theChats.put("" + teamChat.getChatId(), teamChat);
        theTeams.put("" + team.getTeamId(), team);

        MyAppActivity myAppActivity = new MyAppActivity()
                .withActivityId()
                .withActivityType(ACTIVITY_TYPE.ADD_TEAM_TO_CHAT)
                .withActivityTime()
                .withActivityInvokedById(creatorId);
        //*************************************************************************************
        //CallBack or something that except the fact that something happened                 **
        //specific for to get the goal of realtime po-ups                                    **
        //for recommendation and Undo methods purposes its enough to hold an activities array.*
        //Think of design-pattern that get the maximum from activity
        //*************************************************************************************

        return team;
    }

    public Chat AddNewChat(String chatName,String Chat_Type,ArrayList<Integer> teamsIdsList, int creatorId){
        CHAT_TYPE chatType = CHAT_TYPE.valueOf(CHAT_TYPE.class, Chat_Type);

        Chat chat = new Chat()
                .withChatId()
                .withChatName(chatName)
                .withChatType(chatType)
                .withChatTeamList(teamsIdsList)
                .withChatCreatorId(creatorId);

        theChats.put("" + chat.getChatId(), chat);

        return chat;
    }

    public Classroom AddNewClassroom(String classroomName,double lat, double log){

        Classroom classroom = new Classroom()
                .withClassroomId()
                .withClassroomLocation(lat, log)
                .withClassroomName(classroomName);

        theClassrooms.put("" + classroom.getClassroomId(), classroom);

        return classroom;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Task AddNewTask(String taskName, String Task_Status, String description, ArrayList<Integer> teamMembersAssignIdsList, int creatorId){
        TASK_STATUS taskStatus = TASK_STATUS.valueOf(TASK_STATUS.class, Task_Status);

        Task task = null;
        try {
            task = new Task()
                    .withTaskId()
                    .withTaskName(taskName)
                    .withTaskType(taskStatus)
                    .withTaskDescription(description)
                    .withTaskTeamList(teamMembersAssignIdsList)
                    .withTaskPerformedDate()
                    .withTaskCreatorId(creatorId);
        } catch (DateFormatException e) {
            throw new RuntimeException(e);
        }

        theTasks.put("" + task.getTaskId(), task);

        return task;
    }

    public StoredFiles AddNewStoredFile(int creatorId, String File_Format){
        FILE_FORMAT fileFormat = FILE_FORMAT.valueOf(FILE_FORMAT.class, File_Format);

        StoredFiles storedFiles = new StoredFiles()
                .withStoredFilesId()
                .withCreatorId(creatorId)
                .withFileFormat(fileFormat);

        theStoredFiles.put("" + storedFiles.getStoredFilesId(), storedFiles);

        return storedFiles;
    }

    public Project AddNewProject(String projectTitle, int projectTeamId, String Project_Life_Levels){
        PROJECT_LIFE_LEVELS projectLifeLevel = PROJECT_LIFE_LEVELS.valueOf(PROJECT_LIFE_LEVELS.class, Project_Life_Levels);

        Project project = new Project()
                .withProjectId()
                .withProjectLifeLevel(projectLifeLevel)
                .withProjectTitle(projectTitle)
                .withProjectTeamId(projectTeamId)
                .withProjectManagementBoard();

        theProjects.put("" + project.getProjectId(), project);

        return project;
    }

    public Project AddNewProject(String projectTitle, int projectTeamId, String Project_Life_Levels,  ArrayList<Integer> tasksIds){
        PROJECT_LIFE_LEVELS projectLifeLevel = PROJECT_LIFE_LEVELS.valueOf(PROJECT_LIFE_LEVELS.class, Project_Life_Levels);

        Project project = new Project()
                .withProjectId()
                .withProjectLifeLevel(projectLifeLevel)
                .withProjectTitle(projectTitle)
                .withProjectTeamId(projectTeamId)
                .withProjectManagementBoard();

        project.addTasksToMB(tasksIds);

        theProjects.put("" + project.getProjectId(), project);

        return project;
    }

    /*
    public User getUser(int userId){
        for(int AccIdx = 0; AccIdx <= teamAccompanies.size(); AccIdx++){
            if(teamAccompanies.get(AccIdx).getUserId() == userId)
                return teamAccompanies.get(AccIdx);
        }
        for(int MemIdx = 0; MemIdx <= teamMembers.size(); MemIdx++){
            if(teamMembers.get(MemIdx).getUserId() == userId)
                return teamMembers.get(MemIdx);
        }
        return null;
    }

     */

}
