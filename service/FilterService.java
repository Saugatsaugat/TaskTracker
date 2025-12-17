package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                            .filter(x->x.getTag().contains(tag))
                            .toList();
        return filteredDataList;
    }

    @Override
    public List<Task> byDueDate(LocalDate dueDate) {
        List<Task> tasks = taskService.readAllTasks();
        List<Task> filteredDataList = new ArrayList<>();
        filteredDataList = tasks.stream()
                            .filter(x -> (LocalDate.of(x.getDueDate().getYear(), x.getDueDate().getMonthValue(), x.getDueDate().getDayOfMonth())).isEqual(dueDate))
                            .toList();

        return filteredDataList;
    }

    @Override
    public List<Task> byPriority(TaskPriority priority) {
        List<Task> tasks = taskService.readAllTasks();
        List<Task> filteredDataList = new ArrayList<>();
        filteredDataList = tasks.stream()
                            .filter(x->x.getPriority().getValue().contains(priority.getValue()))
                            .collect(Collectors.toList());

        return filteredDataList;
    }
    
}
