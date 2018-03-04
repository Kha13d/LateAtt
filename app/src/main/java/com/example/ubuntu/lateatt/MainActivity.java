package com.example.ubuntu.lateatt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public void addHistory(View view){
        Intent intent1 = new Intent(MainActivity.this,addHistoty.class);
        startActivity(intent1);
    }

    public void Refresh(View view){
        finish();
        startActivity(getIntent());
    }

    // Get The current time and display it as test !
    public void ButtonClick(View view) throws ParseException {
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
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
        Date Ref = sdf.parse("08:30");
        Date Now = sdf.parse(Hour+":"+Mins);

        // Find the Difference
        long difference = Now.getTime() - Ref.getTime();
        if(difference < 0 ) {
        // Warning below the ref time
            Toast.makeText(getApplicationContext(),"The current time did not exceed 8:30 \n You'r not late",Toast.LENGTH_SHORT).show();
            textView2.setText("No Delay for Today!");
        } else {
        // Conv mSEC to Time
            int d_days = (int) (difference / (1000 * 60 * 60 * 24));
            int d_hours = (int) ((difference - (1000 * 60 * 60 * 24 * d_days)) / (1000 * 60 * 60));
            int d_min = (int) (difference - (1000 * 60 * 60 * 24 * d_days) - (1000 * 60 * 60 * d_hours)) / (1000 * 60);
            Boolean Added = myDB.insertData(DayofTheWeek,Day+" "+MonthofTheYear+" "+Year,Hour+":"+Mins,d_days+":"+d_hours+":"+d_min);
            if (Added = true) { Toast.makeText(getApplicationContext(),"Added!", Toast.LENGTH_SHORT).show();
            textView2.setText("Hours: "+ d_hours+" Mins: "+ d_min);}
            else { Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();}
            }
        TotalDelay();
        String S = SignOutTime(Hour,Mins);
        textView3.setText(S);
    }

    public String SignOutTime(String Hours, String Mins){
        int SignOutTime = (Integer.parseInt(Hours) * 60) + (Integer.parseInt(Mins)) + (435);
        int SignOutTime_H = (int) (SignOutTime/60);
        int SignOutTime_M = (int) (SignOutTime - (SignOutTime_H*60));
        String S;
        if (SignOutTime >= 945){S = "15:45";}
        else if (SignOutTime <= 885){S = "14:45";}
        else {S = SignOutTime_H+":"+SignOutTime_M;}
        return S;
    }

    public void TotalDelay(){
        // Get the Delay Data from the Database
        TextView textView = findViewById(R.id.textView);
        Cursor cursor = myDB.getTable();
        ArrayList<String> Delay = new ArrayList<>();
        if (cursor.getCount() == 0){ Toast.makeText(getApplicationContext(),"The history is empty!",Toast.LENGTH_SHORT).show();}
        else {
            while (cursor.moveToNext()){
                Delay.add(cursor.getString(4 )); // Get the ID's of the Rows
            }
        }
        // Create an Array from A list
        String[] DelayArray = new String[Delay.size()];
        DelayArray = Delay.toArray(DelayArray);
        // Cal. the total dealy from the database
        int Days = 0; int Hours = 0; int Mins=0;
        for (String s : DelayArray){
            Days += Integer.parseInt(s.split(":")[0].toString());
            Hours += Integer.parseInt(s.split(":")[1].toString());
            Mins += Integer.parseInt(s.split(":")[2].toString());
        }

        int TotalDelay = (Days*24*60)+(Hours*60)+(Mins); // in Mins
        int D_Days = (int) (TotalDelay/(60*7.25));
        int D_Hours = (int) ((TotalDelay-(D_Days*60*7.25))/(60));
        int D_Mins = (int) ((TotalDelay-(D_Days*60*7.25)-(D_Hours*60)));

        textView.setText("Days: "+ D_Days+" Hours: "+ D_Hours+" Mins: "+ D_Mins);
        // ADD the total Delay specific form see: https://stackoverflow.com/questions/2709253/converting-a-string-to-an-integer-on-android
    }

}




