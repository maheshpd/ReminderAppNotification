package com.createsapp.alarmmanagernotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.createsapp.alarmmanagernotification.adapter.EventAdapter;
import com.createsapp.alarmmanagernotification.database.DatabaseClass;
import com.createsapp.alarmmanagernotification.database.EntityClass;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button createEvent;
    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createEvent = findViewById(R.id.btn_createEvent);
        recyclerView = findViewById(R.id.recyclerview);
        createEvent.setOnClickListener(this);
        databaseClass = DatabaseClass.getDatabase(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        if (v == createEvent) {
            goToCreateEventActivity();
        }
    }

    private void goToCreateEventActivity() {
        Intent intent = new Intent(getApplicationContext(), CreateEvent.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    private void setAdapter() {
        List<EntityClass> classList = databaseClass.eventDao().getAllData();
        eventAdapter = new EventAdapter(getApplicationContext(),classList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();
    }
}