package com.example.wearostraining;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends WearableActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private TextView mTextView;
    private Sensor mAccelerometer;
    private float sumX = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        mTextView =  findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        mTextView.setText(String.valueOf(accuracy));
    }

    public void onSensorChanged(SensorEvent event)
    {
        float x = event.values[0];
        mTextView =  findViewById(R.id.text);

        sumX = sumX + x;

        mTextView.setText(String.valueOf(sumX*100));

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
