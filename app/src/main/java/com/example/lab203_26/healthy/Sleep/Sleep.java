package com.example.lab203_26.healthy.Sleep;

public class Sleep {
    private String currentDate;
    private String timeToSleep;
    private String timeToGetUp;
    private String CountTime;
    private int primaryId;
    private static Sleep sleepInstance;

    public Sleep(){

    }

    public Sleep(String currentDate, String timeToSleep, String timeToGetUp, String countTime, int primaryId) {
        this.currentDate = currentDate;
        this.timeToSleep = timeToSleep;
        this.timeToGetUp = timeToGetUp;
        CountTime = countTime;
        this.primaryId = primaryId;
    }

    public static Sleep getSleepInstance() {
        if(sleepInstance == null) {
            sleepInstance = new Sleep();
        }
        return sleepInstance;
    }
    public static Sleep setSleepInstance() {
        if(sleepInstance != null) {
            sleepInstance = null;
        }
        return sleepInstance;
    }


    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }

    public String getTimeToSleep() {

        return timeToSleep;
    }

    public void setTimeToSleep(String timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    public String getTimeToGetUp() {
        return timeToGetUp;
    }

    public void setTimeToGetUp(String timeToGetUp) {
        this.timeToGetUp = timeToGetUp;
    }

    public String getCountTime() {
        return CountTime;
    }

    public void setCountTime(String countTime) {
        CountTime = countTime;
    }
}
