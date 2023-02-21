package com.cohen.hackathonworld.Model;

import java.util.ArrayList;

/**
 * Project Class - Create Project for team
 * project means sprint, every sprint represent the level of the project in life levels project
 * it could be 5 or more projects(sprints) in workspace
 */
public class Project {

    public static int NUMBER_OF_PROJECT = 7000000;

    public int projectId = 0;
    public String projectTitle = "";
    public int projectTeamId = 0;
    public ManagementBoard projectManagementBoard = null;
    public PROJECT_LIFE_LEVELS projectLifeLevels = null;

    public Project(){}

    public Project withProjectId(){
        this.projectId = NUMBER_OF_PROJECT++;
        return this;
    }
    //must call after projectLifeLevels
    public Project withProjectTitle(String WORKSPACENAME){
        this.projectTitle = WORKSPACENAME + this.projectLifeLevels.name() +""+ this.projectId;
        return this;
    }
    public Project withProjectManagementBoard() {
        if (projectManagementBoard == null) {
            projectManagementBoard = new ManagementBoard();
        }
        return this;
    }
    public Project withProjectTeamId(int teamId){
        this.projectTeamId = teamId;
        return this;
    }
    public Project withProjectLifeLevel(PROJECT_LIFE_LEVELS projectLifeLevels){
        this.projectLifeLevels = projectLifeLevels;
        return this;
    }

    public void setProjectLifeLevel(PROJECT_LIFE_LEVELS projectLifeLevels) {
        this.projectLifeLevels = projectLifeLevels;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public int getProjectTeamId() {
        return projectTeamId;
    }

    public PROJECT_LIFE_LEVELS getProjectLifeLevels() {
        return projectLifeLevels;
    }

    public ManagementBoard getProjectManagementBoard() {
        return projectManagementBoard;
    }
    public void addTasksToMB(ArrayList<Integer> TasksIds) {
        this.projectManagementBoard.addIds(TasksIds);
    }
}
