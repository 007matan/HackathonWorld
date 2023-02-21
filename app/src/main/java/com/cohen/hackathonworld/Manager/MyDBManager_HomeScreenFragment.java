package com.cohen.hackathonworld.Manager;

import androidx.annotation.NonNull;

import com.cohen.hackathonworld.Activity.CallBack_HomeScreenFragmentProtocol;
import com.cohen.hackathonworld.Model.IDsList;
import com.cohen.hackathonworld.Model.ManagementBoard;
import com.cohen.hackathonworld.Model.Project;
import com.cohen.hackathonworld.Model.TASK_STATUS;
import com.cohen.hackathonworld.Model.TEAM_RULE;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.Model.Team;
import com.cohen.hackathonworld.Model.TeamAccompany;
import com.cohen.hackathonworld.Model.TeamMember;
import com.cohen.hackathonworld.Model.WorkSpace;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyDBManager_HomeScreenFragment {

    private FirebaseDatabase db;
    private DatabaseReference ref;

    private CallBack_HomeScreenFragmentProtocol callBack_homeScreenFragmentProtocol;

    public MyDBManager_HomeScreenFragment(){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("dataManager");
    }

    public void setCallBack_homeScreenFragmentProtocol(CallBack_HomeScreenFragmentProtocol callBack_homeScreenFragmentProtocol) {
        this.callBack_homeScreenFragmentProtocol = callBack_homeScreenFragmentProtocol;
    }


    public void getUserByIdentifier(String identifier){
        this.ref.child("theTeamMembers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        String teamMemberPhoneNumber = i.child("phoneNumber").getValue(String.class);
                        if (teamMemberPhoneNumber.equals(identifier)){
                            //i.child("rule").getValue(Integer.class);
                            TeamMember teamMember = new TeamMember();
                            teamMember
                                    .withTeamMemberName(i.child("fullName").getValue(String.class))
                                    .withTeamMemberTeam(i.child("teamList").getValue(IDsList.class).getIds().get(0))
                                    .withTeamMemberAvatar(i.child("avatar").getValue(String.class))
                                    .withTeamMemberEmailAddress(i.child("fullName").getValue(String.class))
                                    .withTeamMemberPhoneNumber(i.child("phoneNumber").getValue(String.class))
                                    .withTeamMemberRule(i.child("rule").child("0").getValue(TEAM_RULE.class))
                                    .withTeamMemberStatus(i.child("status").getValue(String.class))
                                    .setUserId(i.child("userId").getValue(Integer.class));
                            if(callBack_homeScreenFragmentProtocol != null){
                                callBack_homeScreenFragmentProtocol.UpdateUser(teamMember);
                                getTeamsByIdentifier(teamMember.getTeamList());
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        this.ref.child("theTeamAccompanies").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        String teamAccompanyPhoneNumber = i.child("phoneNumber").getValue(String.class);
                        if (teamAccompanyPhoneNumber.equals(identifier)){
                            //TeamAccompany teamAccompany = i.getValue(TeamAccompany.class);
                            TeamAccompany teamAccompany = new TeamAccompany();
                            teamAccompany
                                    .withTeamAccompanyName(i.child("fullName").getValue(String.class))
                                    .withTeamAccompanyTeamsList(i.child("teamList").getValue(IDsList.class).getIds())
                                    .withTeamAccompanyAvatar(i.child("avatar").getValue(String.class))
                                    .withTeamAccompanyEmailAddress(i.child("fullName").getValue(String.class))
                                    .withTeamAccompanyMotherClassroom(i.child("classroomId").getValue(Integer.class))
                                    .withTeamAccompanyPhoneNumber(i.child("phoneNumber").getValue(String.class))
                                    .withTeamAccompanyRule()
                                    .withTeamAccompanyStatus(i.child("status").getValue(String.class))
                                    .setUserId(i.child("userId").getValue(Integer.class));

                            if(callBack_homeScreenFragmentProtocol != null){
                                callBack_homeScreenFragmentProtocol.UpdateUser(teamAccompany);
                                getTeamsByIdentifier(teamAccompany.getTeamList());
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getTeamsByIdentifier(IDsList teamList) {
        this.ref.child("theTeams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Team> teams = new ArrayList<>();
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        int team_Id = i.child("teamId").getValue(Integer.class);
                        for(int idx = 0; idx < teamList.getIds().size(); idx++){
                            if (team_Id == teamList.getIds().get(idx)){
                                Team team = i.getValue(Team.class);
                                teams.add(team);
                                break;
                            }
                        }
                    }
                    if(callBack_homeScreenFragmentProtocol != null){
                        callBack_homeScreenFragmentProtocol.UpdateTeams(teams);
                        getWorkSpaceByTeamId(teams.get(0));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getWorkSpaceByTeamId(Team team){
        IDsList projectsIds = new IDsList();
        projectsIds.withIds();
        WorkSpace teamWorkSpace = team.getTeamWorkSpace();
        for(int i = 0; i < teamWorkSpace.getProjectsIds().size(); i++){
            projectsIds.addId(teamWorkSpace.getProjectsIds().get(i));
        }
        this.ref.child("theProjects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Project> projects = new ArrayList<>();
                int index_projectsList = 0;
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        int project_Id = i.child("projectId").getValue(Integer.class);
                        for(int idx = 0; idx < projectsIds.getIds().size(); idx++){
                            if (project_Id == projectsIds.getIds().get(idx)){
                                Project project = i.getValue(Project.class);
                                projects.add(project);
                                break;
                            }
                        }
                    }
                    if(callBack_homeScreenFragmentProtocol != null){
                        callBack_homeScreenFragmentProtocol.UpdateWorkSpace(projects, team.getTeamId());
                        getManagementBoardByIdentifier(projects.get(projects.size()-1));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getManagementBoardByIdentifier(Project project){
        IDsList tasksIds = new IDsList();
        tasksIds.withIds();
        ManagementBoard projectManagementBoard = project.getProjectManagementBoard();
        for(int i = 0; i < projectManagementBoard.getIds().size(); i++){
            tasksIds.addId(projectManagementBoard.getIds().get(i));
        }
        this.ref.child("theTasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Task> tasks = new ArrayList<>();
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        int task_Id = i.child("taskId").getValue(Integer.class);
                        for(int idx = 0; idx < tasksIds.getIds().size(); idx++){
                            if (task_Id == tasksIds.getIds().get(idx)){
                                Task task = new Task();
                                task
                                        .withTaskCreatorId(i.child("taskCreatorId").getValue(Integer.class))
                                        .withTaskName(i.child("taskName").getValue(String.class))
                                        .withTaskDescription(i.child("taskDescription").getValue(String.class))
                                        .withTaskType(TASK_STATUS.valueOf(i.child("taskType").getValue(String.class)))
                                        .withTaskTeamList(i.child("taskTeamMembersAssign").getValue(IDsList.class).getIds())
                                        .setTaskId(i.child("taskId").getValue(Integer.class));
                                 tasks.add(task);
                                break;
                            }
                        }
                    }
                    Collections.reverse(tasks);
                    if(callBack_homeScreenFragmentProtocol != null){
                        callBack_homeScreenFragmentProtocol.UpdateRecentTask(tasks, project.getProjectId());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void preparationFor_getWorkSpaceByTeamId(String Team_Name){
        this.ref.child("theTeams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        String teamName = i.child("teamName").getValue(String.class);
                            if (teamName.compareTo(Team_Name) == 0){
                                Team team = i.getValue(Team.class);
                                getWorkSpaceByTeamId(team);
                                break;
                            }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void preparationFor_getManagementBoardByIdentifier(String Project_Name){
        this.ref.child("theProjects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        String projectName = i.child("projectTitle").getValue(String.class);
                            if (projectName.compareTo(Project_Name) == 0){
                                Project project = i.getValue(Project.class);
                                getManagementBoardByIdentifier(project);
                                break;
                            }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void UpgradeTaskStatus(String taskId){

        this.ref.child("theTasks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Task> tasks = new ArrayList<>();
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        int task_Id = i.child("taskId").getValue(Integer.class);
                        if (task_Id == Integer.valueOf(taskId)){

                            Task task = new Task();
                            task
                                    .withTaskCreatorId(i.child("taskCreatorId").getValue(Integer.class))
                                    .withTaskName(i.child("taskName").getValue(String.class))
                                    .withTaskDescription(i.child("taskDescription").getValue(String.class))
                                    .withTaskType(TASK_STATUS.valueOf(i.child("taskType").getValue(String.class)))
                                    .withTaskTeamList(i.child("taskTeamMembersAssign").getValue(IDsList.class).getIds())
                                    .setTaskId(i.child("taskId").getValue(Integer.class));
                            tasks.add(task);

                            DatabaseReference newRef = ref.child("theTasks").child(taskId);
                            Map<String, Object> updates = new HashMap<String, Object>();
                            TASK_STATUS task_status = TASK_STATUS.BACKLOG;
                            if(task.getTaskType() == TASK_STATUS.BACKLOG) {
                                task_status = TASK_STATUS.TO_DO;
                            } else if (task.getTaskType() == TASK_STATUS.TO_DO) {
                                task_status = TASK_STATUS.IN_PROGRESS;
                            } else if (task.getTaskType() == TASK_STATUS.IN_PROGRESS) {
                                task_status = TASK_STATUS.DONE;
                            } else if (task.getTaskType() == TASK_STATUS.DONE) {
                                task_status = TASK_STATUS.OTHER;
                            } else if (task.getTaskType() == TASK_STATUS.OTHER) {
                                task_status = TASK_STATUS.DONE;
                            }
                            updates.put("taskType", task_status);
                            newRef.updateChildren(updates);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
