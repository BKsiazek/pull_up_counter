package com.barts.pullupcounter.pullupcounter.Model;

public class DailyEntry {
    private int id;
    private String date;
    private int pullupsCount, assistedPullups, chinupsCount, assistedChinups;

    public DailyEntry() {
    }

    public DailyEntry(int id, String date, int pullupsCount, int assistedPullups, int chinupsCount, int assistedChinups) {
        this.id = id;
        this.date = date;
        this.pullupsCount = pullupsCount;
        this.assistedPullups = assistedPullups;
        this.chinupsCount = chinupsCount;
        this.assistedChinups = assistedChinups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPullupsCount() {
        return pullupsCount;
    }

    public void setPullupsCount(int pullupsCount) {
        this.pullupsCount = pullupsCount;
    }

    public int getAssistedPullups() {
        return assistedPullups;
    }

    public void setAssistedPullups(int assistedPullups) {
        this.assistedPullups = assistedPullups;
    }

    public int getChinupsCount() {
        return chinupsCount;
    }

    public void setChinupsCount(int chinupsCount) {
        this.chinupsCount = chinupsCount;
    }

    public int getAssistedChinups() {
        return assistedChinups;
    }

    public void setAssistedChinups(int assistedChinups) {
        this.assistedChinups = assistedChinups;
    }
}
