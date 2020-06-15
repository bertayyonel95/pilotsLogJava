package com.example.pilotslogjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends Activity {

    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    EditText b4taco;
    EditText aftertaco;
    double b4tacoval = 0.0;
    double aftertacoval = 0.0;
    Date engineStartTime;
    Date takeOffTime;
    Date taxiStartTime;
    Date landingTime;
    Date engineOffTime;
    FileWriter writer;
    File file;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b4taco = findViewById(R.id.b4FlightTacoText);
        aftertaco = findViewById(R.id.afterFlightTacoText);
        file = new File(MainActivity.this.getFilesDir(),"FlightLogs");
        if(!file.exists()) {
            file.mkdir();
        }

        try {
            File gpxfile = new File(file, "sample.txt");
            writer = new FileWriter(gpxfile);
        }
        catch (Exception e) {}
    }
    public void b4tacometerMethod(View view) {
        b4tacoval = Double.parseDouble(b4taco.getText().toString());
        try {
            writer.append(b4taco.getText() + "\n");
            writer.flush();
        }
        catch(Exception e) {}
    }

    public void afterTacometerMethod(View view) {
        aftertacoval = Double.parseDouble(aftertaco.getText().toString());
        try {
            writer.append(aftertaco.getText() + "\n");
            writer.flush();
        }
        catch(Exception e) {}
    }

    public void setTime(View view) {
        String[] tokens;
        switch (view.getId()) {
            case (R.id.engineGoTime):
                engineStartTime = getUTCdatetimeAsDate();
                tokens = engineStartTime.toString().split( " ");
                try {
                    writer.append(tokens[3] + "\n");
                    writer.flush();
                }
                catch(Exception e) {}
                Log.d("setTimeEngineStartTime", tokens[3]);
                break;
            case (R.id.taxiGoTime):
                taxiStartTime = getUTCdatetimeAsDate();
                tokens = taxiStartTime.toString().split( " ");
                try {
                    writer.append(tokens[3] + "\n");
                    writer.flush();
                }
                catch(Exception e) {}
                Log.d("setTimeTaxiStartTime", tokens[3]);
                break;
            case (R.id.takeOffGoTime):
                takeOffTime = getUTCdatetimeAsDate();
                tokens = takeOffTime.toString().split( " ");
                try {
                    writer.append(tokens[3] + "\n");
                    writer.flush();
                }
                catch(Exception e) {}
                Log.d("setTimeTakeOffTime",tokens[3]);
                break;
            case (R.id.landingGoTime):
                landingTime = getUTCdatetimeAsDate();
                tokens = landingTime.toString().split( " ");
                try {
                    writer.append(tokens[3] + "\n");
                    writer.flush();
                }
                catch(Exception e) {}
                Log.d("setTimeLandingTime",tokens[3]);
                break;
            case (R.id.engineOffTime):
                engineOffTime = getUTCdatetimeAsDate();
                tokens = engineOffTime.toString().split( " ");
                try {
                    writer.append(tokens[3] + "\n");
                    writer.flush();
                }
                catch(Exception e) {}
                Log.d("setTimeEngineOffTime", tokens[3]);
                break;
        }
    }

    public static Date getUTCdatetimeAsDate() {
        return stringDateToDate(getUTCdatetimeAsString());
    }

    public static String getUTCdatetimeAsString() {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date());

        return utcTime;
    }

    public static Date stringDateToDate(String StrDate) {
        Date dateToReturn = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try {
            dateToReturn = (Date)dateFormat.parse(StrDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return dateToReturn;
    }
}
