package Utilities;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by nsolanki on 9/5/2016.
 */
public class GoogleAnalyticsTrackingUtility {
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    public static void generateGAEvent(Context context, String eventAction) {
        try {
            start(context);
            tracker.send(new HitBuilders.EventBuilder().setCategory(ApplicationConfiguration.getGACategory()).setAction(eventAction).setLabel("").build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// End of the generateGAEvent


    public static void generateGAEvent(Context context, String eventLabel, String actionDetails) {
        if (ApplicationConfiguration.isGoogleEventsApplicable()) {
            try {
                start(context);
                tracker.send(new HitBuilders.EventBuilder().setCategory(ApplicationConfiguration.getGACategory()).setAction(eventLabel).setLabel(actionDetails).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }// End of the generateGAEvent

    public static void generateGAEvent(Context context, String eventAction, String labelValue, Long value) {
        if (ApplicationConfiguration.isGoogleEventsApplicable()) {
            try {
                start(context);
                tracker.send(new HitBuilders.EventBuilder().setCategory(ApplicationConfiguration.getGACategory()).setAction(eventAction).setValue(value) //
                        .setLabel(labelValue).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }// End of the generateGAEvent

    public static void generateGATiming(Context context, String eventAction, String timingName, Long time) {
        if (ApplicationConfiguration.isGoogleEventsApplicable()) {
            try {
                start(context);
                tracker.send(new HitBuilders.TimingBuilder().setCategory(ApplicationConfiguration.getGACategory()).setValue(time).setVariable(timingName).setLabel(eventAction).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }// End of the generateGATiming

    public static void generateGATiming(long time, String strLabelName, String strLableValue) {
        //TODO:SUMIT-EXISTING-CAO-APP-CHANGES
//		if (Cheapoair.gAnalytics_report_events) {
//			if (!Cheapoair.test_events)
//				EasyTracker.getTracker().sendTiming(AnalyticsTrackingUtility.TIME_CATEGORY_ANDROID, time, strLabelName, strLableValue);
//			else
//				EasyTracker.getTracker().sendTiming(AnalyticsTrackingUtility.TIME_CATEGORY_TEST_ANDROID,  time, strLabelName, strLableValue);
//		}
    }

    public static void setGACampaign(Context context, String campaignData) {

        tracker.send(new HitBuilders.ScreenViewBuilder()
                .setCampaignParamsFromUrl(campaignData)
                .build()
        );
    }

    public static void setGACampaignReferer(Context context, String reffer) {
        //Code need to be written'

    }

    public static void start(Context context) {
        if (analytics == null) {
            analytics = GoogleAnalytics.getInstance(context);
            analytics.setLocalDispatchPeriod(1800);
        }
        if (tracker == null) {
            tracker = analytics.newTracker(ApplicationConfiguration.getGoogleAnalyticID());
            tracker.enableExceptionReporting(true);
            //tracker.enableAdvertisingIdCollection(true);
            tracker.enableAutoActivityTracking(true);
        }
    }

}
