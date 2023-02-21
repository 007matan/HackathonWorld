package com.cohen.hackathonworld.Manager;

import androidx.annotation.NonNull;

import com.cohen.hackathonworld.Activity.CallBack_ManagementBoardActivityProtocol;
import com.cohen.hackathonworld.Activity.CallBack_MyTasksFragmentProtocol;
import com.cohen.hackathonworld.Model.IDsList;
import com.cohen.hackathonworld.Model.ManagementBoard;
import com.cohen.hackathonworld.Model.Project;
import com.cohen.hackathonworld.Model.TASK_STATUS;
import com.cohen.hackathonworld.Model.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDBManager_ManagementBoardActivity {

    private FirebaseDatabase db;
    private DatabaseReference ref;

    private CallBack_ManagementBoardActivityProtocol callBack_managementBoardActivityProtocol;


    public MyDBManager_ManagementBoardActivity(){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("dataManager");
    }

    public void setCallBack_managementBoardActivityProtocol(CallBack_ManagementBoardActivityProtocol callBack_managementBoardActivityProtocol) {
        this.callBack_managementBoardActivityProtocol = callBack_managementBoardActivityProtocol;
    }

    public void getTasksByProjectId_Type_MB(int projectId, TASK_STATUS task_status){
        this.ref.child("theProjects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int index_projectsList = 0;
                if (snapshot.exists()){
                    for (DataSnapshot i : snapshot.getChildren()){
                        int project_Id = i.child("projectId").getValue(Integer.class);
                        if (project_Id == projectId){
                            Project project = i.getValue(Project.class);
                            getTasksByProject_MB(project, task_status);
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
    private void getTasksByProject_MB(Project project, TASK_STATUS task_status) {
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
                    for(int k = 0; k < tasks.size(); k++){
                        if(tasks.get(k).getTaskType() !=task_status){
                            tasks.remove(k);
                            k--;
                        }
                    }
                    if(callBack_managementBoardActivityProtocol != null){
                        if(task_status == TASK_STATUS.BACKLOG) {
                            callBack_managementBoardActivityProtocol.UpdateTasksBackLog(tasks);
                        } else if (task_status == TASK_STATUS.TO_DO) {
                            callBack_managementBoardActivityProtocol.UpdateTasksToDo(tasks);
                        } else if (task_status == TASK_STATUS.IN_PROGRESS) {
                            callBack_managementBoardActivityProtocol.UpdateTasksInProgress(tasks);
                        } else if (task_status == TASK_STATUS.DONE) {
                            callBack_managementBoardActivityProtocol.UpdateTasksDone(tasks);
                        } else if (task_status == TASK_STATUS.OTHER) {
                            callBack_managementBoardActivityProtocol.UpdateTasksOther(tasks);
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
