package com.android.testskin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
        ImageView ivTest = findViewById(R.id.iv_test);
        TextView tvTest = findViewById(R.id.tv_test);
        LinearLayout llTest = findViewById(R.id.ll_test);
        String filePath = AppConstants.DEFAULT_RESOURCE_PATH;
        Log.d(TAG, "filePath ===" + filePath);
        boolean fileExists = FileUtils.isFileExists(filePath);
        if (fileExists) {
            LoadResourceUtil.getInstance().setLoadResource(filePath);
            Drawable drawable = LoadResourceUtil.getInstance().getDrawable("test_image");
            if (drawable != null) {
                ivTest.setImageDrawable(drawable);
            } else {
                Log.d(TAG, "Drawable is null");
            }
            String test_string = LoadResourceUtil.getInstance().getString("test_string");
            if (test_string != null) {
                tvTest.setText(test_string);
            } else {
                Log.d(TAG, "String is null");
            }
            int test_color = LoadResourceUtil.getInstance().getColor("test_color");
            if (test_color != 0) {
                llTest.setBackgroundColor(test_color);
            } else {
                Log.d(TAG, "Color is null");
            }

        } else {
            Log.d(TAG, "Skin is not exists");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_REQUEST_CODE:
                java.util.Map<String, Integer> perms = new HashMap<>();
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                Boolean storage = perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                if (storage) {
                    // 授予的所有权限
                    Toast.makeText(MainActivity.this, "允许存储权限", Toast.LENGTH_SHORT).show();
                } else {
                    // 权限被拒绝
                    Toast.makeText(MainActivity.this, "存储权限被拒绝", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }

    /**
     * 申请权限
     */
    private void checkPermissions() {
        List<String> permissions = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissions.isEmpty()) {
            String[] params = permissions.toArray(new String[permissions.size()]);
            ActivityCompat.requestPermissions(this, params, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            // else：我们已经有权限了，所以正常处理
        }
    }
}