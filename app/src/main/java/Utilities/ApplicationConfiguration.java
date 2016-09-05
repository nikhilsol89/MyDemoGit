package Utilities;

import android.content.Context;

import com.example.nsolanki.myapplication.R;


/**
 * Created by nsolanki on 7/20/2016.
 */
public class ApplicationConfiguration {
    //shared preferemce values and other configuration related stuff//

    //This class is to be used to setting the configuration for the entire application like Dev/Staging mode,
    public static String SharedPreference = "spiceawareAndroid";
    public static String googleAnalyticsID="UA-83724535-1";

    public static final String EVENT_CATEGORY_ANDROID = "Android Event";


    public static String getSharedPreference() {
        return SharedPreference;
    }

    public static boolean isGoogleEventsApplicable() {
        return true;
    }//End of the isGoogleEventsApplicable

    public static String getApplicationName(Context context) {
        try {
            return context.getString(R.string.app_name);
        } catch (Exception e) {
            return "SpiceAware";
        }
    }//end of getApplicationName

    public static String getGACategory() {
        String category = ApplicationConfiguration.EVENT_CATEGORY_ANDROID;
        return category;
    }

    public static String getGoogleAnalyticID() {
        return googleAnalyticsID;
    }//getGoogleAnalyticID

    public static void setGoogleAnalyticID(String analyticsID) {
        googleAnalyticsID = analyticsID;
    }
}
