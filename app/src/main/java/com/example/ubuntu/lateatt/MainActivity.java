package com.example.ubuntu.lateatt;

import android.database.Cursor;
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
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database
        myDB = new DatabaseHelper(this);
        /** List
        Spinner DD_DAY = findViewById(R.id.spDAY);
        String[] items_DD_DAY = new String[]{"Sun","Mon","Tue","Wen","Thu"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items_DD_DAY);
        DD_DAY.setAdapter(adapter);


        Spinner spinner = (Spinner) findViewById(R.id.spDAY);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spDAY, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
         **/
    }

    // Get The current time and display it as test !
    public void ButtonClick(View view) throws ParseException {
        /**
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String CDT = sdf.format(new Date());
        Date startDate = sdf.parse("08:30");
        Date endDate = sdf.parse(CDT);

        long difference = endDate.getTime() - startDate.getTime();
        if(difference<0)
        {
            Date dateMax = sdf.parse("24:00");
            Date dateMin = sdf.parse("00:00");
            difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
        }
        int days = (int) (difference / (1000*60*60*7.25));
        int hours = (int) ((difference - (1000*60*60*7.25*days)) / (1000*60*60));
        int min = (int) (difference - (1000*60*60*7.25*days) - (1000*60*60*hours)) / (1000*60);
        textView.setText(CDT);
        textView2.setText("Days: "+ days +" hours: "+ hours + " Min: "+ min);
         **/

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
            if (Added = true) {
                Toast.makeText(getApplicationContext(),Added.toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
            }

            }
        //  Toast.makeText(getApplicationContext(),days+":"+hours+":"+min,Toast.LENGTH_SHORT).show();

    }

    public void ViewHistory() { Fix it
        Cursor res = myDB.getTable();
        int a = res.getCount();
        if (a == 0){Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();}
        else {Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();}
    }

}




