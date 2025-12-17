package service;

import java.util.ArrayList;
import java.util.List;

import model.Task;
import util.SortUtil;

public class SortService {

    TaskService taskService = new TaskService();

    public List<Task> byTag() {
        List<Task> tasks = taskService.readAllTasks();
        List<Task> filteredDataList = new ArrayList<>();
        filteredDataList = SortUtil.sortFromTag(tasks);
        return filteredDataList;
    }

    public List<Task> byDueDate() {
        List<Task> tasks = taskService.readAllTasks();
        List<Task> filteredDataList = new ArrayList<>();
        filteredDataList = SortUtil.sortFromDate(tasks);
        return filteredDataList;
    }

    public List<Task> byPriority() {
        List<Task> tasks = taskService.readAllTasks();
        List<Task> filteredDataList = new ArrayList<>();
        filteredDataList = SortUtil.sortFromPriority(tasks);
        return filteredDataList;
    }

}
