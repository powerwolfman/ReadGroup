package com.lifucong.readgroup;

import android.content.Intent;

import com.lifucong.apphx.HxBaseApplication;
import com.lifucong.readgroup.presentation.SplashActivity;

/**
 * Created by Administrator on 2016/10/17.
 */

public class ReadGroupApplication extends HxBaseApplication {

    @Override protected void exit() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
