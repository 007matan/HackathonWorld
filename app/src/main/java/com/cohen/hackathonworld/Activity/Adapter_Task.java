package com.cohen.hackathonworld.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cohen.hackathonworld.Model.Task;
import com.cohen.hackathonworld.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Adapter_Task extends RecyclerView.Adapter<Adapter_Task.TaskViewHolder> {
    private Context context;
    private ArrayList<Task> tasks;

    private TaskViewHolder.OnTaskListener mOnTaskListener;

    public Adapter_Task(Context context, ArrayList<Task> tasks, TaskViewHolder.OnTaskListener mOnTaskListener) {
        this.context = context;
        this.tasks = tasks;
        this.mOnTaskListener = mOnTaskListener;
    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task, parent, false);
        TaskViewHolder myTaskViewHolder = new TaskViewHolder(view, mOnTaskListener);
        return myTaskViewHolder;
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, final int position) {

        Task task = tasks.get(position);

        Glide
                .with(context)
                .load("https://content.hy-vee.com/remote.axd/3f4c2184e060ce99111b-f8c0985c8cb63a71df5cb7fd729edcab.ssl.cf2.rackcdn.com/media/19211/sunnysideupeggs.jpg?v=1&mode=crop&width=800&height=640&upscale=false")
                .into(holder.id_task_assign_st_user);
        Glide
                .with(context)
                .load("https://static.wikia.nocookie.net/villains/images/f/f2/Poseidon_-_Ascension_textless.jpg/revision/latest/scale-to-width-down/350?cb=20200707190123")
                .into(holder.id_task_assign_nd_user);
        Glide
                .with(context)
                .load("https://resizing.flixster.com/8o72A794GQSaJwxQCIFEXdVf070=/218x280/v2/https://flxt.tmsimg.com/assets/258_v9_bb.jpg")
                .into(holder.id_task_assign_rd_user);

        holder.id_task_status.setText(task.getTaskType().name());
        holder.id_user_avatar.setImageResource(R.drawable.ic_logo);
        holder.id_task_name.setText(task.getTaskName());
        holder.id_task_desc.setText(task.getTaskDescription());

        holder.id_task_invisible_taskid.setText(String.valueOf(task.getTaskId()));
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MaterialButton id_task_status;
        private ImageView id_user_avatar;
        private MaterialTextView id_task_name;
        private MaterialTextView id_task_desc;
        private ShapeableImageView id_task_assign_st_user;
        private ShapeableImageView id_task_assign_nd_user;
        private ShapeableImageView id_task_assign_rd_user;

        private MaterialTextView id_task_invisible_taskid;

        private AppCompatImageButton id_task_nextStatus;

        protected OnTaskListener onTaskListener;

        public TaskViewHolder(View itemView, OnTaskListener onTaskListener) {
            super(itemView);
            id_task_status = itemView.findViewById(R.id.id_task_status);
            id_user_avatar = itemView.findViewById(R.id.id_user_avatar);
            id_task_name = itemView.findViewById(R.id.id_task_name);
            id_task_desc = itemView.findViewById(R.id.id_task_desc);
            id_task_assign_st_user = itemView.findViewById(R.id.id_task_assign_st_user);
            id_task_assign_nd_user = itemView.findViewById(R.id.id_task_assign_nd_user);
            id_task_assign_rd_user = itemView.findViewById(R.id.id_task_assign_rd_user);

            id_task_invisible_taskid = itemView.findViewById((R.id.id_task_invisible_taskid));
            id_task_nextStatus = itemView.findViewById((R.id.id_task_nextStatus));

            this.onTaskListener = onTaskListener;
            id_task_nextStatus.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTaskListener.onTaskClick(getAdapterPosition(), id_task_invisible_taskid.getText().toString());
        }

        public interface OnTaskListener{

            void onTaskClick(int position, String taskId);
        }
    }
}
