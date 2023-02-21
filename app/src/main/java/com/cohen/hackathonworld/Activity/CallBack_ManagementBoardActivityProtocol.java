package com.cohen.hackathonworld.Activity;

import com.cohen.hackathonworld.Model.Task;

import java.util.ArrayList;

public interface CallBack_ManagementBoardActivityProtocol {
    void UpdateTasksBackLog(ArrayList<Task> tasksArr);
    void UpdateTasksToDo(ArrayList<Task> tasksArr);
    void UpdateTasksInProgress(ArrayList<Task> tasksArr);
    void UpdateTasksDone(ArrayList<Task> tasksArr);
    void UpdateTasksOther(ArrayList<Task> tasksArr);
}
