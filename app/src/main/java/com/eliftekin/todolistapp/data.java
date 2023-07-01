package com.eliftekin.todolistapp;

public class data {
    private String TaskTitleHere;
    private String DueDateHere;
    private boolean check;

    //data nesnesi oluşturulduğunda bu özelliklere değer atar
    public data (String TaskTitleHere, String DueDateHere){
        this.TaskTitleHere = TaskTitleHere;
        this.DueDateHere = DueDateHere;
        this.check = false;
    }

    public String getTaskTitleHere(){
        return TaskTitleHere;}

    public String getDueDateHere(){
        return DueDateHere;}

    public void setcheck(boolean check){
        this.check = check;
    }

    public boolean isTaskCompleted(){
        return check;
    }
}
