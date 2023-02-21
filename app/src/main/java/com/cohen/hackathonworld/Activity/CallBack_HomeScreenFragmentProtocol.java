package com.cohen.hackathonworld.Activity;

import com.cohen.hackathonworld.Model.Project;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.Model.Team;
import com.cohen.hackathonworld.Model.User;

import java.util.ArrayList;

public interface CallBack_HomeScreenFragmentProtocol {
    void UpdateUser(User user);

    void UpdateTeams(ArrayList<Team> teams);

    void UpdateWorkSpace(ArrayList<Project> projects, int teamId);

    void UpdateRecentTask(ArrayList<Task> tasks, int projectId);
}
