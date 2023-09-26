package com.challengers.of.call;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.annotation.RequiresApi;

public class Util {
    public static JobScheduler jobScheduler;
    // schedule the start of the service every 10 - 30 seconds

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void scheduleJob(Context context,String Loginid,String Contestid) {
        ComponentName serviceComponent = new ComponentName(context, TestJobService.class);
        PersistableBundle bundle = new PersistableBundle();
        bundle.putString("Loginid", Loginid);
        bundle.putString("Contestid", Contestid);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(60 * 1000); // wait at least
        builder.setOverrideDeadline(65 * 1000); // maximum delay
        builder.setExtras(bundle);
        //builder.setPeriodic(1000);
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        JobScheduler jobScheduler = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            jobScheduler = context.getSystemService(JobScheduler.class);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            jobScheduler = (JobScheduler)context.getSystemService( Context.JOB_SCHEDULER_SERVICE );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler.schedule(builder.build());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void Canceljob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, TestJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(1 * 1000); // wait at least
        builder.setOverrideDeadline(1 * 1000); // maximum delay
        //builder.setPeriodic(1000);
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        JobScheduler jobScheduler = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            jobScheduler = context.getSystemService(JobScheduler.class);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            jobScheduler = (JobScheduler)context.getSystemService( Context.JOB_SCHEDULER_SERVICE );
        }

        jobScheduler.cancelAll();
    }

}