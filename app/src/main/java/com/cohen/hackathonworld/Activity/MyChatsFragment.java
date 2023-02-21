package com.cohen.hackathonworld.Activity;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cohen.hackathonworld.Model.CHAT_TYPE;
import com.cohen.hackathonworld.Model.Chat;
import com.cohen.hackathonworld.R;
import com.cohen.hackathonworld.databinding.FragmentMyChatsBinding;

import java.util.ArrayList;

public class MyChatsFragment extends Fragment implements Adapter_MyChat.ChatViewHolder.OnMyChatListener {

    private ArrayList<Chat> chats = new ArrayList<>();
    private FragmentMyChatsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMyChatsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chats.add(new Chat()
                .withChatId()
                .withChatName("The Dumbs")
                .withChatType(CHAT_TYPE.FORUM)
                .withChatCreatorId(0)
                .withChatTeamList(3000000)
                .withChatImageUrl("img_avatar"));
        chats.add(new Chat()
                .withChatId()
                .withChatName("The Dumbs")
                .withChatType(CHAT_TYPE.FORUM)
                .withChatCreatorId(0)
                .withChatTeamList(3000000)
                .withChatImageUrl("img_avatar"));
        chats.add(new Chat()
                .withChatId()
                .withChatName("The Dumbs")
                .withChatType(CHAT_TYPE.FORUM)
                .withChatCreatorId(0)
                .withChatTeamList(3000000)
                .withChatImageUrl("img_avatar"));
        chats.add(new Chat()
                .withChatId()
                .withChatName("The Dumbs")
                .withChatType(CHAT_TYPE.FORUM)
                .withChatCreatorId(0)
                .withChatTeamList(3000000)
                .withChatImageUrl("img_avatar"));
        chats.add(new Chat()
                .withChatId()
                .withChatName("The Dumbs")
                .withChatType(CHAT_TYPE.FORUM)
                .withChatCreatorId(0)
                .withChatTeamList(3000000)
                .withChatImageUrl("img_avatar"));
        chats.add(new Chat()
                .withChatId()
                .withChatName("The Dumbs")
                .withChatType(CHAT_TYPE.FORUM)
                .withChatCreatorId(0)
                .withChatTeamList(3000000)
                .withChatImageUrl("img_avatar"));
        chats.add(new Chat()
                .withChatId()
                .withChatName("The Dumbs")
                .withChatType(CHAT_TYPE.FORUM)
                .withChatCreatorId(0)
                .withChatTeamList(3000000)
                .withChatImageUrl("img_avatar"));
        chats.add(new Chat()
                .withChatId()
                .withChatName("The Dumbs")
                .withChatType(CHAT_TYPE.FORUM)
                .withChatCreatorId(0)
                .withChatTeamList(3000000)
                .withChatImageUrl("img_avatar"));

        Adapter_MyChat adapter_My_chat = new Adapter_MyChat(getActivity(), chats, this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.myChatsLSTTask.setLayoutManager(layoutManager);
        binding.myChatsLSTTask.setAdapter(adapter_My_chat);


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

    @Override
    public void onMyChatClick(int position) {
        chats.get(position);
        NavHostFragment.findNavController(MyChatsFragment.this)
                .navigate(R.id.action_MyChatsFragment_to_ChatFragment);

        //add --  sent something
    }
}