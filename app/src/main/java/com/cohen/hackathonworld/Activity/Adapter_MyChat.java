package com.cohen.hackathonworld.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.cohen.hackathonworld.Model.Chat;
import com.cohen.hackathonworld.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Adapter_MyChat extends RecyclerView.Adapter<Adapter_MyChat.ChatViewHolder> {
    private Context context;
    private ArrayList<Chat> chats;
    private ChatViewHolder.OnMyChatListener mOnMyChatListener;

    public Adapter_MyChat(Context context, ArrayList<Chat> chats, ChatViewHolder.OnMyChatListener onMyChatListener) {
        this.context = context;
        this.chats = chats;
        this.mOnMyChatListener = onMyChatListener;
    }

    @Override
    public int getItemCount() {
        return chats == null ? 0 : chats.size();
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, parent, false);
        ChatViewHolder myChatViewHolder = new ChatViewHolder(view, mOnMyChatListener);
        return myChatViewHolder;
    }

    @Override
    public void onBindViewHolder(final ChatViewHolder holder, final int position) {

        Chat chat = chats.get(position);

        holder.id_txt_chat_name.setText(chat.getChatName());
        holder.id_txt_chat_type.setText(chat.getChatType().name());
        holder.id_img_chat.setImageResource(R.drawable.img_avatar_itachi);
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView id_img_chat;
        private MaterialTextView id_txt_chat_name;
        private MaterialTextView id_txt_chat_type;
        private AppCompatButton id_btn_press;

        protected OnMyChatListener onMyChatListener;

        public ChatViewHolder(View itemView, OnMyChatListener onMyChatListener) {
            super(itemView);

            id_img_chat = itemView.findViewById(R.id.id_img_chat);
            id_txt_chat_name = itemView.findViewById(R.id.id_txt_chat_name);
            id_txt_chat_type = itemView.findViewById(R.id.id_txt_chat_type);
            id_btn_press = itemView.findViewById(R.id.id_btn_press);

            this.onMyChatListener = onMyChatListener;

            id_btn_press.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMyChatListener.onMyChatClick(getAdapterPosition());
        }

        public interface OnMyChatListener{
            void onMyChatClick(int position);
        }


    }
}
