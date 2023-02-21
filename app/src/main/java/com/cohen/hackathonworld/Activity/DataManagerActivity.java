package com.cohen.hackathonworld.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cohen.hackathonworld.Exception.DateFormatException;
import com.cohen.hackathonworld.Manager.DataManager;
import com.cohen.hackathonworld.Model.Classroom;
import com.cohen.hackathonworld.Model.Project;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.Model.Team;
import com.cohen.hackathonworld.Model.TeamMember;
import com.cohen.hackathonworld.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;

public class DataManagerActivity extends AppCompatActivity {

    private TextView main_LBL_top;
    private MaterialButton main_BTN_updateUserName;
    private MaterialButton main_BTN_gameOver;

    //educate use************

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_manager);



        main_BTN_updateUserName = findViewById(R.id.main_BTN_updateName);
        main_BTN_gameOver = findViewById(R.id.main_BTN_gameOver);
        main_LBL_top = findViewById(R.id.main_LBL_top);





        
        main_BTN_updateUserName.setOnClickListener(view -> {
            try {
                writeToFireBaseDataManager();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            } catch (DateFormatException e) {
                throw new RuntimeException(e);
            }
        });
        main_BTN_gameOver.setOnClickListener(view -> updateFromFireBaseDataManager());


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void writeToFireBaseDataManager() throws CloneNotSupportedException, DateFormatException {
        DataManager dataManager = new DataManager();

        ArrayList<Integer> teamsMemids = new ArrayList<>();
        teamsMemids.add(1);
        teamsMemids.add(2);
        teamsMemids.add(3);
        teamsMemids.add(4);
        teamsMemids.add(5);





        Classroom cr1 = dataManager.AddNewClassroom("Leaf", 32.1115, 32.577789);
        Classroom cr2 = dataManager.AddNewClassroom("Water", 32.1116, 32.577785);
        Classroom cr3 = dataManager.AddNewClassroom("Mist", 32.1116, 32.577776);
        Classroom cr4 = dataManager.AddNewClassroom("Fog", 32.1115, 32.577723);
        Classroom cr5 = dataManager.AddNewClassroom("Fire", 32.1115, 32.57774);
        Classroom cr6 = dataManager.AddNewClassroom("Lightning", 32.1116, 32.57777);
        Classroom cr7 = dataManager.AddNewClassroom("Ice", 32.1112, 32.577771);
        Classroom cr8 = dataManager.AddNewClassroom("Magma", 32.1116, 32.5777702);
        Classroom cr9 = dataManager.AddNewClassroom("Sand", 32.1111, 32.5777736);
        Classroom cr10 = dataManager.AddNewClassroom("Mount", 32.11105, 32.577747);

        ArrayList<Integer> tasksTempList = new ArrayList<>();
        tasksTempList.add(8000000);
        tasksTempList.add(8000001);

        Project project1 = dataManager.AddNewProject("Sprint 1", 3000000, "ASSESSMENT_AND_PLANNING_LEVEL", tasksTempList);
        Project project2 = dataManager.AddNewProject("Sprint 2", 3000001, "ASSESSMENT_AND_PLANNING_LEVEL", tasksTempList);
        Project project3 = dataManager.AddNewProject("Sprint 1", 3000002, "ASSESSMENT_AND_PLANNING_LEVEL", tasksTempList);
        Project project4 = dataManager.AddNewProject("Sprint 1", 3000003, "ASSESSMENT_AND_PLANNING_LEVEL", tasksTempList);



        ArrayList<Integer> proIds = new ArrayList<>();
        proIds.add(project1.getProjectId());
        proIds.add(project2.getProjectId());
        proIds.add(project3.getProjectId());
        proIds.add(project4.getProjectId());

        Team team1 = dataManager.AddNewTeam("Genesis Team", 0, cr1.classroomId, 0,teamsMemids, proIds);
        Team team2 = dataManager.AddNewTeam("Exodus Team", 0, cr2.classroomId, 0,teamsMemids, proIds);
        Team team3 = dataManager.AddNewTeam("Leviticus Team", 0, cr3.classroomId, 0,teamsMemids, proIds);
        Team team4 = dataManager.AddNewTeam("Numbers Team", 0, cr4.classroomId, 0,teamsMemids, proIds);
        Team team5 = dataManager.AddNewTeam("Deuteronomy Team", 0, cr5.classroomId, 0,teamsMemids, proIds);

        ArrayList<Integer> accompanyTeamsIds = new ArrayList<>();
        accompanyTeamsIds.add(3000000);
        accompanyTeamsIds.add(3000001);
        accompanyTeamsIds.add(3000002);
        accompanyTeamsIds.add(3000003);

        ArrayList<Integer> storedFiles = new ArrayList<>();
        storedFiles.add(0);
        storedFiles.add(1);
        storedFiles.add(2);
        storedFiles.add(3);

        dataManager.AddNewTeamAccompany("Guy Isakov", cr6.classroomId, "Good day", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999990","beti0@gmail.com", accompanyTeamsIds, storedFiles);

        dataManager.AddNewTeamAccompany("Moshe Haim", cr7.classroomId, "Smile", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972544443333","beti1@gmail.com", accompanyTeamsIds, storedFiles);
        dataManager.AddNewTeamAccompany("Raz Shabtaiev", cr8.classroomId, "Excellence", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999992","beti2@gmail.com", accompanyTeamsIds, storedFiles);
        dataManager.AddNewTeamAccompany("Ronen Harar", cr9.classroomId, ":)", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999993","beti3@gmail.com", accompanyTeamsIds, storedFiles);
        dataManager.AddNewTeamAccompany("Aharoni", cr10.classroomId, "(:", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999994","beti4@gmail.com", accompanyTeamsIds, storedFiles);


        dataManager.AddNewTeamMember("Noah1", "YOLO1", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999901","gershon01@gmail.com", "QA_ENGINEER", team1.getTeamId());
        dataManager.AddNewTeamMember("Noah2", "YOLO2", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999902","gershon02@gmail.com", "TEAM_LEAD", team1.getTeamId());
        dataManager.AddNewTeamMember("Noah3", "YOLO3", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999903","gershon03@gmail.com", "UX_UI_DESIGNERS", team1.getTeamId());
        dataManager.AddNewTeamMember("Noah4", "YOLO4", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999904","gershon04@gmail.com", "SOFTWARE_DEVELOPERS", team1.getTeamId());
        dataManager.AddNewTeamMember("Noah5", "YOLO5", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999905","gershon05@gmail.com", "SOFTWARE_DEVELOPERS", team1.getTeamId());

        dataManager.AddNewTeamMember("Shaul1", "GoodVibes1", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999911","gershon11@gmail.com", "QA_ENGINEER", team2.getTeamId());
        dataManager.AddNewTeamMember("Shaul2", "GoodVibes2", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999922","gershon22@gmail.com", "TEAM_LEAD", team2.getTeamId());
        dataManager.AddNewTeamMember("Shaul3", "GoodVibes3", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999933","gershon33@gmail.com", "UX_UI_DESIGNERS", team2.getTeamId());
        dataManager.AddNewTeamMember("Shaul4", "GoodVibes4", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999944","gershon44@gmail.com", "SOFTWARE_DEVELOPERS", team2.getTeamId());
        dataManager.AddNewTeamMember("Shaul5", "GoodVibes5", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999955","gershon55@gmail.com", "SOFTWARE_DEVELOPERS", team2.getTeamId());

        dataManager.AddNewTeamMember("Fani1", "TNT1", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999921","gershon21@gmail.com", "QA_ENGINEER", team3.getTeamId());
        dataManager.AddNewTeamMember("Fani2", "TNT2", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999922","gershon22@gmail.com", "TEAM_LEAD", team3.getTeamId());
        dataManager.AddNewTeamMember("Fani3", "TNT3", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999923","gershon23@gmail.com", "UX_UI_DESIGNERS", team3.getTeamId());
        dataManager.AddNewTeamMember("Fani4", "TNT4", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999924","gershon24@gmail.com", "SOFTWARE_DEVELOPERS", team3.getTeamId());
        dataManager.AddNewTeamMember("Fani5", "TNT5", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999925","gershon25@gmail.com", "SOFTWARE_DEVELOPERS", team3.getTeamId());

        dataManager.AddNewTeamMember("Mergi1", "1", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999931","gershon31@gmail.com", "QA_ENGINEER", team4.getTeamId());
        dataManager.AddNewTeamMember("Mergi2", "2", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999932","gershon32@gmail.com", "TEAM_LEAD", team4.getTeamId());
        dataManager.AddNewTeamMember("Mergi3", "3", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999933","gershon33@gmail.com", "UX_UI_DESIGNERS", team4.getTeamId());
        dataManager.AddNewTeamMember("Mergi4", "4", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999934","gershon34@gmail.com", "SOFTWARE_DEVELOPERS", team4.getTeamId());
        dataManager.AddNewTeamMember("Mergi5", "5", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999935","gershon35@gmail.com", "SOFTWARE_DEVELOPERS", team4.getTeamId());

        TeamMember teamMem1 = dataManager.AddNewTeamMember("Simon1", "IGBO&SHAYO1", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999941","gershon41@gmail.com", "QA_ENGINEER", team5.getTeamId());
        TeamMember teamMem2 = dataManager.AddNewTeamMember("Simon2", "IGBO&SHAYO2", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999942","gershon42@gmail.com", "TEAM_LEAD", team5.getTeamId());
        TeamMember teamMem3 = dataManager.AddNewTeamMember("Simon3", "IGBO&SHAYO3", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999943","gershon43@gmail.com", "UX_UI_DESIGNERS", team5.getTeamId());
        TeamMember teamMem4 = dataManager.AddNewTeamMember("Simon4", "IGBO&SHAYO4", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999944","gershon44@gmail.com", "SOFTWARE_DEVELOPERS", team5.getTeamId());
        TeamMember teamMem5 = dataManager.AddNewTeamMember("Simon5", "IGBO&SHAYO5", "https://e7.pngegg.com/pngimages/807/625/png-clipart-pancake-drawing-crepe-red-waffle-food-hat.png", "+972549999945","gershon45@gmail.com", "SOFTWARE_DEVELOPERS", team5.getTeamId());

        ArrayList<Integer> teamsIds = new ArrayList<>();
        teamsIds.add(team1.getTeamId());
        teamsIds.add(team2.getTeamId());
        teamsIds.add(team3.getTeamId());
        teamsIds.add(team4.getTeamId());
        teamsIds.add(team5.getTeamId());

        ArrayList<Integer> teamsIds2 = new ArrayList<>();
        teamsIds.add(team1.getTeamId());
        teamsIds.add(team3.getTeamId());
        teamsIds.add(team5.getTeamId());

        dataManager.AddNewChat("General Chat", "GENERAL", teamsIds, 0);
        dataManager.AddNewChat("New Forum", "FORUM", teamsIds2, 0);

        ArrayList<Integer> teamMemberIds = new ArrayList<>();
        teamMemberIds.add(teamMem1.getUserId());
        teamMemberIds.add(teamMem2.getUserId());
        teamMemberIds.add(teamMem3.getUserId());

        Task task1 = dataManager.AddNewTask("Task1", "TO_DO", "Must listen to the next sentence,- Dim...", teamMemberIds, 0);
        Task task2 = dataManager.AddNewTask("Task2", "TO_DO", "Must listen to Yirmi", teamMemberIds, 0);



        dataManager.AddNewStoredFile(0, "JSON");



        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("dataManager");

        ref.setValue(dataManager);
    }


    private void updateFromFireBaseDataManager() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("dataManager");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 DataManager value = dataSnapshot.getValue(DataManager.class);
                if(value != null)
                    main_LBL_top.setText("Success");
                else{
                    main_LBL_top.setText("NAN");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("pttt", "Failed to read value.", error.toException());
            }
        });


    }

    private void uploadLinkToUserAvatar(int userId, String img_url){
        /*
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("dataManager");
        Uri file = Uri.fromFile(new File("C:\\Users\\MATAN\\AndroidStudioProjects\\HackathonWorld\\app\\src\\main\\res\\drawable\\img_avatar_itachi.png"));
        DatabaseReference riversRef = ref.child("images/"+file.getLastPathSegment());
        uploadTask = riversRef.putFile(file);

         */
    }

}