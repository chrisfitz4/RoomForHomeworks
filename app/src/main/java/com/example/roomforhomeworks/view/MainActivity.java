package com.example.roomforhomeworks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomforhomeworks.R;
import com.example.roomforhomeworks.adapter.RVAdapter;
import com.example.roomforhomeworks.database.HomeworkDatabase;
import com.example.roomforhomeworks.database.HomeworkEntity;
import com.example.roomforhomeworks.presenter.Contract;
import com.example.roomforhomeworks.presenter.HomePresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.HomeworkView, RVAdapter.HomeworkDelegate, FragmentNewHomework.ToastDelegate,FragmentHomeworkViewer.ViewHomeworkDelegate {


    private HomeworkDatabase homeworkDatabase;
    private RecyclerView rv_homeworks;
    private TextView tv_noHomework;
    private TextView newHomework;
    private Contract.HomeworkPresenter presenter;

    FragmentNewHomework fragmentNewHomework = new FragmentNewHomework(this);
    FragmentHomeworkViewer fragmentHomeworkViewer = new FragmentHomeworkViewer(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        presenter = new HomePresenter(this);

//        presenter.insertHomework(new HomeworkEntity("HomeworkDB",3.3,false));
//        presenter.insertHomework(new HomeworkEntity("Taco Bell",3.91,"12/18/2017"));
//        presenter.insertHomework(new HomeworkEntity("Android Studio",100.11,"1/19/2002"));
//        presenter.insertHomework(new HomeworkEntity("Homework Store",1.00,"2/29/2016"));


        rv_homeworks = findViewById(R.id.rv_main);
        tv_noHomework = findViewById(R.id.tv_totalcost_main);
        newHomework = findViewById(R.id.tv_newhomework_main);

        presenter.getHomeworks();
        //presenter.getTotalCost();

//        presenter = new HomePresenter(this);
//        presenter.getHomeworks();
//        presenter.getTotalCost();

        Bundle bundle = new Bundle();
        fragmentNewHomework.setArguments(bundle);

        newHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.frameLayoutAreTheWorst,fragmentNewHomework)
                        .addToBackStack(fragmentNewHomework.getTag())
                        .commit();
            }
        });
    }

    @Override
    public void displayHomeworks(List<HomeworkEntity> homeworks) {

        RVAdapter rvAdapter = new RVAdapter(homeworks,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        rv_homeworks.setLayoutManager(layoutManager);
        rv_homeworks.setAdapter(rvAdapter);
    }

    @Override
    public void homeworksEmpty() {
        tv_noHomework.setText(getString(R.string.info_text,"No saved homeworks."));
    }

    @Override
    public void displayError(String error) {
        tv_noHomework.setText(getString(R.string.info_text,error));
    }

    @Override
    public void displayCost(double totalCost) {
        tv_noHomework.setText(getString(R.string.total_text,totalCost));
    }

    @Override
    public void updateSuccess() {
        Toast.makeText(this,"Update successful",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void insertSuccess() {
        Toast.makeText(this,"Insert successful",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doSomething(HomeworkEntity homework) {
        Bundle bundle = new Bundle();
        Log.d("TAG_X", "doSomething: "+homework.getHomeworkID());
        Log.d("TAG_X", "doSomething: "+homework.getHomeworkTitle());
        Log.d("TAG_X", "doSomething: "+homework.getHomeworkDay());
        Log.d("TAG_X", "doSomething: "+homework.getHomeworkDescription());

        bundle.putInt("key", homework.getHomeworkID());
        bundle.putString("description",homework.getHomeworkDescription());
        bundle.putDouble("date",homework.getHomeworkDay());
        bundle.putBoolean("complete",homework.isHomework_complete());

        fragmentHomeworkViewer.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayoutAreTheWorst,fragmentHomeworkViewer)
                .addToBackStack(fragmentHomeworkViewer.getTag())
                .commit();

    }

    @Override
    public Context getContext() {
        return this.getContext();
    }

    @Override
    public void showToast(int n) {
        switch(n) {
            case 0:
                Toast.makeText(this, "Choose a week", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "Choose a day", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Type a description", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void addHomework(HomeworkEntity hw) {
        presenter.insertHomework(hw);
        presenter.getHomeworks();
    }

    @Override
    public void completed(HomeworkEntity homework) {
        presenter.modifyHomework(homework);
        presenter.getHomeworks();
    }
}
