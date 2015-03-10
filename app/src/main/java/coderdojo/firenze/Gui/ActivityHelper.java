package coderdojo.firenze.Gui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;


public class ActivityHelper {

    public static void setActionBarColorFormRes(Context context, Activity activity, int color) {
        activity.getActionBar().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(color)));
        activity.getActionBar().setDisplayShowTitleEnabled(false);
        activity.getActionBar().setDisplayShowTitleEnabled(true);
        refreshActionBar(activity);
    }

    public static void setActionBarColor(Context context, Activity activity, int color) {
        activity.getActionBar().setBackgroundDrawable(new ColorDrawable(color));
        refreshActionBar(activity);
    }

    public static void refreshActionBar(Activity activity) {
        activity.getActionBar().setDisplayShowTitleEnabled(false);
        activity.getActionBar().setDisplayShowTitleEnabled(true);
    }

    public static void setActionBarTitle(Activity activity, String barTitle) {
        activity.getActionBar().setTitle(barTitle);
    }

    public static void setActionBarDrawable(Context context, Activity activity, BitmapDrawable background) {
        activity.getActionBar().setBackgroundDrawable(background);
        activity.getActionBar().setDisplayShowTitleEnabled(false);

    }

    public static void setBackgroundColor(Context context, Activity activity, int color) {
        activity.getWindow().getDecorView().setBackground(new ColorDrawable(context.getResources().getColor(color)));
    }
}
