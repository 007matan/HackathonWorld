package com.cohen.hackathonworld.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.cohen.hackathonworld.Manager.MyDBManager_HomeScreenFragment;
import com.cohen.hackathonworld.Model.Project;
import com.cohen.hackathonworld.Model.TEAM_RULE;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.Model.Team;
import com.cohen.hackathonworld.Model.User;
import com.cohen.hackathonworld.R;
import com.cohen.hackathonworld.databinding.FragmentHomeScreenBinding;

import java.util.ArrayList;

public class HomeScreenFragment extends Fragment implements Adapter_Team.TeamViewHolder.OnTeamListener, Adapter_Project.ProjectViewHolder.OnProjectListener, Adapter_Task.TaskViewHolder.OnTaskListener{

    private FragmentHomeScreenBinding binding;

    ArrayList<Team> teams = new ArrayList<>();

    ArrayList<Project> projects = new ArrayList<>();

    //private MyDBManager myDBManager;
    private MyDBManager_HomeScreenFragment myDBManager_homeScreenFragment;
    //private RecyclerView first_LST_tasks;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHomeScreenBinding.inflate(inflater, container, false);

        //myDBManager = new MyDBManager();
        myDBManager_homeScreenFragment = new MyDBManager_HomeScreenFragment();

        return binding.getRoot();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Intent intent = getActivity().getIntent();
        String userPhone = intent.getStringExtra("uphone");

        initViews(userPhone);

        //set to callback
        myDBManager_homeScreenFragment.setCallBack_homeScreenFragmentProtocol(callBack_homeScreenFragmentProtocol);

        //RequestUser
        myDBManager_homeScreenFragment.getUserByIdentifier(userPhone);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onTeamClick(int position, String Team_Name) {
        //teams.get(position);
        //userCurrTeamId update
        /*myDBManager.setUserCurrteamByTeamId(teams.get(position).getTeamId());*/
        // call runUi
        //id_tv_team_name.setBackgroundColor(162);
        //add team to the chat you built

        //myDBManager_homeScreenFragment.getWorkSpaceByTeamId(teams.get(position));
        myDBManager_homeScreenFragment.preparationFor_getWorkSpaceByTeamId(Team_Name);


    }

    @Override
    public void onProjectClick(int position, String Project_Name) {
        //projects.get(position);

        //myDBManager_homeScreenFragment.getManagementBoardByIdentifier(projects.get(position) );
        myDBManager_homeScreenFragment.preparationFor_getManagementBoardByIdentifier(Project_Name);

    }


    private void initViews(String userPhone){
        binding.idBtnMyTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectId = String.valueOf(binding.idTvInvisibleProjectId.getText());
                String phoneNumber = userPhone;
                Bundle result = new Bundle();
                result.putString("projectId", projectId);
                result.putString("phoneNumber", phoneNumber);
                getParentFragmentManager().setFragmentResult("dataFromHomeScreenFragment", result);

                NavHostFragment.findNavController(HomeScreenFragment.this)
                        .navigate(R.id.action_HomeScreenFragment_to_MyTasksFragment);
            }
        });
        binding.idBtnTeamChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeScreenFragment.this)
                        .navigate(R.id.action_HomeScreenFragment_to_MyChatsFragment);
            }
        });
        binding.idBtnGeneralChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeScreenFragment.this)
                        .navigate(R.id.action_HomeScreenFragment_to_ChatFragment);
            }
        });
        binding.idBtnWorkSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isVisible = binding.homeScreenLSTProjects.getVisibility();
                if(isVisible == View.INVISIBLE)
                    binding.homeScreenLSTProjects.setVisibility( View.VISIBLE);
                else
                    binding.homeScreenLSTProjects.setVisibility( View.INVISIBLE);
            }
        });
        binding.idBtnMyTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isVisible = binding.homeScreenLSTTeam.getVisibility();
                if(isVisible == View.INVISIBLE)
                    binding.homeScreenLSTTeam.setVisibility( View.VISIBLE);
                else
                    binding.homeScreenLSTTeam.setVisibility( View.INVISIBLE);
            }
        });
        binding.idBtnTeamBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ManagementBoardActivity.class);
                //String userPhone = intent.getStringExtra("uphone");
                String projectId = String.valueOf(binding.idTvInvisibleProjectId.getText());
                intent.putExtra("phoneNumber", userPhone);
                intent.putExtra("projectId", projectId);
                startActivity(intent);
            }
        });

        binding.idBtnHackathonmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivityPanel.class);
                startActivity(intent);
            }
        });
    }
    public void RequestUserByIdentifier(){
        Intent intent = getActivity().getIntent();
        String userPhone = intent.getStringExtra("uphone");
        //myDBManager.getUserByIdentifier(userPhone);
    }
    public void UserNotFound(){
        /*
            Intent addUserIntent = new Intent(getActivity(), AddUserTeamMemberActivity.class);
            startActivity(addUserIntent);
        */
    }

     public void updateUIUser(User user){
        //gradle
         Glide
                 .with(this)
                 .load(user.getAvatar())
                 .into(binding.idImgAvatar);
        //binding.idImgAvatar.setImageResource(R.drawable.img_avatar_itachi);
         if(user.getRule().size() < 2 && user.getRule().get(0) == TEAM_RULE.ACCOMPANY){
             binding.idBtnMyTeams.setVisibility(View.VISIBLE);
         }
     }
     public void updateUITeams(ArrayList<Team> teamsArr){
         Adapter_Team adapter_team = new Adapter_Team(getActivity(), teamsArr, this);
         LinearLayoutManager layoutManager =
                 new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
         binding.homeScreenLSTTeam.setLayoutManager(layoutManager);
         binding.homeScreenLSTTeam.setAdapter(adapter_team);
     }
     public void updateUIProjects(ArrayList<Project> projectsArr, int teamId){
        binding.idTvInvisibleTeamId.setText(String.valueOf(teamId));

         Adapter_Project adapter_project = new Adapter_Project(getActivity(), projectsArr, this);
         LinearLayoutManager layoutManagerProject =
                 new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
         binding.homeScreenLSTProjects.setLayoutManager(layoutManagerProject);
         binding.homeScreenLSTProjects.setAdapter(adapter_project);
     }
     public void updateUIRecentTasks(ArrayList<Task> tasksArr, int projectId){
        binding.idTvInvisibleProjectId.setText(String.valueOf(projectId));

         Adapter_Task adapter_task = new Adapter_Task(getActivity(), tasksArr, this);
         LinearLayoutManager layoutManagerTask
                 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
         binding.firstLSTTask.setLayoutManager(layoutManagerTask);
         binding.firstLSTTask.setAdapter(adapter_task);
     }

CallBack_HomeScreenFragmentProtocol callBack_homeScreenFragmentProtocol = new CallBack_HomeScreenFragmentProtocol() {
    @Override
    public void UpdateUser(User user) {
        updateUIUser(user);
    }

    @Override
    public void UpdateTeams(ArrayList<Team> teams) {
        updateUITeams(teams);
    }

    @Override
    public void UpdateWorkSpace(ArrayList<Project> projects, int teamId) {
        updateUIProjects(projects, teamId);
    }

    @Override
    public void UpdateRecentTask(ArrayList<Task> tasks, int projectId) {
        updateUIRecentTasks(tasks, projectId);
    }
};

    @Override
    public void onTaskClick(int position, String taskId) {
        myDBManager_homeScreenFragment.UpgradeTaskStatus(taskId);
        Log.d("pttt", "onTaskEditClick:  *HomeScreenFrag");
    }



/*
    private void runUi(){
        teams.add(new Team()
                .withTeamId()
                .withTeamName("Choko"));
        teams.add(new Team()
                .withTeamId()
                .withTeamName("Moko"));

        //ArrayList<Team> userTeamList = new ArrayList<>();
        ArrayList l = myDBManager.getUserTeams();

        Adapter_Team adapter_team = new Adapter_Team(getActivity(), teams, this);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
        binding.homeScreenLSTTeam.setLayoutManager(layoutManager);
        binding.homeScreenLSTTeam.setAdapter(adapter_team);
    }
    */
}