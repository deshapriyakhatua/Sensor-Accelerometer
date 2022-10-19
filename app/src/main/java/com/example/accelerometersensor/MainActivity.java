package com.example.accelerometersensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hiding action bar
        getSupportActionBar().hide();

        // hiding status bar
        WindowCompat.setDecorFitsSystemWindows(getWindow(),false);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager != null){

            Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if(accelerometer != null){

                sensorManager.registerListener(this,accelerometer,sensorManager.SENSOR_DELAY_NORMAL);

            }
            else{
                Toast.makeText(this, "Accelerometer Sensor not working !", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(this, "Sensor not working !", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            TextView textView = findViewById(R.id.textView);
            TextView textView2 = findViewById(R.id.textView2);
            TextView textView3 = findViewById(R.id.textView3);
            TextView textView4 = findViewById(R.id.textView4);

            textView2.setText("x : "+sensorEvent.values[0]);
            textView.setText(" y : "+sensorEvent.values[1]);
            textView3.setText(" z : "+sensorEvent.values[2]);

            int zIndex = (int)sensorEvent.values[2];
            int neg = 9;

            if(zIndex<0){ textView4.setBackgroundColor(Color.BLUE); neg = 9; }
            else{ textView4.setBackgroundColor(Color.WHITE); neg = -9; }

            textView4.setRotationX((float)Math.floor(sensorEvent.values[1]) * neg);
            textView4.setRotationY((float)Math.floor(sensorEvent.values[0]) * neg);


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(this);
        super.onDestroy();
    }
}