package service;

import java.time.LocalDate;
import java.util.List;

import enums.TaskPriority;
import interfaces.IFilterSortSearch;

public class SearchService<T> implements IFilterSortSearch<T> {

    @Override
    public List<T> byTag(String tag) {

        return null;
    }

    @Override
    public List<T> byDueDate(LocalDate dueDate) {

        return null;
    }

    @Override
    public List<T> byPriority(TaskPriority priority) {

        return null;
    }
    
}
