package com.bjtu.redis;

public class user {
    private String id;
    private int counter;
    private String values;
    private String action;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "user{" +
                "id='" + id + '\'' +
                ", counter=" + counter +
                ", values='" + values + '\'' +
                ", action='" + action + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public user() {
    }
}
