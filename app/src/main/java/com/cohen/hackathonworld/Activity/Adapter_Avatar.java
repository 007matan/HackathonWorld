package com.cohen.hackathonworld.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cohen.hackathonworld.Model.TeamMember;
import com.cohen.hackathonworld.R;

import java.util.ArrayList;

public class Adapter_Avatar extends RecyclerView.Adapter<Adapter_Avatar.AvatarViewHolder> {

    private Context context;
    private ArrayList<TeamMember> teamMembers;
    private AvatarViewHolder.OnAvatarListener mOnAvatarListener;

    public Adapter_Avatar(Context context, ArrayList<TeamMember> teamMembers, AvatarViewHolder.OnAvatarListener mOnAvatarListener){
        this.context = context;
        this.teamMembers = teamMembers;
        this.mOnAvatarListener = mOnAvatarListener;
    }

    @Override
    public int getItemCount() {
        return teamMembers == null ? 0 : teamMembers.size();
    }

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_avatar, parent, false);
        AvatarViewHolder myAvatarViewHolder = new AvatarViewHolder(view, mOnAvatarListener);
        return myAvatarViewHolder;
    }

    @Override
    public void onBindViewHolder(final AvatarViewHolder holder, final int position){
        TeamMember teamMember = teamMembers.get(position);
        Glide
                .with(context)
                .load(teamMember.getAvatar())
                .into(holder.id_imgbtn_avatar);

        //holder.id_imgbtn_avatar.setImageResource(R.drawable.img_avatar_itachi);
        holder.id_tv_name.setText(teamMember.fullName);
        holder.id_tv_user_ID.setText(""+teamMember.userId);
    }

    static class AvatarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatImageButton id_imgbtn_avatar;
        private AppCompatTextView id_tv_name;
        private AppCompatTextView id_tv_user_ID;

        protected Adapter_Avatar.AvatarViewHolder.OnAvatarListener onAvatarListener;

        public AvatarViewHolder(View itemView, Adapter_Avatar.AvatarViewHolder.OnAvatarListener onAvatarListener) {
            super(itemView);

            id_imgbtn_avatar = itemView.findViewById(R.id.id_imgbtn_avatar);
            id_tv_name = itemView.findViewById(R.id.id_tv_name);
            id_tv_user_ID = itemView.findViewById(R.id.id_tv_userID);

            this.onAvatarListener = onAvatarListener;

            id_imgbtn_avatar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onAvatarListener.onAvatarClick(getAdapterPosition(), id_tv_user_ID.getText().toString());

        }

        public interface OnAvatarListener{
            void onAvatarClick(int position, String s);
        }


    }
}
