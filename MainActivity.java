package com.example.tiltdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView x, y, z, bright;
    float brightness = 0;
    WindowManager.LayoutParams brightnessParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, sensorManager.SENSOR_DELAY_NORMAL);

        x = findViewById(R.id.xaxistv);
        y = findViewById(R.id.yaxistv);
        z = findViewById(R.id.zaxistv);
        bright = findViewById(R.id.brightnessid);

        brightnessParam = getWindow().getAttributes();
        bright.setText(brightness+"");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x.setText(sensorEvent.values[0]+"");
        y.setText(sensorEvent.values[1]+"");
        z.setText(sensorEvent.values[2]+"");

        if (brightness<1.0f)
            brightness+=0.01f;
        brightnessParam.screenBrightness = brightness;
        getWindow().setAttributes(brightnessParam);
        bright.setText(brightness+"");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}