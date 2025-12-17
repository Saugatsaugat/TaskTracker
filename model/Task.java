package model;

import java.time.LocalDate;
import enums.TaskPriority;

public class Task {
    private String name;
    private String tag;
    private LocalDate dueDate;
    private TaskPriority priority;
    private String note;

    public Task(){}

    public Task(String name, String tag, LocalDate dueDate, TaskPriority priority, String note){
        this.name = name;
        this.tag = tag;
        this.dueDate = dueDate;
        this.priority = priority;
        this.note = note;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getTag(){
        return this.tag;
    }
    public void setTag(String tag){
        this.tag = tag;
    }

    public LocalDate getDueDate(){
        return this.dueDate;
    }
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public TaskPriority getPriority(){
        return this.priority;
    }
    public void setPriority(TaskPriority priority){
        this.priority = priority;
    }

    public String getNote(){
        return this.note;
    }
    public void setNote(String note){
        this.note = note;
    }
}
