package com.silverhorse.topfivecolors;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.silverhorse.topfivecolors.ui.camera.CameraFragment;
import com.silverhorse.topfivecolors.ui.fivecolors.InfoColorsFragment;

public class MainActivity extends AppCompatActivity {
//    final private String TAG = "MainActivity";

//    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_camera, new CameraFragment())
                .replace(R.id.fragment_info_colors, new InfoColorsFragment())
                .commit();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        CameraFragment fragment = (CameraFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_camera);
        if (fragment != null) {
            fragment.getCameraHelper().onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}