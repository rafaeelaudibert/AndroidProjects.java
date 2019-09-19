package com.example.rafae.praticaandroid3;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView text_x, text_y, text_z;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_x = (TextView) findViewById(R.id.text_x);
        text_y = (TextView) findViewById(R.id.text_y);
        text_z = (TextView) findViewById(R.id.text_z);

        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);

        // Configure accelerometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer != null)
        {
            sensorManager.registerListener(MainActivity.this, accelerometer,
                    SensorManager.SENSOR_DELAY_GAME);
        }else
        {
            text_x.setText("Accelerometer not supported!");

        }

        // Configure gyroscope
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyroscope != null)
        {
            sensorManager.registerListener(MainActivity.this, gyroscope,
                    SensorManager.SENSOR_DELAY_GAME);
        }else
        {
            text_y.setText("Gyroscope not supported!");
        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            String x = String.format("%.3f",  event.values[0]);
            String y = String.format("%.3f",  event.values[1]);
            String z = String.format("%.3f",  event.values[2]);

            text_x.setText("X: " + x);
            text_y.setText("Y: " + y);
            text_z.setText("Z: " + z);
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            if (Math.abs(event.values[1]) > 4)
            {
                // Call new activity
                startActivity(new Intent(this, GyroscopeActivity.class));
                count++;
                System.out.println(count);
            }
        }
    }
}
