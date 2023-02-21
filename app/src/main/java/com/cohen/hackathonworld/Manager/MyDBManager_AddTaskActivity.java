package com.cohen.hackathonworld.Manager;

import androidx.annotation.NonNull;

import com.cohen.hackathonworld.Activity.CallBack_AddTaskActivityProtocol;
import com.cohen.hackathonworld.Model.IDsList;
import com.cohen.hackathonworld.Model.Project;
import com.cohen.hackathonworld.Model.TEAM_RULE;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.Model.Team;
import com.cohen.hackathonworld.Model.TeamAccompany;
import com.cohen.hackathonworld.Model.TeamMember;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDBManager_AddTaskActivity {
    private FirebaseDatabase db;
    private DatabaseReference ref;

    private CallBack_AddTaskActivityProtocol callBack_addTaskActivityProtocol;

    public MyDBManager_AddTaskActivity(){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("dataManager");
    }

    public void setCallBack_AddTaskActivityProtocol(CallBack_AddTaskActivityProtocol callBack_addTaskActivityProtocol) {
        this.callBack_addTaskActivityProtocol = callBack_addTaskActivityProtocol;
    }


    public void getUserByIdentifier(String identifier){
        this.ref.child("theTeamMembers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        String teamMemberPhoneNumber = i.child("phoneNumber").getValue(String.class);
                        if (teamMemberPhoneNumber.equals(identifier)){
                            //i.child("rule").child("0").getValue(Integer.class);
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
                                getTeamByIdentifier(teamMember.getTeamList().getIds().get(0));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //team accompany is not suppose to add task, the logic inside is iilegal, for aducational puposes only!!!
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
                                getTeamByIdentifier(teamAccompany.getTeamList().getIds().get(0));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getTeamByIdentifier(int teamId) {
        this.ref.child("theTeams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Integer> teamMembersIds = new ArrayList<>();
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        int team_Id = i.child("teamId").getValue(Integer.class);
                            if (team_Id == teamId){
                                Team team = i.getValue(Team.class);
                                teamMembersIds= i.child("teamIdsList").getValue(IDsList.class).getIds();
                                getTeamMembersByIdentifier(teamMembersIds);
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
    public void getTeamMembersByIdentifier(ArrayList<Integer>teamMembersIds){
        this.ref.child("theTeamMembers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<TeamMember>teamMembers = new ArrayList<>();
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        int userId = i.child("userId").getValue(Integer.class);
                        for(int idx = 0; idx < teamMembersIds.size(); idx++){
                            if (teamMembersIds.get(idx) == userId){
                                TeamMember teamMember = new TeamMember();
                                teamMember
                                        .withTeamMemberName(i.child("fullName").getValue(String.class))
                                        .withTeamMemberTeam(i.child("teamList").getValue(IDsList.class).getIds().get(0))
                                        .withTeamMemberAvatar(i.child("avatar").getValue(String.class))
                                        .withTeamMemberEmailAddress(i.child("fullName").getValue(String.class))
                                        .withTeamMemberPhoneNumber(i.child("phoneNumber").getValue(String.class))
                                        //.withTeamMemberRule(TEAM_RULE.valueOf(i.child("rule").getValue(ArrayList.class).get(0).toString()))
                                        .withTeamMemberStatus(i.child("status").getValue(String.class))
                                        .setUserId(i.child("userId").getValue(Integer.class));
                                teamMembers.add(teamMember);
                                break;
                            }
                        }

                    }
                }
                if(callBack_addTaskActivityProtocol != null){
                    callBack_addTaskActivityProtocol.UpdateTeamMembers(teamMembers);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void AddTask(Task task, String projectId){
        //ref.child("theTasks").setValue(task);
        Map<String, Object>updates = new HashMap<String, Object>();
        updates.put(String.valueOf(task.getTaskId()), task);
        ref.child("theTasks").updateChildren(updates);

        this.ref.child("theProjects").child(projectId).child("projectManagementBoard").child("ids").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int counterMBTasks = 0;
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        counterMBTasks++;
                    }
                }
                AddTaskToProjectManagmentBoard(task,projectId, counterMBTasks);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void AddTaskToProjectManagmentBoard(Task task, String projectId, int counterMBTasks) {
        this.ref.child("theProjects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int index_projectsList = 0;
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        int project_Id = i.child("projectId").getValue(Integer.class);
                            if (project_Id == Integer.valueOf(projectId)){
                                Project project = i.getValue(Project.class);

                                DatabaseReference newRef = ref.child("theProjects").child(projectId).child("projectManagementBoard").child("ids");
                                Map<String, Object> updates = new HashMap<String, Object>();
                                updates.put(String.valueOf(counterMBTasks),task.getTaskId());
                                newRef.updateChildren(updates);
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
