package com.example.vishal.silent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.util.ArrayList;

public class AddAppointment extends AppCompatActivity {

    public static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    Button btn;
    private int dayOfTheWeek = 7;
    //RadioButton radioBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        btn = (Button) findViewById(R.id.addButton);
        //radioBtn = (RadioButton) findViewById()
        final EditText appName = (EditText) findViewById(R.id.nameAppointment);
        final TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePickerStart);
        final TimePicker timePicker2 = (TimePicker) findViewById(R.id.timePickerEnd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointments.add(new Appointment(appName.getText().toString(), timePicker1.getHour(),
                        timePicker1.getMinute(), timePicker2.getHour(), timePicker2.getMinute(), dayOfTheWeek));

                System.out.println(appointments.size());

            }

        });


    }

    public void setDayOfTheWeek(int n) {
        dayOfTheWeek = n;
    }

    public static ArrayList<Appointment> getAppointment() {
        return appointments;
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton)v).isChecked();

        switch (v.getId()) {
            case R.id.radioButtonSunday:
                if (checked) {
                    setDayOfTheWeek(1);
                    break;
                }
            case R.id.radioButtonMonday:
                if (checked) {
                    setDayOfTheWeek(2);
                    break;
                }
            case R.id.radioButtonTuesday:
                if (checked) {
                    setDayOfTheWeek(3);
                    break;
                }
            case R.id.radioButtonWednesday:
                if (checked) {
                    setDayOfTheWeek(4);
                    break;
                }
            case R.id.radioButtonThursday:
                if (checked) {
                    setDayOfTheWeek(5);
                    break;
                }
            case R.id.radioButtonFriday:
                if (checked) {
                    setDayOfTheWeek(6);
                    break;
                }
            case R.id.radioButtonSaturday:
                if(checked) {
                    setDayOfTheWeek(7);
                    break;
                }
        }
    }

    /*
    public void changeVolume(){
        SilenceEvent se = new SilenceEvent(AddAppointment.this, getAppointment());
        se.setMuteTime();
        se.setUnmuteTime();
    }
    */

}
