package interfaces;

import java.time.LocalDate;
import java.util.List;

import enums.TaskPriority;

/*
methods defined based on the fields for sorting, filtering, and searching
*/
public interface IFilterSortSearch<T>{
    List<T> byTag(String tag);
    List<T> byDueDate(LocalDate dueDate);
    List<T> byPriority(TaskPriority priority);
}