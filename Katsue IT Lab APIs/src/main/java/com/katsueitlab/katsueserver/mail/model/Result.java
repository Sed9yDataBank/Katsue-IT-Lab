package com.katsueitlab.katsueserver.mail.model;

public class Result {

    private int code;
    private String note;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    //Our Two Arguments --->
    public Result () {

    }
    public Result(int code, String note) {
        this.code = code;
        this.note = note;
    }

    public String toString() {
        return "Result{" +
                "Code=" + code +
                ", Note='" + note + '\'' +
                '}';
    }
}
