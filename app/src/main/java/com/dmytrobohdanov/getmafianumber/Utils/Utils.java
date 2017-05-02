package com.dmytrobohdanov.getmafianumber.Utils;

import android.app.Activity;
import android.view.WindowManager;


public class Utils {
    /**
     * Keeps screen on, if @param keepScreenOn is true, otherwise allow to turn off screen
     */
    public static void keepScreenOn(Activity activity, boolean keepScreenOn) {
        if (keepScreenOn) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
}
