package com.example.roomforhomeworks.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomforhomeworks.R;
import com.example.roomforhomeworks.database.HomeworkEntity;

import java.text.NumberFormat;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    List<HomeworkEntity> homeworkEntities;
    HomeworkDelegate homeworkDelegate;

    public interface HomeworkDelegate{
        void doSomething(HomeworkEntity homework);
        Context getContext();
    }

    public RVAdapter(List<HomeworkEntity> homeworkEntities, HomeworkDelegate homeworkDelegate) {
        this.homeworkEntities = homeworkEntities;
        this.homeworkDelegate = homeworkDelegate;
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_homework_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, final int position) {
        final HomeworkEntity homeworkEntity = homeworkEntities.get(position);
        holder.tv_title.setText(homeworkEntities.get(position).getHomeworkDescription());
        holder.tv_date.setText(homeworkEntities.get(position).dateToString());
        holder.tv_completed.setText(homeworkEntities.get(position).completedToString());
        boolean completed = homeworkEntity.isHomework_complete();
        if(completed) {
            holder.tv_completed.setTextColor(Color.rgb(0,180,160));
        }else{
            holder.tv_completed.setTextColor(Color.RED);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG_X", "onClick: ");
                homeworkDelegate.doSomething(homeworkEntities.get(position));
            }
        });
        //TODO: Add onSwipe click listener
//        if(position==0) {
//            holder.itemView.setOnTouchListener(new SwipeListener(((MainActivity) homeworkDelegate).getContext()) {
//                @Override
//                public void onSwipeRight() {
//                    Log.d("TAG_X", "onSwipeRight: ");
//                    homeworkDelegate.doSomething(position);
//                }
//
//                @Override
//                public void onSwipeLeft() {
//                    Log.d("TAG_X", "onSwipeLeft: ");
//                    homeworkDelegate.doSomething(position);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return homeworkEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_date;
        TextView tv_completed;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title_rvl);
            tv_completed = itemView.findViewById(R.id.tv_price_rvl);
            tv_date = itemView.findViewById(R.id.tv_date_rvl);
        }
    }
}
