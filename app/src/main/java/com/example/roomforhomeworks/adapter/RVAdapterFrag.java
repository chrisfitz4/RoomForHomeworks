package com.example.roomforhomeworks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomforhomeworks.R;

import java.util.List;

public class RVAdapterFrag extends RecyclerView.Adapter<RVAdapterFrag.ViewHolder> {


    public interface FragmentDelegate{
        void getPosition(String value, int position);
    }

    String[] values;
    FragmentDelegate delegate;

    public RVAdapterFrag(String[] values, FragmentDelegate adapterFrag) {
        this.values = values;
        this.delegate = adapterFrag;
    }

    @NonNull
    @Override
    public RVAdapterFrag.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_textview,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVAdapterFrag.ViewHolder holder, final int position) {
        holder.textView.setText(values[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.getPosition(values[position], position);
                holder.itemView.setBackgroundColor(R.color.darkblue);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_day_week);
        }
    }
}
