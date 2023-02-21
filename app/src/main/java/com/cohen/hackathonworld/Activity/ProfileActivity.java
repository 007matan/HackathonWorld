package com.cohen.hackathonworld.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.cohen.hackathonworld.Manager.MyDBManager_ProfileActivity;
import com.cohen.hackathonworld.Model.User;
import com.cohen.hackathonworld.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity {

    private FloatingActionButton profile_btn_home;
    private AppCompatImageView id_imgvw_profile_avatar;

    private TextInputEditText id_tf_profile_name;
    private TextInputEditText id_tf_profile_status;
    private TextInputEditText id_tf_profile_phonenumber;
    private TextInputEditText id_tf_profile_email;
    private TextInputEditText id_tf_profile_Rule;


    private AppCompatTextView id_tv_invisible_userid;

    private AppCompatImageButton id_btn_edit_profile;
    private AppCompatImageButton id_btn_confirmEdit;

    private FloatingActionButton id_btn_edit_avatar_img;

    private TextInputEditText id_tf_invisible_input_imgurl;
    private AppCompatButton id_btn_confirm_imgurl;


    private MyDBManager_ProfileActivity myDBManager_profileActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myDBManager_profileActivity = new MyDBManager_ProfileActivity();

        String userPhone = getIntent().getStringExtra("phoneNumber");


        findView();
        initView(userPhone);

        myDBManager_profileActivity.setCallBack_profileActivityProtocol(callBack_profileActivityProtocol);
        myDBManager_profileActivity.getUserByIdentifier(userPhone);


    }

    private void initView(String userPhone) {
        profile_btn_home.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            //startActivity(intent);
            finish();
        }
         });

        id_btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_tf_profile_name.setFocusableInTouchMode(true);
                id_tf_profile_status.setFocusableInTouchMode(true);
                //id_tf_profile_phonenumber.setFocusableInTouchMode(true);
                //id_tf_profile_email.setFocusableInTouchMode(true);
                //id_tf_profile_Rule.setFocusableInTouchMode(true);

                id_btn_confirmEdit.setVisibility(View.VISIBLE);
            }
        });

        id_btn_confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profileName = id_tf_profile_name.getText().toString();
                String profileStatus = id_tf_profile_status.getText().toString();
                //String profilePhoneNumber = id_tf_profile_phonenumber.getText().toString();
                //String profileEmail = id_tf_profile_email.getText().toString();
                String profileRule = id_tf_profile_Rule.getText().toString();
                String profileUserId = id_tv_invisible_userid.getText().toString();
                myDBManager_profileActivity.updateUserByIdentifier(profileUserId, profileName, profileStatus, profileRule);

                finish();
            }
        });

        id_btn_edit_avatar_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_tf_invisible_input_imgurl.setVisibility(View.VISIBLE);
                id_btn_confirm_imgurl.setVisibility(View.VISIBLE);
            }
        });

        id_btn_confirm_imgurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profileUserId = id_tv_invisible_userid.getText().toString();
                String profileRule = id_tf_profile_Rule.getText().toString();
                myDBManager_profileActivity.ChangeAvatar(profileUserId, profileRule, id_tf_invisible_input_imgurl.getText().toString());
                id_tf_invisible_input_imgurl.setVisibility(View.INVISIBLE);
                id_btn_confirm_imgurl.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void findView() {

        profile_btn_home = findViewById(R.id.id_btn_home);

        id_imgvw_profile_avatar = findViewById(R.id.id_imgvw_profile_avatar);
        id_tf_profile_name = findViewById(R.id.id_tf_profile_name);
        id_tf_profile_status = findViewById(R.id.id_tf_profile_status);
        id_tf_profile_phonenumber = findViewById(R.id.id_tf_profile_phonenumber);
        id_tf_profile_email = findViewById(R.id.id_tf_profile_email);
        id_tf_profile_Rule = findViewById(R.id.id_tf_profile_Rule);
        id_tv_invisible_userid = findViewById(R.id.id_tv_invisible_userid);
        id_btn_edit_profile = findViewById(R.id.id_btn_edit_profile);
        id_btn_confirmEdit = findViewById(R.id.id_btn_confirmEdit);

        id_btn_edit_avatar_img = findViewById(R.id.id_btn_edit_avatar_img);

        id_tf_invisible_input_imgurl = findViewById(R.id.id_tf_invisible_input_imgurl);
        id_btn_confirm_imgurl = findViewById(R.id.id_btn_confirm_imgurl);
    }

    public void updateUIProfile(User user){
        Glide
                .with(this)
                .load(user.getAvatar())
                .into(id_imgvw_profile_avatar);
        id_tf_profile_name.setText(user.getFullName());
        id_tf_profile_status.setText(user.getStatus());
        id_tf_profile_phonenumber.setText(user.getPhoneNumber());
        id_tf_profile_email.setText(user.getEmailAddress());
        id_tf_profile_Rule.setText(user.getRule().get(0).name());
        id_tv_invisible_userid.setText(String.valueOf(user.getUserId()));
    }

    CallBack_ProfileActivityProtocol callBack_profileActivityProtocol = new CallBack_ProfileActivityProtocol() {

        @Override
        public void UpdateProfile(User user) {
            updateUIProfile(user);
        }
    };
}