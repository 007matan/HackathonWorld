package com.cohen.hackathonworld.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cohen.hackathonworld.Model.Team;
import com.cohen.hackathonworld.R;

import java.util.ArrayList;

public class AddChatActivity extends AppCompatActivity implements Adapter_Team.TeamViewHolder.OnTeamListener {

    private ArrayList<Team> teams = new ArrayList<>();

    private RecyclerView add_chat_LST_team;
    private AppCompatTextView id_tv_team_name;
    private AppCompatButton id_btn_team;

    private AppCompatButton id_btn_enter;

    private AppCompatButton id_btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);

        findViews();

        teams.add(new Team()
                .withTeamId()
                .withTeamName("Choko"));
        teams.add(new Team()
                .withTeamId()
                .withTeamName("Moko"));

        Adapter_Team adapter_team = new Adapter_Team(this, teams, this);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        add_chat_LST_team.setLayoutManager(layoutManager);
        add_chat_LST_team.setAdapter(adapter_team);

        id_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(AddChatActivity.this, HomeActivity.class);
                //startActivity(intent);
                finish();
            }
        });

        id_btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void findViews() {
        add_chat_LST_team = findViewById(R.id.add_chat_LST_team);
        id_tv_team_name = findViewById(R.id.id_tv_team_name);
        id_btn_cancel = findViewById(R.id.id_btn_cancel);
        id_btn_enter = findViewById(R.id.id_btn_enter);
    }

    @Override
    public void onTeamClick(int position, String s) {
        teams.get(position);
        //id_tv_team_name.setBackgroundColor(162);
        //add team to the chat you built
    }
}