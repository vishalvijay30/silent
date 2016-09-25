package com.example.vishal.silent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class AppointmentChecker {

    public ArrayList<Appointment> events;
    public Calendar cal;

    private boolean eventBlock[] = new boolean[7 * 24 * 60];
    //7 days, 24 hours, checks every minute

    public AppointmentChecker(ArrayList<Appointment> ev)
    {
        events = ev;
        cal = Calendar.getInstance();
        loadEventsToBoolean();
    }

    public void updateAppointments(ArrayList<Appointment> events)
    {
        resetEventBoolean();
        this.events = events;
        loadEventsToBoolean();
    }

    public void resetEventBoolean()
    {
        for(int i = 0; i < eventBlock.length; i ++)
        {
            eventBlock[i] = false;
        }
    }

    public void loadEventsToBoolean()
    {
        int day, fromH, toH, fromM, toM;
        resetEventBoolean();

        for(int i = 0; i < events.size(); i ++)
        {
            day = events.get(i).getDaysOfTheWeek();
            fromH = events.get(i).getStartTimeHour();
            fromM = events.get(i).getStartTimeMinute();
            toH = events.get(i).getEndTimeHour();
            toM = events.get(i).getEndTimeMinute();

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

        int index = (dayOfWeek() - 1) * 24 * 60 + getHour() * 60 + getMinute();

        return eventBlock[index];
    }

    public boolean mockIsBusy(int day, int hour, int min)
    {
        int index = (day - 1) * 24 * 60 + hour * 60 + min;
        return eventBlock[index];
    }

    public boolean mockIsBusy(int ind)
    {
        return eventBlock[ind];
    }

}
