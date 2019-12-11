package com.example.hossein.wallet;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "jalil";

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> scores = new ArrayList<>();

    private EditText name;
    private EditText score;
    private Button add;
    private Button showChart;
    private Button clearDataButton;

    private MyDataBase myDataBase = new MyDataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        setOnClicks();

    }

    private void setOnClicks() {

        addToList();

        openChartFragment();

        clearDatabase();

    }

    private void clearDatabase() {

        clearDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDataBase.clearTable();
            }
        });
        names.clear();
        scores.clear();

    }

    private void addToList() {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().length() != 0 && score.getText().toString().trim().length() != 0) {
                    myDataBase.addData(name.getText().toString(), Integer.parseInt(score.getText().toString()));
                    names.add(name.getText().toString());
                    scores.add(Integer.parseInt(score.getText().toString()));
                }
            }
        });

    }

    private void getDataFromDatabase() {

        Cursor cursor = myDataBase.getData();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                names.add(cursor.getString(cursor.getColumnIndex("Name")));
                scores.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Score"))));
            }

        }

    }

    private void openChartFragment() {

        showChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDataFromDatabase();

                MonthFragment monthFragment = new MonthFragment();

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("names", names);
                bundle.putIntegerArrayList("scores", scores);
                monthFragment.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, monthFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

    }

    private void message(String message) {
        Log.i(TAG, message);
    }

    private void findViews() {

        add = (Button) findViewById(R.id.addButton);
        showChart = (Button) findViewById(R.id.showChart);
        clearDataButton = (Button) findViewById(R.id.clearData);
        name = (EditText) findViewById(R.id.name);
        score = (EditText) findViewById(R.id.score);

    }

}
