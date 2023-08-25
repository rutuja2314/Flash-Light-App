package com.example.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    public CameraManager cm;
    private String getCamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton tb1 = findViewById((R.id.b1));
        cm = (CameraManager) getSystemService((Context.CAMERA_SERVICE));
        try{
            getCamID = cm.getCameraIdList()[0];
        }
        catch(CameraAccessException e){
            e.printStackTrace();
        }
        tb1.setOnCheckedChangeListener((compoundButton, b) -> switchFlashLight(b));
    }
    public void switchFlashLight(boolean status){
        try{

            if(status) {
                cm.setTorchMode(getCamID,true);
                Toast.makeText(MainActivity.this, "FlashLight is turned ON!!!", Toast.LENGTH_SHORT).show();
            }
            else{
                cm.setTorchMode(getCamID,false);
                Toast.makeText(MainActivity.this, "FlashLight is turned OFF!!!", Toast.LENGTH_SHORT).show();
            }
        }
        catch(CameraAccessException e){
            e.printStackTrace();
        }
    }
}