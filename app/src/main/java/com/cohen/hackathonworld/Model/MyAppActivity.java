package com.cohen.hackathonworld.Model;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

/**
 * Class Activity - Create user activity from different types
 * Goals - Documentation of all activities from all the users
 *       - Real time pop-ups to all relevant users
 *       - Undo methods option for users use
 * Class with NO setters - cannot change activity
 */
public class MyAppActivity {

    public static int NUMBER_OF_ACTIVITY = 1000000;

    public int activityId;
    public ACTIVITY_TYPE activityType;
    public LocalDateTime createdTimestamp;
    public int invokedBy;

    public MyAppActivity() {}

    public MyAppActivity withActivityId() {
        this.activityId = NUMBER_OF_ACTIVITY++;
        return this;
    }
    public MyAppActivity withActivityType(ACTIVITY_TYPE activityType) {
        this.activityType = activityType;
        return this;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public MyAppActivity withActivityTime() {
        this.createdTimestamp = LocalDateTime.now();
        return this;
    }
    public MyAppActivity withActivityInvokedById(int invokedBy) {
        this.invokedBy = invokedBy;
        return this;
    }

    public int getActivityId() {
        return activityId;
    }

    public ACTIVITY_TYPE getActivityType() {
        return activityType;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public int getInvokedBy() {
        return invokedBy;
    }
}
