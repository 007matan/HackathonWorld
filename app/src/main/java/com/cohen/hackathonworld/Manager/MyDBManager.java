package com.cohen.hackathonworld.Manager;

import androidx.annotation.NonNull;

import com.cohen.hackathonworld.Activity.CallBack_HomeScreenFragmentProtocol;
import com.cohen.hackathonworld.Model.Chat;
import com.cohen.hackathonworld.Model.Classroom;
import com.cohen.hackathonworld.Model.IDsList;
import com.cohen.hackathonworld.Model.ManagementBoard;
import com.cohen.hackathonworld.Model.Project;
import com.cohen.hackathonworld.Model.StoredFiles;
import com.cohen.hackathonworld.Model.TEAM_RULE;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.Model.Team;
import com.cohen.hackathonworld.Model.TeamMember;
import com.cohen.hackathonworld.Model.WorkSpace;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MyDBManager {

    private static int userId;
    private static TEAM_RULE userType;
    private static int userCurrteamId;

    private int userId_nonstatic = 5;
    private TEAM_RULE userType_nonstatic = TEAM_RULE.SCRUM_MASTER;
    private int userCurrteamId_nonstatic = 999;

    private static ArrayList<Integer> globalFreeUseList;

    private static Chat myChat;
    private static Team myTeam;
    private static Classroom myClassroom;
    private static StoredFiles myStoredFiles;
    private static Project myProject;


    private static Task myTask;
    //private static User myUser;



    private FirebaseDatabase db;
    private DatabaseReference ref;

    private CallBack_HomeScreenFragmentProtocol callBack_homeScreenFragmentProtocol;

    public void setCallBack_homeScreenFragmentProtocol(CallBack_HomeScreenFragmentProtocol callBack_homeScreenFragmentProtocol) {
        this.callBack_homeScreenFragmentProtocol = callBack_homeScreenFragmentProtocol;
    }

    public MyDBManager(){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("dataManager");
    }


    public static int getUserId() {
        return userId;
    }

    public void setUserCurrteamByTeamId(int teamId){
        //check in db before u do it maybe...
        userCurrteamId = teamId;
    }

    public void setUserByIdentifier(String userIdentifier){

        this.ref.child("theTeamMembers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        //TeamAccompany teamAccompany = i.getValue(TeamAccompany.class); - NOT POSIBBLE attributes in user
                        String teamMemberPhoneNumber = i.child("phoneNumber").getValue(String.class);
                        //IDsList teamAccompanyTeamList = i.child("teamList").getValue(IDsList.class);
                        if (teamMemberPhoneNumber.equals(userIdentifier)){
                            userId = i.child("userId").getValue(Integer.class);
                            userType = TEAM_RULE.OTHER;
                            userCurrteamId = i.child("teamList").child("ids").getValue(Integer.class);
                            setUserCurrteamByTeamId(45);
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
                        //TeamAccompany teamAccompany = i.getValue(TeamAccompany.class); - NOT POSIBBLE attributes in user
                        String teamAccompanyPhoneNumber = i.child("phoneNumber").getValue(String.class);
                        //IDsList teamAccompanyTeamList = i.child("teamList").getValue(IDsList.class);
                        if (teamAccompanyPhoneNumber.equals(userIdentifier)){
                            userId = i.child("userId").getValue(Integer.class);
                            userType = TEAM_RULE.ACCOMPANY;
                            userCurrteamId = i.child("teamList").child("ids").getChildren().iterator().next().getValue(Integer.class);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
/*
        this.ref.child("theTasks").child("8000000").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //ArrayList arrayList = new ArrayList<>();
                //ArrayList taskArr = new ArrayList<Task>();
                //IDsList myList = new IDsList();
                if (snapshot.exists()) {
                    int creatorId = snapshot.child("taskCreatorId").getValue(Integer.class);
                    Log.w("pttt", "ffff");
                    String taskDesc = snapshot.child("taskDescription").getValue(String.class);
                    Log.w("pttt", "ffff");
                    IDsList list = snapshot.child("taskTeamMembersAssign").getValue(IDsList.class);

                    Log.w("pttt", "ffff");
                    MyDate myDate = snapshot.child("taskPerformedDate").getValue(MyDate.class);
                    Log.w("pttt", "ffff");
                    try {
                        myTask = snapshot.getValue(Task.class);
                        Task task = new Task()
                                .withTaskId()
                                .withTaskCreatorId(creatorId)
                                .withTaskPerformedDate();
                        Log.w("pttt", "eee");
                    } catch (DateFormatException e) {
                        throw new RuntimeException(e);
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

 */

        userId_nonstatic = userId;
        userType_nonstatic = userType;
        userCurrteamId_nonstatic = userCurrteamId;

    }
    public static TEAM_RULE getUserType(){
        return userType;
    }

    public ArrayList<Integer> getUserTeams() {

        if(userType == TEAM_RULE.ACCOMPANY) {
            this.ref.child("theTeamAcompany").child(""+userId).child("teamList").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot i : snapshot.getChildren()){
                            globalFreeUseList=i.getValue(ArrayList.class);
                            return;
                        }
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            this.ref.child("theTeamMembers").child(""+userId).child("teamList").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot i : snapshot.getChildren()){
                            globalFreeUseList=i.getValue(ArrayList.class);
                            return;
                        }
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return globalFreeUseList;
    }

    public void getUserByIdentifier(String identifier){
            this.ref.child("theTeamMembers").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot i : snapshot.getChildren()){
                            String teamMemberPhoneNumber = i.child("phoneNumber").getValue(String.class);
                            if (teamMemberPhoneNumber.equals(identifier)){
                                TeamMember teamMember = i.getValue(TeamMember.class);
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
                                userId = i.child("userId").getValue(Integer.class);
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
                                Task task = i.getValue(Task.class);
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

}
