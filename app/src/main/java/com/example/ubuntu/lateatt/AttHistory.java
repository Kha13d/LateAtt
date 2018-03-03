package com.example.ubuntu.lateatt;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by zorom on 3/3/2018.
 */

public class AttHistory extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        myDB = new DatabaseHelper(this);
        ListHistory();
    }

    private void ListHistory(){
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> TheAtt = new ArrayList<>();
        Cursor data = myDB.getTable();
        if (data.getCount() == 0){ Toast.makeText(getApplicationContext(),"The history is empty!",Toast.LENGTH_SHORT).show();}
        else {
            while (data.moveToNext()){
                TheAtt.add(data.getString(0 )+"- "+data.getString(1 )+" "+data.getString(2 )+" "+data.getString(3 )+"\n"+"Delay: "+data.getString(4 ).split(":")[0]+" Days "+data.getString(4 ).split(":")[1]+" Hours "+data.getString(4 ).split(":")[2]+" Mins");
            }
        }
        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,TheAtt);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String test = adapterView.getItemAtPosition(i).toString().split("-")[0];
                myDB.deleteDelay(test);
                Toast.makeText(getApplicationContext(),"Deleted!",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());

                // Find Another method to refresh
                // AlertDialog
            }
        });
    }

}
