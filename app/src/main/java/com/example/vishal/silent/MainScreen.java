package com.example.vishal.silent;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;



public class MainScreen extends AppCompatActivity implements Runnable{

    public Button add;
    public Button calendar;
    public Button more;
    public Button sync;

    //appointment checker stuff
    public Calendar cal;

    private boolean eventBlock[] = new boolean[7 * 24 * 60];


    public AppointmentChecker appCh;
    Thread thread;

    //ArrayList<Appointment> apps = new ArrayList();

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
                    //new WebReader().execute("http://sampletimetable.blogspot.com/2016/09/blog-post.html");
                    WebReader webReader = new WebReader();
                    webReader.loadEvents("http://sampletimetable.blogspot.com/2016/09/blog-post.html");
                    appointments = webReader.getAppointments();

                    for(int i = 0; i < appointments.size(); i ++)
                    {
                        AddAppointment.appointments.add(appointments.get(i));
                    }

                    //System.out.println
                    Toast.makeText(MainScreen.this, AddAppointment.appointments.size() + " "+appointments.size(),Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
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


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        if(thread == null)
        {
            System.out.println("thread created");
            thread = new Thread(this, "checkerThread");
            thread.start();
        }

        appCh = new AppointmentChecker(AddAppointment.appointments);
        cal = Calendar.getInstance();
    }

    @Override
    public void run() {

        try
        {
            //System.out.println()

            AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

            while(true)
            {

                loadEventsToBoolean(AddAppointment.appointments);
                System.out.println(isBusy());
                if(isBusy())
                {
                    audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }else
                {
                    audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }

                Thread.sleep(60 * 1000);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void resetEventBoolean()
    {
        for(int i = 0; i < eventBlock.length; i ++)
        {
            eventBlock[i] = false;
        }
    }

    public void loadEventsToBoolean(ArrayList<Appointment> appointments)
    {
        int day, fromH, toH, fromM, toM;
        resetEventBoolean();

        for(int i = 0; i < appointments.size(); i ++)
        {
            day = appointments.get(i).getDaysOfTheWeek();
            fromH = appointments.get(i).getStartTimeHour();
            fromM = appointments.get(i).getStartTimeMinute();
            toH = appointments.get(i).getEndTimeHour();
            toM = appointments.get(i).getEndTimeMinute();

            //subtract 1 from day to bring the value of sunday to 0
            int absoluteFromMins = (day - 1) * 24 * 60 + fromH * 60 + fromM;
            int absoluteToMins = (day - 1) * 24 * 60 + toH * 60 + toM;

            for(int j = absoluteFromMins; j < absoluteToMins; j ++)
            {
                eventBlock[j] = true;
            }

        }
    }

    public int dayOfWeek()
    {
        int day;

        cal.setTime(new Date(System.currentTimeMillis()));
        day = cal.get(Calendar.DAY_OF_WEEK);

        return day;
    }

    public int getHour()
    {
        cal.setTime(new Date(System.currentTimeMillis()));
        return cal.get(Calendar.HOUR_OF_DAY);
    }


    public int getMinute()
    {
        cal.setTime(new Date(System.currentTimeMillis()));
        return cal.get(Calendar.MINUTE);
    }

    public boolean isBusy()
    {

        System.out.println("entered is busy");
        int index = (dayOfWeek() - 1) * 24 * 60 + getHour() * 60 + getMinute();

        return eventBlock[index];
    }
    /*
    public void addButt onOnClick(View v) {
        Button button = (Button) v;
        ((Button)v).setText("clicked");
    }
     */

}
