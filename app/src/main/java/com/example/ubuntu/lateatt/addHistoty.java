package com.example.ubuntu.lateatt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ubuntu on 04/03/18.
 */

public class addHistoty extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_history);
        myDB = new DatabaseHelper(this);
        Diff();
    }

    public void Diff(){

        Spinner DAY = (Spinner) findViewById(R.id.spinnerDAY);
        ArrayAdapter<CharSequence> adapterDAY = ArrayAdapter.createFromResource(this,
                R.array.DAY, android.R.layout.simple_spinner_item);
        adapterDAY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DAY.setAdapter(adapterDAY);

        Spinner DATE_DAY = (Spinner) findViewById(R.id.spinnerDATE_DAY);
        ArrayAdapter<CharSequence> adapterDATE_DAY = ArrayAdapter.createFromResource(this,
                R.array.DATE_DAY, android.R.layout.simple_spinner_item);
        adapterDATE_DAY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DATE_DAY.setAdapter(adapterDATE_DAY);

        Spinner DATE_MONTH = (Spinner) findViewById(R.id.spinnerDATE_MONTH);
        ArrayAdapter<CharSequence> adapterDATE_MONTH = ArrayAdapter.createFromResource(this,
                R.array.DATE_MONTH, android.R.layout.simple_spinner_item);
        adapterDATE_MONTH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DATE_MONTH.setAdapter(adapterDATE_MONTH);

        Spinner DATE_YEAR = (Spinner) findViewById(R.id.spinnerDATE_YEAR);
        ArrayAdapter<CharSequence> adapterDATE_YEAR = ArrayAdapter.createFromResource(this,
                R.array.DATE_YEAR, android.R.layout.simple_spinner_item);
        adapterDATE_YEAR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DATE_YEAR.setAdapter(adapterDATE_YEAR);

        Spinner TimeHour = (Spinner) findViewById(R.id.spinnerTime_HOUR);
        ArrayAdapter<CharSequence> adapterTimeHour = ArrayAdapter.createFromResource(this,
                R.array.Time_HOURS, android.R.layout.simple_spinner_item);
        adapterTimeHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TimeHour.setAdapter(adapterTimeHour);

        Spinner TimeMin = (Spinner) findViewById(R.id.spinnerTIME_MIN);
        ArrayAdapter<CharSequence> adapterTimeMin = ArrayAdapter.createFromResource(this,
                R.array.Time_MIN, android.R.layout.simple_spinner_item);
        adapterTimeMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TimeMin.setAdapter(adapterTimeMin);

        Spinner DelayHour = (Spinner) findViewById(R.id.spinnerDELAY_HOUR);
        ArrayAdapter<CharSequence> adapterDelayHour = ArrayAdapter.createFromResource(this,
                R.array.Delay_HOURS, android.R.layout.simple_spinner_item);
        adapterDelayHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DelayHour.setAdapter(adapterDelayHour);

        Spinner DelayMin = (Spinner) findViewById(R.id.spinnerDEALY_MIN);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Delay_MIN, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DelayMin.setAdapter(adapter);

    }

    public void Savebtn(View view){
        TextView textView17 = findViewById(R.id.textView17);
        Spinner DAY = (Spinner) findViewById(R.id.spinnerDAY);
        Spinner DATE_DAY = (Spinner) findViewById(R.id.spinnerDATE_DAY);
        Spinner DATE_MONTH = (Spinner) findViewById(R.id.spinnerDATE_MONTH);
        Spinner DATE_YEAR = (Spinner) findViewById(R.id.spinnerDATE_YEAR);
        Spinner TimeHour = (Spinner) findViewById(R.id.spinnerTime_HOUR);
        Spinner TimeMin = (Spinner) findViewById(R.id.spinnerTIME_MIN);
        Spinner DelayHour = (Spinner) findViewById(R.id.spinnerDELAY_HOUR);
        Spinner DelayMin = (Spinner) findViewById(R.id.spinnerDEALY_MIN);

        Boolean Added = myDB.insertData(String.valueOf(DAY.getSelectedItem()),
                String.valueOf(DATE_DAY.getSelectedItem())+" "+String.valueOf(DATE_MONTH.getSelectedItem())+" "+String.valueOf(DATE_YEAR.getSelectedItem()),
                String.valueOf(TimeHour.getSelectedItem())+":"+String.valueOf(TimeMin.getSelectedItem()),
                "00"+":"+String.valueOf(DelayHour.getSelectedItem())+":"+String.valueOf(DelayMin.getSelectedItem()));
        if (Added = true) { Toast.makeText(getApplicationContext(),"Added!", Toast.LENGTH_SHORT).show();
            textView17.setText(String.valueOf(DAY.getSelectedItem())+" "+
                    String.valueOf(DATE_DAY.getSelectedItem())+" "+String.valueOf(DATE_MONTH.getSelectedItem())+" "+String.valueOf(DATE_YEAR.getSelectedItem())+" "+
                    String.valueOf(TimeHour.getSelectedItem())+":"+String.valueOf(TimeMin.getSelectedItem())+" "+
                    "00"+":"+String.valueOf(DelayHour.getSelectedItem())+":"+String.valueOf(DelayMin.getSelectedItem()));}
        else { Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();}

        //Toast.makeText(this,String.valueOf(DAY.getSelectedItem()),Toast.LENGTH_SHORT).show();
    }



}
