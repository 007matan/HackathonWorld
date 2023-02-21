package com.cohen.hackathonworld.Activity;

import com.cohen.hackathonworld.Model.Task;

import java.util.ArrayList;

public interface CallBack_MyTasksFragmentProtocol {
    void updateTasks(ArrayList<Task> myTasks);
}
