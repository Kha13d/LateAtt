package com.example.ubuntu.lateatt;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database
        myDB = new DatabaseHelper(this);
        TotalDelay();

        // Add manuel entering data

    }

    public void showHistory(View view){
        Intent intent = new Intent(MainActivity.this,AttHistory.class);
        startActivity(intent);
    }

    // Get The current time and display it as test !
    public void ButtonClick(View view) throws ParseException {
        TextView textView2 = findViewById(R.id.textView2);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        // Get the Current Date and Time
        Date date = new Date();
        String DayofTheWeek = (String) DateFormat.format("EEEE", date);
        String MonthofTheYear = (String) DateFormat.format("MMM", date);
        String Day = (String) DateFormat.format("dd", date);
        String Month = (String) DateFormat.format("MM", date);
        String Year = (String) DateFormat.format("yyyy", date);
        String Hour = (String) DateFormat.format("HH", date);
        String Mins = (String) DateFormat.format("mm", date);

        // Ref Time for sub
        Date Ref = sdf.parse("00:00");
        Date Now = sdf.parse(Hour+":"+Mins);

        // Find the Difference
        long difference = Now.getTime() - Ref.getTime();
        if(difference < 0 ) {
        // Warning below the ref time
            Toast toast = Toast.makeText(getApplicationContext(),"The current time did not exceed 8:30 \n You'r not late",Toast.LENGTH_SHORT);
            TextView TS = (TextView) toast.getView().findViewById(android.R.id.message);
            TS.setGravity(Gravity.CENTER);
            toast.show();
        } else {
        // Conv mSEC to Time
            int d_days = (int) (difference / (1000 * 60 * 60 * 24));
            int d_hours = (int) ((difference - (1000 * 60 * 60 * 24 * d_days)) / (1000 * 60 * 60));
            int d_min = (int) (difference - (1000 * 60 * 60 * 24 * d_days) - (1000 * 60 * 60 * d_hours)) / (1000 * 60);
            Boolean Added = myDB.insertData(DayofTheWeek,Day+" "+MonthofTheYear+" "+Year,Hour+":"+Mins,d_days+":"+d_hours+":"+d_min);
            if (Added = true) { Toast.makeText(getApplicationContext(),"Added!", Toast.LENGTH_SHORT).show(); }
            else { Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();}
            }
        TotalDelay();
    }

    public void TotalDelay(){
        TextView textView = findViewById(R.id.textView);
        Cursor cursor = myDB.getTable();
        ArrayList<String> Delay = new ArrayList<>();
        if (cursor.getCount() == 0){ Toast.makeText(getApplicationContext(),"The history is empty!",Toast.LENGTH_SHORT).show();}
        else {
            while (cursor.moveToNext()){
                Delay.add(cursor.getString(4 ));
            }
        }

        String[] DelayArray = new String[Delay.size()];
        DelayArray = Delay.toArray(DelayArray);
        textView.setText(DelayArray[0]);

        // ADD the total Delay specific form see: https://stackoverflow.com/questions/2709253/converting-a-string-to-an-integer-on-android
    }

}




