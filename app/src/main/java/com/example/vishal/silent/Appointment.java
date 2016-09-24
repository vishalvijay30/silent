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
            private int startTimeHour; 
            private int startTimeMinute; 
            private int endTimeHour; 
            private int endTimeMinute; 
            private int daysOfTheWeek; 
            ArrayList<Appointment> eventCollection = new ArrayList<Appointment>();  
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

            private void addToCollection(Appointment e) { 
                eventCollection.add(e); 
            }  
            public void removeEvent(Appointment e) { 
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
