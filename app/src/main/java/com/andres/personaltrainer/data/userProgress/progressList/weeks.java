package com.andres.personaltrainer.data.userProgress.progressList;

public class weeks {

    private String numberWeek, user;

    public weeks(String numberWeek, String user) {
        this.numberWeek = numberWeek;
        this.user = user;
    }

    public String getNumberWeek() {
        return numberWeek;
    }

    public void setNumberWeek(String numberWeek) {
        this.numberWeek = numberWeek;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "weeks{" +
                "numberWeek='" + numberWeek + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
