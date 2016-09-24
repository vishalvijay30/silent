package com.example.vishal.silent;

import java.util.ArrayList;

/**
 * Created by vishal on 9/24/16.
 */

 /**
  Look into adding a modify event option (allows user to change start or end time) 
 */

public class Appointment {
             private String name; 
            private int startTimeHour;  //hour value of the starting time
            private int startTimeMinute;  //minute value of the starting time
            private int endTimeHour;  //hour value of the ending time
            private int endTimeMinute;  //minute value of the starting time
            private int daysOfTheWeek;  //Day of the week with Sunday = 1 .... Saturday = 7
            //stores collection of Appointments
            ArrayList<Appointment> eventCollection = new ArrayList<Appointment>();  

            //constructor creates a new Appointment object with given attributes
            //An Appointment is an event in the user's schedule set by the user
            public Appointment(String name, int startTimeHour, int startTimeMinute, 
                               String startTimeAMPM, String endTimeAMPM, int endTimeHour, 
                               int endTimeMinute, int daysOfTheWeek) { 
                this.name = name;  
                if (startTimeAMPM.equals("PM")) { 
                    this.startTimeHour = startTimeHour+12; 
                } else { 
                    this.startTimeHour = startTimeHour; 
                }  
                this.startTimeMinute = startTimeMinute;  
                if (endTimeAMPM.equals("PM")) { 
                    this.endTimeHour = endTimeHour+12; 
                }
                else {
                     this.endTimeHour = endTimeHour; 
                }  
                this.endTimeMinute = endTimeMinute; 
                this.daysOfTheWeek = daysOfTheWeek; 
                addToCollection(this); 
            }  

            //helper method to add Appointments to the collection of Appointments
            private void addToCollection(Appointment e) { 
                eventCollection.add(e); 
            }  

            //method allows removal of an existing appointment
            public void removeAppointment(Appointment e) { 
                eventCollection.remove(e); 
            }  

            public String getName() { 
                return name; 
            }  

            public void setName(String name) { 
                this.name = name; 
            }  

            public int getStartTimeHour() { 
                return startTimeHour; 
            }  
            public void setStartTimeHour(int startTimeHour) { 
                this.startTimeHour = startTimeHour; 
            }  

            public int getStartTimeMinute() { 
                return startTimeMinute; 
            }  

            public void setStartTimeMinute(int startTimeMinute) { 
                this.startTimeMinute = startTimeMinute; 
            }  

            public int getEndTimeHour() { 
                return endTimeHour; 
            }  

            public void setEndTimeHour(int endTimeHour) { 
                this.endTimeHour = endTimeHour; 
            }  

            public int getEndTimeMinute() { 
                return endTimeMinute; 
            }  

            public void setEndTimeMinute(int endTimeMinute) { 
                this.endTimeMinute = endTimeMinute; 
            }  

            public int getDaysOfTheWeek() { 
                return daysOfTheWeek; 
            }  

            public void setDaysOfTheWeek(int daysOfTheWeek) { 
                this.daysOfTheWeek = daysOfTheWeek; 
            } 
}
