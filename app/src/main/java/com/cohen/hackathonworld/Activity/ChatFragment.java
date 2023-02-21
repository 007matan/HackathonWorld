package com.cohen.hackathonworld.Activity;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cohen.hackathonworld.Model.CHAT_TYPE;
import com.cohen.hackathonworld.Model.Chat;
import com.cohen.hackathonworld.R;
import com.cohen.hackathonworld.databinding.FragmentChatBinding;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentChatBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Adapter_Chat adapter_chat = new Adapter_Chat(getActivity(), chats);
        //LinearLayoutManager layoutManager
        //        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //binding.myChatsLSTTask.setLayoutManager(layoutManager);
        //binding.myChatsLSTTask.setAdapter(adapter_chat);
/*
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}