package com.eliftekin.todolistapp;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.MyViewHolder> {

    ArrayList<data> datalist;

    //constructor
    public rvAdapter(ArrayList<data> datalist) {
        this.datalist = datalist; //adaptörün kullanacağı datalist
    }

    @Override
    public rvAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtask, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(rvAdapter.MyViewHolder holder, int position) {
        data selectedData = datalist.get(position);
        holder.setData(selectedData);

        holder.TaskCheck.setOnCheckedChangeListener(null);
        holder.TaskCheck.setChecked(selectedData.isTaskCompleted());

        holder.TaskCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                selectedData.setcheck(b);

                if(b){
                    data selectedItem = datalist.remove(position);
                    datalist.add(selectedItem);
                }
                else {
                    data selectedItem = datalist.remove(position);
                    datalist.add(0, selectedItem);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (datalist == null) {
            return 0;
        }
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView TaskTitleHere, DueDate;
        CheckBox TaskCheck;

        public MyViewHolder(View itemView) {
            super(itemView);
            TaskTitleHere = itemView.findViewById(R.id.TaskTitleHere);
            DueDate = itemView.findViewById(R.id.DueDateHere);
            TaskCheck = itemView.findViewById(R.id.TaskCheck);
        }

        public void setData(data selectedData) {
            this.TaskTitleHere.setText(selectedData.getTaskTitleHere());
            this.DueDate.setText(selectedData.getDueDateHere());

            if (selectedData.isTaskCompleted()) {
                TaskTitleHere.setPaintFlags(TaskTitleHere.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                TaskTitleHere.setTypeface(null, Typeface.ITALIC);

                DueDate.setTypeface(null, Typeface.ITALIC);

            } else {
                TaskTitleHere.setPaintFlags(TaskTitleHere.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                TaskTitleHere.setTypeface(null, Typeface.NORMAL);
                DueDate.setTypeface(null, Typeface.NORMAL);
            }

        }
    }
}
