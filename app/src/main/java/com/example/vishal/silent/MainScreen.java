package com.example.vishal.silent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainScreen extends AppCompatActivity {

    public Button add;
    public Button calendar;
    public Button more;

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

    public void loadMoreActivity() {
        more = (Button)findViewById(R.id.moreButton);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadMore = new Intent(MainScreen.this, MoreScreen.class);
                startActivity(loadMore);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        loadAddAppointmentActivity();
        loadCalendarActivity();
        loadMoreActivity();
    }

    /*
    public void addButtonOnClick(View v) {
        Button button = (Button) v;
        ((Button)v).setText("clicked");
    }
     */

}
