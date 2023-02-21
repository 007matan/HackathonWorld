package com.cohen.hackathonworld.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cohen.hackathonworld.Exception.DateFormatException;
import com.cohen.hackathonworld.Manager.MyDBManager_ManagementBoardActivity;
import com.cohen.hackathonworld.Model.TASK_STATUS;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.R;

import java.util.ArrayList;

public class ManagementBoardActivity extends AppCompatActivity implements Adapter_Task.TaskViewHolder.OnTaskListener {

    private AppCompatTextView management_board_TV_BL;

    private RecyclerView management_board_LST_BL_tasks;
    private AppCompatTextView management_board_TV_TO_DO;

    private RecyclerView management_board_LST_TO_DO_tasks;

    private AppCompatTextView management_board_TV_IN_PROGRESS;

    private RecyclerView management_board_LST_IN_PROGRESS_tasks;
    private AppCompatTextView management_board_TV_DONE;

    private RecyclerView management_board_LST_DONE_tasks;

    private AppCompatTextView management_board_TV_OTHER;

    private RecyclerView management_board_LST_OTHER_tasks;

    private AppCompatImageButton id_plusbtn_BackLog;
    private AppCompatImageButton id_plusbtn_ToDo;
    private AppCompatImageButton id_plusbtn_InProgress;
    private AppCompatImageButton id_plusbtn_Done;
    private AppCompatImageButton id_plusbtn_Other;


    private MyDBManager_ManagementBoardActivity myDBManager_managementBoardActivity;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_board);

        myDBManager_managementBoardActivity = new MyDBManager_ManagementBoardActivity();

        //'getintent"
        //String phone = "+972544443333";
        //String projectId = "7000003";

        Intent intent = this.getIntent();
        String phone  = intent.getStringExtra("phoneNumber");
        String projectId = intent.getStringExtra("projectId");

        findViews();
        initView(projectId, phone);
        //initView(projectId, "+972549999901"); //if you want to check after in my tasks

        myDBManager_managementBoardActivity.setCallBack_managementBoardActivityProtocol(callBack_managementBoardActivityProtocol);

        myDBManager_managementBoardActivity.getTasksByProjectId_Type_MB(Integer.valueOf(projectId), TASK_STATUS.BACKLOG);
        myDBManager_managementBoardActivity.getTasksByProjectId_Type_MB(Integer.valueOf(projectId), TASK_STATUS.TO_DO);
        myDBManager_managementBoardActivity.getTasksByProjectId_Type_MB(Integer.valueOf(projectId), TASK_STATUS.IN_PROGRESS);
        myDBManager_managementBoardActivity.getTasksByProjectId_Type_MB(Integer.valueOf(projectId), TASK_STATUS.DONE);
        myDBManager_managementBoardActivity.getTasksByProjectId_Type_MB(Integer.valueOf(projectId), TASK_STATUS.OTHER);


    }

    private void initView(String projectId, String phone) {
        Intent intent = new Intent(ManagementBoardActivity.this, AddTaskActivity.class);
        intent.putExtra("projectid", projectId);
        intent.putExtra("phoneNumber", phone);
        id_plusbtn_BackLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("taskstatus", TASK_STATUS.BACKLOG.name());
                startActivity(intent);
                finish();
            }
        });
        id_plusbtn_ToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("taskstatus", TASK_STATUS.TO_DO.name());
                startActivity(intent);
                finish();
            }
        });
        id_plusbtn_InProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("taskstatus", TASK_STATUS.IN_PROGRESS.name());
                startActivity(intent);
                finish();
            }
        });
        id_plusbtn_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("taskstatus", TASK_STATUS.DONE.name());
                startActivity(intent);
                finish();
            }
        });
        id_plusbtn_Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("taskstatus", TASK_STATUS.OTHER.name());
                startActivity(intent);
                finish();
            }
        });
    }

    private void findViews() {
        management_board_TV_BL= findViewById(R.id.id_TV_BL);
        management_board_LST_BL_tasks=findViewById(R.id.M_B_LST_Task_BL);
        management_board_TV_TO_DO= findViewById(R.id.id_TV_TO_DO);
        management_board_LST_TO_DO_tasks=findViewById(R.id.M_B_LST_Task_TO_DO);
        management_board_TV_IN_PROGRESS= findViewById(R.id.id_TV_IN_PROGRESS);
        management_board_LST_IN_PROGRESS_tasks=findViewById(R.id.M_B_LST_Task_IN_PROGRESS);
        management_board_TV_DONE= findViewById(R.id.id_TV_DONE);
        management_board_LST_DONE_tasks=findViewById(R.id.M_B_LST_Task_DONE);
        management_board_TV_OTHER= findViewById(R.id.id_TV_OTHER);
        management_board_LST_OTHER_tasks=findViewById(R.id.M_B_LST_Task_OTHER);

        id_plusbtn_BackLog = findViewById(R.id.id_plusbtn_BackLog);
        id_plusbtn_ToDo = findViewById(R.id.id_plusbtn_ToDo);
        id_plusbtn_InProgress = findViewById(R.id.id_plusbtn_InProgress);
        id_plusbtn_Done = findViewById(R.id.id_plusbtn_Done);
        id_plusbtn_Other = findViewById(R.id.id_plusbtn_Other);
    }

    public void UpdateUITasksBackLog(ArrayList<Task> tasksArr){

        Adapter_Task adapter_task = new Adapter_Task(ManagementBoardActivity.this, tasksArr, this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(ManagementBoardActivity.this, LinearLayoutManager.VERTICAL, false);
        management_board_LST_BL_tasks.setLayoutManager(layoutManager);
        management_board_LST_BL_tasks.setAdapter(adapter_task);
    }

    public void UpdateUITasksToDo(ArrayList<Task> tasksArr){

        Adapter_Task adapter_task_TO_DO = new Adapter_Task(ManagementBoardActivity.this, tasksArr, this);
        LinearLayoutManager layoutManager_TO_DO
                = new LinearLayoutManager(ManagementBoardActivity.this, LinearLayoutManager.VERTICAL, false);
        management_board_LST_TO_DO_tasks.setLayoutManager(layoutManager_TO_DO);
        management_board_LST_TO_DO_tasks.setAdapter(adapter_task_TO_DO);
    }

    public void UpdateUITasksInProgress(ArrayList<Task> tasksArr){

        Adapter_Task adapter_task_IN_PROGRESS = new Adapter_Task(ManagementBoardActivity.this, tasksArr, this);
        LinearLayoutManager layoutManager_IN_PROGRESS
                = new LinearLayoutManager(ManagementBoardActivity.this, LinearLayoutManager.VERTICAL, false);
        management_board_LST_IN_PROGRESS_tasks.setLayoutManager(layoutManager_IN_PROGRESS);
        management_board_LST_IN_PROGRESS_tasks.setAdapter(adapter_task_IN_PROGRESS);
    }

    public void UpdateUITasksDone(ArrayList<Task> tasksArr){

        Adapter_Task adapter_task_DONE = new Adapter_Task(ManagementBoardActivity.this, tasksArr, this);
        LinearLayoutManager layoutManager_DONE
                = new LinearLayoutManager(ManagementBoardActivity.this, LinearLayoutManager.VERTICAL, false);
        management_board_LST_DONE_tasks.setLayoutManager(layoutManager_DONE);
        management_board_LST_DONE_tasks.setAdapter(adapter_task_DONE);
    }

    public void UpdateUITasksOther(ArrayList<Task> tasksArr){

        Adapter_Task adapter_task_OTHER = new Adapter_Task(ManagementBoardActivity.this, tasksArr, this);
        LinearLayoutManager layoutManager_OTHER
                = new LinearLayoutManager(ManagementBoardActivity.this, LinearLayoutManager.VERTICAL, false);
        management_board_LST_OTHER_tasks.setLayoutManager(layoutManager_OTHER);
        management_board_LST_OTHER_tasks.setAdapter(adapter_task_OTHER);
    }

CallBack_ManagementBoardActivityProtocol callBack_managementBoardActivityProtocol = new CallBack_ManagementBoardActivityProtocol() {

    @Override
    public void UpdateTasksBackLog(ArrayList<Task> tasksArr) {
        UpdateUITasksBackLog(tasksArr);
    }

    @Override
    public void UpdateTasksToDo(ArrayList<Task> tasksArr) {
        UpdateUITasksToDo(tasksArr);
    }

    @Override
    public void UpdateTasksInProgress(ArrayList<Task> tasksArr) {
        UpdateUITasksInProgress(tasksArr);
    }

    @Override
    public void UpdateTasksDone(ArrayList<Task> tasksArr) {
        UpdateUITasksDone(tasksArr);
    }

    @Override
    public void UpdateTasksOther(ArrayList<Task> tasksArr) {
        UpdateUITasksOther(tasksArr);
    }
};
    @Override
    public void onTaskClick(int position, String taskId) {
        myDBManager_managementBoardActivity.UpgradeTaskStatus(taskId);
        Log.d("pttt", "onTaskClick: (ManagmentBoard)");
    }
}