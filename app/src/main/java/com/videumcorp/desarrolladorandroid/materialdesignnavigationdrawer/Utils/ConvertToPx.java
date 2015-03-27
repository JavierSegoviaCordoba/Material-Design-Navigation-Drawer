package com.videumcorp.desarrolladorandroid.materialdesignnavigationdrawer.Utils;

import android.app.Activity;

public class ConvertToPx {

    public int convertToPx (int dp, Activity activity) {
        // Get the screen's density scale
        final float scale = activity.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }

}
