package com.example.rafae.androidpratica4;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor luminosity, gyroscope, magnetometer, temperature, pressure, accelerometer;
    private GPSTracker GPSTracker;
    private TextView luminosityText, gyroscopeText, magnetometerText, temperatureText, pressureText, coordinatesText, accelerometerText;
    private Button calculatorButton, editTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Initialized");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GPSTracker = new GPSTracker(getApplicationContext());
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);


        luminosityText = (TextView) findViewById(R.id.luminosityText);
        gyroscopeText = (TextView) findViewById(R.id.gyroscopeText);
        magnetometerText = (TextView) findViewById(R.id.magnetometerText);
        temperatureText = (TextView) findViewById(R.id.temperatureText);
        pressureText = (TextView) findViewById(R.id.pressureText);
        coordinatesText = (TextView) findViewById(R.id.coordinatesText);
        accelerometerText = (TextView) findViewById(R.id.accelerometerText);

        editTextButton = (Button) findViewById(R.id.editTextButton);
        calculatorButton = (Button) findViewById(R.id.calculatorButton);

        editTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity.this, EditTextActivity.class));
            }
        });

        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent i = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(i);
            }
        });

        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);

        configureLuminosity();
        configureGyroscope();
        configureMagnetometer();
        configureTemperature();
        configurePressure();
        // configureAccelerometer();

    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("Resumed");
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        switch(sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                luminosityText.setText("Luminosity: " + event.values[0] + "fux");
                break;
            case Sensor.TYPE_GYROSCOPE:
                gyroscopeText.setText(String.format("Gyroscope (rad/s) %.2f %.2f %.2f", event.values[0], event.values[1], event.values[2]));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magnetometerText.setText(String.format("Magnetometer (uT): %.2f %.2f %.2f", event.values[0], event.values[1], event.values[2]));
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                temperatureText.setText("Temperature: " + event.values[0] + "ºC");
                break;
            case Sensor.TYPE_PRESSURE:
                pressureText.setText("Pressure: " + event.values[0] + "hPa");
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accelerometerText.setText(String.format("Accelerometer (m/s²): %.2f %.2f %.2f", event.values[0], event.values[1], event.values[2]));
            default:
                break;
        }

        // Update GPS as well
        updateGPSPosition();
    }

    private void configureLuminosity() {
        // Configure luminosity
        luminosity = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(luminosity != null)
        {
            sensorManager.registerListener(MainActivity.this, luminosity,
                    SensorManager.SENSOR_DELAY_GAME);
        }else
        {
            luminosityText.setText("Luminosity sensor not supported!");
        }
    }

    private void configureGyroscope() {
        // Configure luminosity
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyroscope != null)
        {
            sensorManager.registerListener(MainActivity.this, gyroscope,
                    SensorManager.SENSOR_DELAY_GAME);
        }else
        {
            gyroscopeText.setText("Gyroscope not supported!");
        }
    }

    private void configureMagnetometer() {
        // Configure luminosity
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(magnetometer != null)
        {
            sensorManager.registerListener(MainActivity.this, magnetometer,
                    SensorManager.SENSOR_DELAY_GAME);
        }else
        {
            magnetometerText.setText("Magnetometer not supported!");
        }
    }

    private void configureTemperature() {
        // Configure luminosity
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(temperature != null)
        {
            sensorManager.registerListener(MainActivity.this, temperature,
                    SensorManager.SENSOR_DELAY_GAME);
        }else
        {
            temperatureText.setText("Temperature sensor not supported!");
        }
    }

    private void configurePressure() {
        // Configure luminosity
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(pressure != null)
        {
            sensorManager.registerListener(MainActivity.this, pressure,
                    SensorManager.SENSOR_DELAY_GAME);
        }else
        {
            pressureText.setText("Pressure sensor not supported!");
        }
    }

    private void configureAccelerometer() {
        // Configure luminosity
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer != null)
        {
            sensorManager.registerListener(MainActivity.this, accelerometer,
                    SensorManager.SENSOR_DELAY_GAME);
        }else
        {
            accelerometerText.setText("Accelerometer sensor not supported!");
        }
    }

    private void updateGPSPosition(){
        Location l = GPSTracker.getLocation();

        if(l != null)
        {
            double latitude = l.getLatitude();
            double longitude = l.getLongitude();
            coordinatesText.setText(String.format("Coordinates: %.3fº %.3fº", latitude, longitude));
        } else {
            coordinatesText.setText(String.format("Coordinates: Not found..."));
        }

    }
}
