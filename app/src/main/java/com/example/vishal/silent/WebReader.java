package com.example.vishal.silent;

/**
 * Created by vishal on 9/24/16.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class WebReader extends AsyncTask {


    // these objects are temporary. If this app becomes truly large, this has to be altered

    //events are going to be in the form name!day!fromHr!fromMin!toHr!toMin
    //times are going to be
    ArrayList<Appointment> events;

    //this is the table with all cell names
    String table[][] = new String[6][7];
    String starter = "<table border = 1 cellspacing = 1 cellpadding = 5 font-size = 18>";

    public WebReader() {

        events = new ArrayList();

    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            loadEvents(params[0].toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public void setStarter(String s)
    {
        starter = s;
    }

    public void printTable()
    {
        for(int i = 0; i < table.length; i ++)
        {
            for(int j = 0; j < table[i].length; j ++)
            {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }
    public ArrayList<Appointment> getAppointments(){
        return events;
    }

    public void loadEvents(String url) throws Exception
    {
        convertToTable(url);

        for(int i = 0; i < table.length; i ++)
        {
            for(int j = 0; j < table[i].length; j ++)
            {
                if(i > 0 && j > 0)
                {
                    int day = j + 1;
                    int time = i + 8;
                    //events.add(new AppointNet(table[i][j], day, time, 0, time + 1, 0));
                    events.add(new Appointment(table[i][j], time, 0, time+1, 0, day));

                }
            }
        }

    }

    public void convertToTable(String url) throws Exception
    {
        URL loc = new URL(url);
        BufferedReader br = new BufferedReader(new InputStreamReader(loc.openStream()));

        String line;


        //to skip those first few lines
        while((line = br.readLine()) != null && !line.equals(starter))
        {
            //do nothing
        }


        int row = 0;
        int col = 0;

        while((line = br.readLine()) != null && row < 6)
        {

            if(line.length() > 0 && !line.equals("<tr>"))
            {
                //the code to split
                if(line.equals("</tr>"))
                {
                    row ++;
                    col = 0;
                    continue;
                }

                //System.out.println(line);
                String elem[] = line.split(" ");
                String name = elem[1];

                table[row][col] = name;

                col++;


            }

        }


        br.close();

    }

    //these methods are no longer useful
    public void interpret(String s)
    {
        System.out.println(s);
        // the index of the cell in the table, going attached row wise starting from 0
        int tableInd = 0;

        for(int i = 0; i < s.length(); i ++)
        {

            if(s.charAt(i) != ' ')
            {
                tableInd ++;
                Appointment an;
                String name = "";
                while(i < s.length() && s.charAt(i) != ' ')
                {
                    name = name + s.charAt(i);
                    i ++;
                }


                //event = event + "!";

                if(tableInd % 6 != 0 && !(tableInd <= 5) && !name.equals("-"))
                {

                    //System.out.println(name);
                    // to get the day of the week, numbered with Monday = 2
                    int day = tableInd % 6 + 1;
                    // to get the time slot
                    //the times start at 9 AM, thus the "+ 8"
                    int time = tableInd / 7 + 8;

                    an = new Appointment(name, time, 0, time + 1, 0, day);
                    events.add(an);
                }

            }

        }


    }



    public String returnString(String url) throws Exception
    {

        URL loc = new URL(url);
        BufferedReader br = new BufferedReader(new InputStreamReader(loc.openStream()));

        String l;
        while((l = br.readLine()) != null)
        {
            System.out.println(l);
        }

        String line = br.readLine();
        System.out.println(line);
        line = br.readLine();
        System.out.println(line);

        int stInd = line.indexOf("Monday");
        int endInd = line.indexOf("' property='og:description");

        return line.substring(stInd, endInd);

    }


}
