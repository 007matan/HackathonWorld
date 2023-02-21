package com.cohen.hackathonworld.Activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.cohen.hackathonworld.Model.Project;
import com.cohen.hackathonworld.R;

import java.util.ArrayList;

public class Adapter_Project extends RecyclerView.Adapter<Adapter_Project.ProjectViewHolder> {

    private Context context;
    private ArrayList<Project> projects;
    private Adapter_Project.ProjectViewHolder.OnProjectListener mOnProjectListener;

    public Adapter_Project(Context context, ArrayList<Project> projects, Adapter_Project.ProjectViewHolder.OnProjectListener mOnProjectListener){
        this.context = context;
        this.projects = projects;
        this.mOnProjectListener = mOnProjectListener;
    }

    @Override
    public int getItemCount() {
        return projects == null ? 0 : projects.size();
    }

    @Override
    public Adapter_Project.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_project, parent, false);
        Adapter_Project.ProjectViewHolder myProjectViewHolder = new Adapter_Project.ProjectViewHolder(view, mOnProjectListener);
        return myProjectViewHolder;
    }

    @Override
    public void onBindViewHolder(final Adapter_Project.ProjectViewHolder holder, final int position){
        Project project = projects.get(position);

        holder.id_tv_project_name.setText(project.getProjectTitle());
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatTextView id_tv_project_name;
        private Button id_btn_project;

        protected Adapter_Project.ProjectViewHolder.OnProjectListener onProjectListener;

        public ProjectViewHolder(View itemView, Adapter_Project.ProjectViewHolder.OnProjectListener onProjectListener) {
            super(itemView);

            id_tv_project_name = itemView.findViewById(R.id.id_tv_project_name);
            id_btn_project = itemView.findViewById(R.id.id_btn_project);

            this.onProjectListener = onProjectListener;

            id_btn_project.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onProjectListener.onProjectClick(getAdapterPosition(), id_tv_project_name.getText().toString());
        }

        public interface OnProjectListener{
            void onProjectClick(int position, String s);
        }


    }
}