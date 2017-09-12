package com.alexpersaud.timestables;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int timesTableMax = 20;
    ListView timesTableListView;
    SeekBar timesTableSeekBar;

    public void generateTimesTable(int timesTable){
        ArrayList<String> timesTableContent = new ArrayList<String>();
        for(int i=1; i<=20; i++){
            timesTableContent.add(timesTable + " x " + i + " = " + Integer.toString(i * timesTable));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTableContent);
        timesTableListView.setAdapter(arrayAdapter);

    }

    public void changeMaxValue(View view){

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        EditText newMax = (EditText) findViewById(R.id.maxValue);
        int newMaxInt = Integer.parseInt(newMax.getText().toString());
        if(newMaxInt > 0 && newMaxInt <= 1000000 ) {
            timesTableMax = newMaxInt;
            timesTableSeekBar.setMax(timesTableMax);
        } else {
            Toast.makeText(this, "Please enter a positive number between 0 and 1,000,000.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timesTableSeekBar = (SeekBar) findViewById(R.id.timesTableSeekBar);
        timesTableListView = (ListView) findViewById(R.id.timesTableListView);

        timesTableSeekBar.setMax(timesTableMax);
        timesTableSeekBar.setProgress(10);

        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesTable;
                if(progress < min){
                    timesTable = min;
                    timesTableSeekBar.setProgress(min);
                }else{
                    timesTable = progress;
                }
                generateTimesTable(timesTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        generateTimesTable(10);
    }
}
