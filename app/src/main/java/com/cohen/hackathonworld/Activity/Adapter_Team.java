package com.cohen.hackathonworld.Activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.cohen.hackathonworld.Model.Team;
import com.cohen.hackathonworld.R;

import java.util.ArrayList;

public class Adapter_Team extends RecyclerView.Adapter<Adapter_Team.TeamViewHolder> {

    private Context context;
    private ArrayList<Team> teams;
    private Adapter_Team.TeamViewHolder.OnTeamListener mOnTeamListener;

    public Adapter_Team(Context context, ArrayList<Team> teams, Adapter_Team.TeamViewHolder.OnTeamListener mOnTeamListener){
        this.context = context;
        this.teams = teams;
        this.mOnTeamListener = mOnTeamListener;
    }

    @Override
    public int getItemCount() {
        return teams == null ? 0 : teams.size();
    }

    @Override
    public Adapter_Team.TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_team, parent, false);
        Adapter_Team.TeamViewHolder myTeamViewHolder = new Adapter_Team.TeamViewHolder(view, mOnTeamListener);
        return myTeamViewHolder;
    }

    @Override
    public void onBindViewHolder(final Adapter_Team.TeamViewHolder holder, final int position){
        Team team = teams.get(position);

        holder.id_tv_team_name.setText(team.getTeamName());
    }

    static class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatTextView id_tv_team_name;
        private Button id_btn_team;

        protected Adapter_Team.TeamViewHolder.OnTeamListener onTeamListener;

        public TeamViewHolder(View itemView, Adapter_Team.TeamViewHolder.OnTeamListener onTeamListener) {
            super(itemView);

            id_tv_team_name = itemView.findViewById(R.id.id_tv_team_name);
            id_btn_team = itemView.findViewById(R.id.id_btn_team);

            this.onTeamListener = onTeamListener;

            id_btn_team.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTeamListener.onTeamClick(getAdapterPosition(), id_tv_team_name.getText().toString());
        }

        public interface OnTeamListener{
            void onTeamClick(int position, String s);
        }


    }
}

