package com.example.vishal.silent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class SilenceEvent {
    AlarmManager am;
    Calendar muteTime, unmuteTime;
    Context context;
    Appointment appointment;
    boolean fullSilence;

    public SilenceEvent(Context context,Appointment appointment){
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        muteTime = Calendar.getInstance();
        unmuteTime = Calendar.getInstance();
        this.appointment = appointment;
        this.context = context;
        fullSilence = true;
    }
    public void setMuteTime(){
        muteTime.set(Calendar.DAY_OF_WEEK, appointment.getDaysOfTheWeek());
        muteTime.setTimeInMillis(System.currentTimeMillis());
        muteTime.set(Calendar.HOUR_OF_DAY, appointment.getStartTimeHour());
        muteTime.set(Calendar.MINUTE, appointment.getStartTimeMinute());
        muteTime.set(Calendar.SECOND,0);
        Intent myIntent;
        if(fullSilence) {
            myIntent = new Intent(context, SilenceBR.class);
        }
        else{
            myIntent = new Intent(context, VibrateBR.class);
        }
        am.setExact(AlarmManager.RTC_WAKEUP, muteTime.getTimeInMillis() , PendingIntent.getBroadcast(context, 0, myIntent,PendingIntent.FLAG_UPDATE_CURRENT));

    }

    public void setUnmuteTime(){
        unmuteTime.set(Calendar.DAY_OF_WEEK, appointment.getDaysOfTheWeek());
        unmuteTime.setTimeInMillis(System.currentTimeMillis());
        unmuteTime.set(Calendar.HOUR_OF_DAY, appointment.getEndTimeHour());
        unmuteTime.set(Calendar.MINUTE, appointment.getEndTimeMinute());
        unmuteTime.set(Calendar.SECOND,0);
        Intent myIntent = new Intent(context, UnmuteBR.class);
        am.setExact(AlarmManager.RTC_WAKEUP, unmuteTime.getTimeInMillis() ,PendingIntent.getBroadcast(context, 0, myIntent,PendingIntent.FLAG_UPDATE_CURRENT));
    }


}