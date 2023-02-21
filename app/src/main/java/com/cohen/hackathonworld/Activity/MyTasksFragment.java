package com.cohen.hackathonworld.Activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cohen.hackathonworld.Exception.DateFormatException;
import com.cohen.hackathonworld.Manager.MyDBManager_MyTasksFragment;
import com.cohen.hackathonworld.Model.TASK_STATUS;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.databinding.FragmentMyTasksBinding;

import java.util.ArrayList;

public class MyTasksFragment extends Fragment implements Adapter_Task.TaskViewHolder.OnTaskListener {

    private FragmentMyTasksBinding binding;

    private MyDBManager_MyTasksFragment myDBManager_myTasksFragment;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMyTasksBinding.inflate(inflater, container, false);

        myDBManager_myTasksFragment = new MyDBManager_MyTasksFragment();

        return binding.getRoot();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getParentFragmentManager().setFragmentResultListener("dataFromHomeScreenFragment", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String userPhone = result.getString("phoneNumber");
                String projId = result.getString("projectId");
                binding.idTvInvisiblePhonenumber.setText(userPhone);
                binding.idTvInvisibleProjectId.setText(projId);


                //'getintent"
                ///// String phone = "+972544443333";
                //String projectId = "7000003";

                String phone = binding.idTvInvisiblePhonenumber.getText().toString();
                //String phone = "+972549957941";  //team member
                String projectId = binding.idTvInvisibleProjectId.getText().toString();




                myDBManager_myTasksFragment.setCallBack_myTasksFragmentProtocol(callBack_myTasksFragmentProtocol);

                binding.idMyTasksChooseType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(binding.idMyTasksRadbtnAll.isChecked()){
                            //task_status = TASK_STATUS.valueOf(id_radbtn_back_log.getText().toString());
                            myDBManager_myTasksFragment.pregetTasksByProjectId_Type(Integer.valueOf(projectId), binding.idMyTasksRadbtnAll.getText().toString(),phone);
                        }
                        else if(binding.idMyTasksRadbtnToDo.isChecked()){
                            //task_status = TASK_STATUS.valueOf(id_radbtn_to_do.getText().toString());
                            myDBManager_myTasksFragment.pregetTasksByProjectId_Type(Integer.valueOf(projectId), binding.idMyTasksRadbtnToDo.getText().toString(), phone);
                        }
                        else if(binding.idMyTasksRadbtnInPrg.isChecked()){
                            //task_status = TASK_STATUS.valueOf(id_radbtn_in_progress.getText().toString());
                            myDBManager_myTasksFragment.pregetTasksByProjectId_Type(Integer.valueOf(projectId), binding.idMyTasksRadbtnInPrg.getText().toString(), phone);
                        }
                        else if (binding.idMyTasksRadbtnDone.isChecked()) {
                            //task_status = TASK_STATUS.valueOf(id_radbtn_done.getText().toString());
                            myDBManager_myTasksFragment.pregetTasksByProjectId_Type(Integer.valueOf(projectId), binding.idMyTasksRadbtnDone.getText().toString(), phone);
                        }
                        else if (binding.idMyTasksRadbtnBL.isChecked()) {
                            //task_status = TASK_STATUS.valueOf(id_radbtn_other.getText().toString());
                            myDBManager_myTasksFragment.pregetTasksByProjectId_Type(Integer.valueOf(projectId), binding.idMyTasksRadbtnBL.getText().toString(), phone);
                        }
                        else if (binding.idMyTasksRadbtnOther.isChecked()) {
                            //task_status = TASK_STATUS.valueOf(id_radbtn_other.getText().toString());
                            myDBManager_myTasksFragment.pregetTasksByProjectId_Type(Integer.valueOf(projectId), binding.idMyTasksRadbtnOther.getText().toString(), phone);
                        }
                    }
                });



            }
        });


    }

    public void updateUITasks(ArrayList<Task> tasksArr){

        Adapter_Task adapter_task = new Adapter_Task(getActivity(), tasksArr, this);
        LinearLayoutManager layoutManagerTask
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.myTaskLSTTask.setLayoutManager(layoutManagerTask);
        binding.myTaskLSTTask.setAdapter(adapter_task);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onTaskClick(int position, String taskId) {

        myDBManager_myTasksFragment.UpgradeTaskStatus(taskId);
        Log.d("pttt", "onTaskClick: ");
    }

    CallBack_MyTasksFragmentProtocol callBack_myTasksFragmentProtocol = new CallBack_MyTasksFragmentProtocol() {
        @Override
        public void updateTasks(ArrayList<Task> myTasks) {
            updateUITasks(myTasks);
        }

    };
}