package com.cohen.hackathonworld.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cohen.hackathonworld.Exception.DateFormatException;
import com.cohen.hackathonworld.Manager.MyDBManager_AddTaskActivity;
import com.cohen.hackathonworld.Model.Project;
import com.cohen.hackathonworld.Model.TASK_STATUS;
import com.cohen.hackathonworld.Model.TEAM_RULE;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.Model.Team;
import com.cohen.hackathonworld.Model.TeamMember;
import com.cohen.hackathonworld.Model.User;
import com.cohen.hackathonworld.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity implements Adapter_Avatar.AvatarViewHolder.OnAvatarListener {

    private ArrayList<TeamMember> teamMembersAssign = new ArrayList<>();


    private RecyclerView add_task_LST_team_mem_assign;
    private AppCompatButton id_btn_enter;
    private AppCompatButton id_btn_cancel;

    private TextInputEditText id_tf_taskName;
    private RadioButton id_radbtn_back_log;
    private RadioButton id_radbtn_to_do;
    private RadioButton id_radbtn_in_progress;
    private RadioButton id_radbtn_done;
    private RadioButton id_radbtn_other;

    private TextInputEditText id_tf_desc;

    private Task myTask;

    private MyDBManager_AddTaskActivity myDBManager_addTaskActivity;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        myDBManager_addTaskActivity = new MyDBManager_AddTaskActivity();
        myTask = new Task();

        String userPhone = getIntent().getStringExtra("phoneNumber");

        String taskType = getIntent().getStringExtra("taskstatus");

        String projectId = getIntent().getStringExtra("projectid");


        findViews();
        initView(projectId);

        if(taskType != null){
            initRadioGroup(taskType);
        }


        myDBManager_addTaskActivity.setCallBack_AddTaskActivityProtocol(callBack_addTaskActivityProtocol);
        myDBManager_addTaskActivity.getUserByIdentifier(userPhone);





    }

    private void initRadioGroup(String taskType) {
        if(taskType.compareTo(TASK_STATUS.BACKLOG.name()) == 0){
            id_radbtn_back_log.setChecked(true);
        } else if (taskType.compareTo(TASK_STATUS.TO_DO.name()) == 0) {
            id_radbtn_to_do.setChecked(true);
        } else if (taskType.compareTo(TASK_STATUS.IN_PROGRESS.name()) == 0) {
            id_radbtn_in_progress.setChecked(true);
        } else if (taskType.compareTo(TASK_STATUS.DONE.name()) == 0) {
            id_radbtn_done.setChecked(true);
        } else if (taskType.compareTo(TASK_STATUS.OTHER.name()) == 0) {
            id_radbtn_other.setChecked(true);
        }
    }

    private void initView(String projectId) {
        id_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(AddTaskActivity.this, HomeActivity.class);
                //startActivity(intent);
                finish();
            }
        });

        id_btn_enter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //collect info input from user- beside teamMemberassigned
                String task_name = id_tf_taskName.getText().toString();
                TASK_STATUS task_status = TASK_STATUS.TO_DO;
                if(id_radbtn_back_log.isChecked()){
                    task_status = TASK_STATUS.valueOf(id_radbtn_back_log.getText().toString());
                }
                else if(id_radbtn_to_do.isChecked()){
                    task_status = TASK_STATUS.valueOf(id_radbtn_to_do.getText().toString());
                }
                else if(id_radbtn_in_progress.isChecked()){
                    task_status = TASK_STATUS.valueOf(id_radbtn_in_progress.getText().toString());
                }
                else if (id_radbtn_done.isChecked()) {
                    task_status = TASK_STATUS.valueOf(id_radbtn_done.getText().toString());
                } else if (id_radbtn_other.isChecked()) {
                    task_status = TASK_STATUS.valueOf(id_radbtn_other.getText().toString());
                }
                String task_description = id_tf_desc.getText().toString();

                //update the myTask
                try {
                    myTask
                            .withTaskName(task_name)
                            .withTaskType(task_status)
                            .withTaskId()
                            .withTaskDescription(task_description)
                            .withTaskPerformedDate()
                            .withTaskCreatorId(2134558);
                } catch (DateFormatException e) {
                    throw new RuntimeException(e);
                }

                //Addtask to database
                myDBManager_addTaskActivity.AddTask(myTask, projectId);
                //Intent intent = new Intent(AddTaskActivity.this, HomeActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }

    private void findViews() {

        add_task_LST_team_mem_assign = findViewById(R.id.add_task_LST_team_avatar);
        id_btn_cancel = findViewById(R.id.id_btn_cancel);
        id_btn_enter = findViewById(R.id.id_btn_enter);

        id_tf_taskName = findViewById(R.id.id_tf_taskName);
        id_radbtn_back_log = findViewById(R.id.id_radbtn_back_log);
        id_radbtn_to_do = findViewById(R.id.id_radbtn_to_do);
        id_radbtn_in_progress = findViewById(R.id.id_radbtn_in_progress);
        id_radbtn_done = findViewById(R.id.id_radbtn_done);
        id_radbtn_other = findViewById(R.id.id_radbtn_other);
        id_tf_desc = findViewById(R.id.id_tf_desc);

    }

    public void updateUITeamMembers(ArrayList<TeamMember> teamMembers){
        Adapter_Avatar adapter_avatar = new Adapter_Avatar(this, teamMembers, this);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        add_task_LST_team_mem_assign.setLayoutManager(layoutManager);
        add_task_LST_team_mem_assign.setAdapter(adapter_avatar);
    }


    @Override
    public void onAvatarClick(int position, String userId) {

        myTask.setTaskTeamMemberAssign( Integer.parseInt(userId));

        Toast.makeText(this, ""+userId + " chosen", Toast.LENGTH_SHORT).show();
    }

    CallBack_AddTaskActivityProtocol callBack_addTaskActivityProtocol = new CallBack_AddTaskActivityProtocol() {
        @Override
        public void UpdateTeamMembers(ArrayList<TeamMember> teamMembers) {
            updateUITeamMembers(teamMembers);
        }
    };

}