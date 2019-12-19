package com.example.roomforhomeworks.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomforhomeworks.R;
import com.example.roomforhomeworks.adapter.RVAdapterFrag;
import com.example.roomforhomeworks.database.HomeworkEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentNewHomework extends Fragment implements RVAdapterFrag.FragmentDelegate {

    @BindView(R.id.bt_cancel_frag)
    Button cancel;
    @BindView(R.id.bt_confirm_frag)
    Button confirm;
    @BindView(R.id.rv_week_frag)
    RecyclerView weekRV;
    @BindView(R.id.rv_day_frag)
    RecyclerView dayRV;
    @BindView(R.id.homework_description_frag)
    EditText homework_description;

    int weekNum = -1;
    double dayNum = -1;

    ToastDelegate toastDelegate;

    interface ToastDelegate{
        void showToast(int n);
        void addHomework(HomeworkEntity hw);
    }

    FragmentNewHomework(ToastDelegate toastDelegate){
        this.toastDelegate = toastDelegate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Weekend"};
        String[] weeks = new String[1000];
        for(int i = 0; i<weeks.length;i++){
            weeks[i]=""+(i+1);
        }
        dayRV.setLayoutManager(new LinearLayoutManager(this.getContext()));
        dayRV.setAdapter(new RVAdapterFrag(days,this));
        weekRV.setLayoutManager(new LinearLayoutManager(this.getContext()));
        weekRV.setAdapter(new RVAdapterFrag(weeks,this));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:
                if(weekNum<0){
                    toastDelegate.showToast(0);
                }else if(dayNum<0){
                    toastDelegate.showToast(1);
                }else if(homework_description.getText().toString().equals("")){
                    toastDelegate.showToast(2);
                }else{
                    HomeworkEntity hw = new HomeworkEntity(homework_description.getText().toString(),
                            weekNum+dayNum/10.0,
                            false);
                    toastDelegate.addHomework(hw);
                    getActivity().getSupportFragmentManager().popBackStack();
                    homework_description.setText("");
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void getPosition(String value, int position) {
        //TODO: fix this
        try{
            Integer.parseInt(value);
            weekNum = position;
        }catch(NumberFormatException e){
            dayNum = position;
        }
    }
}
