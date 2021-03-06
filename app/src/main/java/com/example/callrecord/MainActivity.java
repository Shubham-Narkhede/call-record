package com.example.callrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;

//public class MainActivity extends AppCompatActivity {
//
//    private static final int REQUEST_CODE = 0;
//    private DevicePolicyManager mDPM;
//    private ComponentName mAdminName;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        try {
//             mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
//            mAdminName = new ComponentName(this, DeviceAdminDemo.class);
//
//            if (!mDPM.isAdminActive(mAdminName)) {
//                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
//                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
//                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Click on Activate button to secure your application.");
//                startActivityForResult(intent, REQUEST_CODE);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (REQUEST_CODE == requestCode) {
//            Intent intent = new Intent(MainActivity.this, TService.class);
//            startService(intent);
//        }
//    }
//}

public class MainActivity extends Activity {
    private static final int REQUEST_SCREENSHOT=59706;
    private MediaProjectionManager mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mgr=(MediaProjectionManager)getSystemService(MEDIA_PROJECTION_SERVICE);

        startActivityForResult(mgr.createScreenCaptureIntent(),
                REQUEST_SCREENSHOT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_SCREENSHOT) {
            if (resultCode==RESULT_OK) {
                Intent i=
                        new Intent(this, ScreenshotService.class)
                                .putExtra(ScreenshotService.EXTRA_RESULT_CODE, resultCode)
                                .putExtra(ScreenshotService.EXTRA_RESULT_INTENT, data);

                startService(i);
            }
        }

        finish();
    }
}