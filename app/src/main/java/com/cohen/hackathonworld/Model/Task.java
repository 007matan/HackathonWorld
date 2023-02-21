package com.cohen.hackathonworld.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cohen.hackathonworld.Exception.DateFormatException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Task {
    public static int NUMBER_OF_TASKS = 8000000;

    private int taskId = 0;
    private String taskName = "";
    private TASK_STATUS taskType = null;
    private String description = "";
    private IDsList teamMembersAssign = null;
    private MyDate performedDate = null;
    private int creatorId = 0;

    public Task(){}

    public Task withTaskId(){
        Random rand = new Random();
        int randi = rand.nextInt(100000)+2;
        this.taskId = NUMBER_OF_TASKS+randi;
        return this;
    }
    public Task withTaskName(String name){
        this.taskName = name;
        return this;
    }
    public Task withTaskType(TASK_STATUS taskType){
        this.taskType = taskType;
        return this;
    }
    public Task withTaskDescription(String description){
        this.description = description;
        return this;
    }
    public Task withTaskTeamList(ArrayList<Integer> teamMembersAssign){
        this.teamMembersAssign = new IDsList();
        this.teamMembersAssign
                .withIds(teamMembersAssign);
        return this;
    }
    public Task withTaskTeamList(int teamMemberAssign){
        this.teamMembersAssign = new IDsList();
        this.teamMembersAssign
                .withIds(teamMemberAssign);
        return this;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Task withTaskPerformedDate() throws DateFormatException {
        this.performedDate = new MyDate();
        this.performedDate.withDate(this.performedDate.CurrentDate());
        return this;
    }
    public Task withTaskCreatorId(int creatorId){
        this.creatorId = creatorId;
        return this;
    }

    public int getTaskId() {
        return taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public TASK_STATUS getTaskType() {
        return taskType;
    }
    public String getTaskDescription() {
        return description;
    }
    public IDsList getTaskTeamMembersAssign() {
        return teamMembersAssign;
    }
    public MyDate getTaskPerformedDate(){return  performedDate;}
    public int getTaskCreatorId() {
        return creatorId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTaskType(TASK_STATUS taskType) {
        this.taskType = taskType;
    }
    public void setTaskTeamMembersAssign(ArrayList<Integer> teamMembersAssign){
        if(this.teamMembersAssign == null)
            this.teamMembersAssign = new IDsList();
        this.teamMembersAssign.addIds(teamMembersAssign);
    }
    public void setTaskTeamMemberAssign(int teamMemberAssign){
        if(this.teamMembersAssign == null)
            this.teamMembersAssign = new IDsList();
        this.teamMembersAssign.addId(teamMemberAssign);
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void setTaskPerformedDate(MyDate performedDate){
        this.performedDate = performedDate;
    }

    public void addDescription(String textToAdd, String memberName){
        this.description += "\nEdit by: " + memberName + "\n" + textToAdd;
    }
}
