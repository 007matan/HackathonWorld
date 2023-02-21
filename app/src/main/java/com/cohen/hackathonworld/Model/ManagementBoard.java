package com.cohen.hackathonworld.Model;

import java.util.ArrayList;

/**
 * ManagementBoard - Create ManagementBoard for project
 */
public class ManagementBoard extends IDsList{

    public static int NUMBER_OF_MANAGEMENTBOARDS = 7000000;

    public int managmentBoardId = 0;


    public ManagementBoard(){
        this.managmentBoardId = NUMBER_OF_MANAGEMENTBOARDS++;
        super.withIds();
    }
/*
    public ManagementBoard ManagmentBoardEmpty(){
        if(managmentBoardId != 0) {
            managmentBoardId = NUMBER_OF_MANAGEMENTBOARDS++;
            super.withIds();
        }
        return this;
    }

 */

    public void addTaskIds(ArrayList<Integer> taskIdsList){
        super.addIds(taskIdsList);
    }
    public void addTaskId(int taskId){
        super.addId(taskId);
    }

    public void removeTaskFromTasksList(int taskToRemoveFromTaskList){
        super.removeId(taskToRemoveFromTaskList);
    }

    public int getmanagmentBoardId() {
        return managmentBoardId;
    }


}
