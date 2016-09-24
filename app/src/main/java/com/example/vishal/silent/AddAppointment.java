package com.example.vishal.silent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TimePicker;

public class AddAppointment extends AppCompatActivity {
    public Appointment appointment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        EditText appName = (EditText) findViewById(R.id.nameAppointment);
        TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePickerStart);
        TimePicker timePicker2 = (TimePicker) findViewById(R.id.timePickerEnd);
        appointment1 = new Appointment(appName.getText().toString(), timePicker1.getHour(),
                timePicker1.getMinute(), timePicker2.getHour(), timePicker2.getMinute(),7);

    }

    public Appointment getAppointment() {
        return appointment1;
    }

}
