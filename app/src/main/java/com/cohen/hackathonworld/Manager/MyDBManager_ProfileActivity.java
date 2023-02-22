package com.cohen.hackathonworld.Manager;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.cohen.hackathonworld.Activity.CallBack_ProfileActivityProtocol;
import com.cohen.hackathonworld.Model.IDsList;
import com.cohen.hackathonworld.Model.TEAM_RULE;
import com.cohen.hackathonworld.Model.TeamAccompany;
import com.cohen.hackathonworld.Model.TeamMember;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDBManager_ProfileActivity {

    private FirebaseDatabase db;
    private DatabaseReference ref;

    private FirebaseStorage storage;
    private StorageReference storage_ref;

    private CallBack_ProfileActivityProtocol callBack_profileActivityProtocol;

    public MyDBManager_ProfileActivity(){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("dataManager");
        storage = FirebaseStorage.getInstance();
        storage_ref = storage.getReference("storageManager");
    }

    public void setCallBack_profileActivityProtocol(CallBack_ProfileActivityProtocol callBack_profileActivityProtocol){
        this.callBack_profileActivityProtocol = callBack_profileActivityProtocol;
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
                            if(callBack_profileActivityProtocol != null){
                                callBack_profileActivityProtocol.UpdateProfile(teamMember);
                            }
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
                                    .withTeamAccompanyEmailAddress(i.child("emailAddress").getValue(String.class))
                                    .withTeamAccompanyMotherClassroom(i.child("classroomId").getValue(Integer.class))
                                    .withTeamAccompanyPhoneNumber(i.child("phoneNumber").getValue(String.class))
                                    .withTeamAccompanyRule()
                                    .withTeamAccompanyStatus(i.child("status").getValue(String.class))
                                    .setUserId(i.child("userId").getValue(Integer.class));
                            if(callBack_profileActivityProtocol != null){
                                callBack_profileActivityProtocol.UpdateProfile(teamAccompany);
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

    public void updateUserByIdentifier(String profileUserId, String profileName, String profileStatus, String profileRule){
        if(profileRule.compareTo(TEAM_RULE.ACCOMPANY.name()) == 0){

            DatabaseReference newRef = ref.child("theTeamAccompanies").child(profileUserId);

            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("fullName", profileName);
            updates.put("status", profileStatus);
            newRef.updateChildren(updates);
        }
        else {
            DatabaseReference newRef = ref.child("theTeamMembers").child(profileUserId);

            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("fullName", profileName);
            updates.put("status", profileStatus);
            newRef.updateChildren(updates);
        }

    }
    public void ChangeAvatar(String profileUserId, String profileRule, String img_url){
        Uri file = Uri.fromFile(new File(img_url));
        StorageReference imgRef = storage_ref.child("images/"+file.getLastPathSegment());
        UploadTask uploadTask = imgRef.putFile(file);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return storage_ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    UpdateUserAvatarByIdentifier(profileUserId, profileRule, downloadUri);
                } else {
                    // Handle failures
                    // ...
                }
            }
        });
    }

    private void UpdateUserAvatarByIdentifier(String profileUserId, String profileRule, Uri downloadUri){
        if(profileRule.compareTo(TEAM_RULE.ACCOMPANY.name()) == 0){

            DatabaseReference newRef = ref.child("theTeamAccompanies").child(profileUserId);

            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("avatar", downloadUri.toString());
            newRef.updateChildren(updates);
        }
        else {
            DatabaseReference newRef = ref.child("theTeamMembers").child(profileUserId);

            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("avatar", downloadUri.toString());
            newRef.updateChildren(updates);
        }
    }
}
