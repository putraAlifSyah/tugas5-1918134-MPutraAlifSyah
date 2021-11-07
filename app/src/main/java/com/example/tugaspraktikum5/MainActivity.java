package com.example.tugaspraktikum5;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends Activity {

    TimePicker myTimePicker;
    Button buttonStratSetDialog;
    TextView textAlarmPrompt;

    TimePickerDialog timePickerDialog;
    final static int RQS_1=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAlarmPrompt = (TextView) findViewById(R.id.alarmprompt);
        
        buttonStratSetDialog = (Button)findViewById(R.id.startSetDialog);
        buttonStratSetDialog.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                textAlarmPrompt.setText("");
                openTimePickerDialog(false);

            }
        });
    }

    private void openTimePickerDialog(boolean is24r) {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(MainActivity.this,
                onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true);
                timePickerDialog.setTitle("Set time");

                timePickerDialog.show();
    }
    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();

        calSet.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calSet.set(Calendar.MINUTE,minute);
        calSet.set(Calendar.MILLISECOND,0);
        calSet.set(Calendar.SECOND,0);

        if (calSet.compareTo(calNow)<= 0){
            calSet.add(Calendar.DATE,1);
            Log.i("hasil","=<0");
        }else if(calSet.compareTo(calNow) >0){
            Log.i("hasil","> 0");
        }else{
            Log.i("hasil","> 0");
        }

        setjam(calSet);

        }
    };

    private  void setjam(Calendar targetCal){

        textAlarmPrompt.setText("====\n"+"Alarm Set"+targetCal.getTime()+"\n====");

        Intent intent = new Intent(getBaseContext(), Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(),RQS_1,intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set (AlarmManager.RTC_WAKEUP,targetCal.getTimeInMillis(),pendingIntent);
    }
}