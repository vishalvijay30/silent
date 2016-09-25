package com.example.vishal.silent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainScreen extends AppCompatActivity {

    public Button add;
    public Button calendar;
    public Button more;
    public Button sync;

    public void loadAddAppointmentActivity() {
        add = (Button)findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadAddActivity = new Intent(MainScreen.this, AddAppointment.class);
                startActivity(loadAddActivity);
            }
        });
    }

    public void loadCalendarActivity() {
        calendar = (Button)findViewById(R.id.calendarButton);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadCalendar = new Intent(MainScreen.this, CalendarScreen.class);
                startActivity(loadCalendar);
            }
        });
    }


    public void getSyncData() {
        sync = (Button) findViewById(R.id.syncButton);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Appointment> appointments = new ArrayList<Appointment>();
                try{
                    new WebReader().execute("http://sampletimetable.blogspot.com/2016/09/blog-post.html");
                    WebReader webReader = new WebReader();
                    appointments = webReader.getAppointments();
                    Toast.makeText(MainScreen.this,"Something is happening"+appointments.size(),Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
                for(int i =0;i< appointments.size();i++){
                    SilenceEvent se = new SilenceEvent(MainScreen.this, appointments.get(i));
                    se.setMuteTime();
                    se.setUnmuteTime();
                }

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        loadAddAppointmentActivity();
        loadCalendarActivity();

        getSyncData();
    }

    /*
    public void addButt onOnClick(View v) {
        Button button = (Button) v;
        ((Button)v).setText("clicked");
    }
     */

}
