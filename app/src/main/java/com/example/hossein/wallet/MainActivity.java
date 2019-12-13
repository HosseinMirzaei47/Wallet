package com.example.hossein.wallet;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "jalil";

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> scores = new ArrayList<>();

    private android.support.v7.widget.Toolbar toolbar;
    private EditText name;
    private EditText score;
    private Button add;
    private DrawerLayout drawerLayout;

    private MyDataBase myDataBase = new MyDataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setNavigationViewListener();
        setSupportActionBar(toolbar);

        drawerButtonRotation();

        setOnClicks();

    }

    private void setOnClicks() {

        addToList();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int pressed = menuItem.getItemId();

        if (pressed == R.id.clearData) {
            clearDataIfConfirmed();
        } else if (pressed == R.id.chart) {
            openChartFragment();
        } else if (pressed == R.id.viewEntries) {

        } else if (pressed == R.id.exit) {
            this.finishAffinity();
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }

    private void clearDataIfConfirmed() {

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("All data will be deleted! Are you sure?")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDatabase();
                dialog.dismiss();
            }
        });

    }

    private void setNavigationViewListener() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

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

        names.clear();
        scores.clear();

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

    private void addToList() {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().length() != 0 && score.getText().toString().trim().length() != 0) {
                    myDataBase.addData(name.getText().toString(), Integer.parseInt(score.getText().toString()));
                    /*names.add(name.getText().toString());
                    scores.add(Integer.parseInt(score.getText().toString()));*/
                }
                name.setText("");
                name.setHint("Name");
                score.setText("");
                score.setHint("Score");
            }
        });

    }

    private void clearDatabase() {

        myDataBase.clearTable();
        names.clear();
        scores.clear();

    }

    private void drawerButtonRotation() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void message(String message) {
        Log.i(TAG, message);
    }

    private void findViews() {

        add = (Button) findViewById(R.id.addButton);
        name = (EditText) findViewById(R.id.name);
        score = (EditText) findViewById(R.id.score);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

    }

}
