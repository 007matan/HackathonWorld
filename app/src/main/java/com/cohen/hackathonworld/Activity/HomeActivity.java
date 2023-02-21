package com.cohen.hackathonworld.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.cohen.hackathonworld.R;
import com.cohen.hackathonworld.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration   appBarConfiguration;
    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        Intent intent = getIntent();
        String userPhone = intent.getStringExtra("uphone");
        Log.d("pttt", userPhone+ "Uno");


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int isVisible = binding.idBottomAddLl.getVisibility() ^ View.INVISIBLE;
                //binding.idBottomAddLl.setVisibility( isVisible  );
                int isVisible = binding.idBottomAddLl.getVisibility();
                if(isVisible == View.INVISIBLE)
                    binding.idBottomAddLl.setVisibility( View.VISIBLE);
                else
                    binding.idBottomAddLl.setVisibility( View.INVISIBLE);

                /*
                secondFragment = new SecondFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, secondFragment)
                        .addToBackStack(FirstFragment.class.getSimpleName())
                        .commit();

                 */


            }
        });

        binding.idBtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.putExtra("phoneNumber", getIntent().getStringExtra("uphone"));
                startActivity(intent);
                //finish();
            }
        });

        binding.idBtnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
                intent.putExtra("phoneNumber", getIntent().getStringExtra("uphone"));
                startActivity(intent);
                //finish();
            }
        });
        binding.idBtnAddChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddChatActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



}