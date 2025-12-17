package service;

import java.time.LocalDate;
import java.util.List;

import enums.TaskPriority;
import interfaces.IFilterSortSearch;
import model.Task;

public class SearchService<T> implements IFilterSortSearch {

    @Override
    public List<Task> byTag(String tag) {

        return null;
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
