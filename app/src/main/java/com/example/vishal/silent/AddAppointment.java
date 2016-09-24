package com.example.vishal.silent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddAppointment extends AppCompatActivity {
    public Appointment appointment1;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        btn = (Button) findViewById(R.id.addButton);
        final EditText appName = (EditText) findViewById(R.id.nameAppointment);
        final TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePickerStart);
        final TimePicker timePicker2 = (TimePicker) findViewById(R.id.timePickerEnd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment1 = new Appointment(appName.getText().toString(), timePicker1.getHour(),
                        timePicker1.getMinute(), timePicker2.getHour(), timePicker2.getMinute(),7);
                changeVolume();

            }
        });



    }

    public Appointment getAppointment() {
        return appointment1;
    }
    public void changeVolume(){
        SilenceEvent se = new SilenceEvent(AddAppointment.this, getAppointment());
        se.setMuteTime();
        se.setUnmuteTime();
    }

}
