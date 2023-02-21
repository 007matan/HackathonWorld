package com.cohen.hackathonworld.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cohen.hackathonworld.Model.TeamAccompany;
import com.cohen.hackathonworld.Model.TeamMember;
import com.cohen.hackathonworld.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomepageActivity extends AppCompatActivity {

    private enum USER_TYPE {
        MEMBER,
        ACCOMPANY
    }

    //dont use*************************************************************************

    private static int userId;
    private static USER_TYPE user_type;
    private FirebaseDatabase db;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        initAttr();


    }

    private void initAttr() {
        Intent intent = getIntent();
        String identifier = intent.getStringExtra("uphone");
        String identifier2 = intent.getStringExtra("uemail");

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("dataManager");
        getUserByIdentifier(identifier);
    }

    private void getUserByIdentifier(String userIdentifier){
        int usrid = 0;
        this.ref.child("theTeamMembers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Checking if the value exists
                if (snapshot.exists()){
                    //studentsList.clear();
                    int retUserId = 0;
                    // looping through the values
                    for (DataSnapshot i : snapshot.getChildren()){
                        TeamMember teamMember = i.getValue(TeamMember.class);
                        String phone_number = "+972" +teamMember.getPhoneNumber().substring(1);
                        // checking if the name searched is available and adding it to the array list
                        if (phone_number == userIdentifier || teamMember.getEmailAddress() == userIdentifier){
                            userId = teamMember.getUserId();
                            user_type = USER_TYPE.MEMBER;
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
                // Checking if the value exists
                if (snapshot.exists()){
                    //studentsList.clear();
                    int retUserId = 0;
                    // looping through the values
                    for (DataSnapshot i : snapshot.getChildren()){
                        TeamAccompany teamAccompany = i.getValue(TeamAccompany.class);
                        // checking if the name searched is available and adding it to the array list
                        if (teamAccompany.getPhoneNumber() == userIdentifier || teamAccompany.getEmailAddress() == userIdentifier){
                            userId = teamAccompany.getUserId();
                            user_type = USER_TYPE.ACCOMPANY;
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