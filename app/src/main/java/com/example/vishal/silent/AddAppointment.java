package com.example.vishal.silent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class AddAppointment extends AppCompatActivity {

    public static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    Button btn;
    private int dayOfTheWeek = 7;
    RadioGroup days;
    //RadioButton radioBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        btn = (Button) findViewById(R.id.addButton);
        //radioBtn = (RadioButton) findViewById()
        days = (RadioGroup) findViewById(R.id.days);
        final EditText appName = (EditText) findViewById(R.id.nameAppointment);
        final TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePickerStart);
        final TimePicker timePicker2 = (TimePicker) findViewById(R.id.timePickerEnd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointments.add(new Appointment(appName.getText().toString(), timePicker1.getHour(),
                        timePicker1.getMinute(), timePicker2.getHour(), timePicker2.getMinute(), dayOfTheWeek));

                System.out.println(appointments.size());
                finish();

            }

        });
        days.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonSunday:
                         {
                            setDayOfTheWeek(1);
                            System.out.println("The day of the week is "+dayOfTheWeek);
                            break;
                        }
                    case R.id.radioButtonMonday:
                       {
                            setDayOfTheWeek(2);
                           System.out.println("The day of the week is "+dayOfTheWeek);
                            break;
                        }
                    case R.id.radioButtonTuesday:
                         {
                            setDayOfTheWeek(3);
                             System.out.println("The day of the week is "+dayOfTheWeek);
                            break;
                        }
                    case R.id.radioButtonWednesday:
                       {
                            setDayOfTheWeek(4);
                           System.out.println("The day of the week is "+dayOfTheWeek);
                            break;
                        }
                    case R.id.radioButtonThursday:
                        {
                            setDayOfTheWeek(5);
                            System.out.println("The day of the week is "+dayOfTheWeek);
                            break;
                        }
                    case R.id.radioButtonFriday:
                         {
                            setDayOfTheWeek(6);
                             System.out.println("The day of the week is "+dayOfTheWeek);
                            break;
                        }
                    case R.id.radioButtonSaturday:
                         {
                            setDayOfTheWeek(7);
                             System.out.println("The day of the week is "+dayOfTheWeek);
                            break;
                        }

            }

        }});
        Toast.makeText(this,"Day of the week is "+dayOfTheWeek,Toast.LENGTH_LONG).show();


    }

    public void setDayOfTheWeek(int n) {
        dayOfTheWeek = n;
    }

    public static ArrayList<Appointment> getAppointment() {
        return appointments;
    }



}
