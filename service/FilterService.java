package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.TaskPriority;
import interfaces.IFilterSortSearch;
import model.Task;

public class FilterService<T> implements IFilterSortSearch {

    TaskService taskService = new TaskService();

    @Override
    public List<Task> byTag(String tag) {
        List<Task> tasks = taskService.readAllTasks();
        List<Task> filteredDataList = new ArrayList<>();
        filteredDataList = tasks.stream()
                            .filter(x->x.getTag().equals(tag))
                            .toList();
        return filteredDataList;
    }

    @Override
    public List<Task> byDueDate(LocalDate dueDate) {

        return null;
    }

    @Override
    public List<Task> byPriority(TaskPriority priority) {

        return null;
    }
    
}
