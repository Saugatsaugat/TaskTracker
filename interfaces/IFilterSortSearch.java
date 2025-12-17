package interfaces;

import java.time.LocalDate;
import java.util.List;

import enums.TaskPriority;
import model.Task;

/*
methods defined based on the fields for sorting, filtering, and searching
*/
public interface IFilterSortSearch{
    List<Task> byTag(String tag);
    List<Task> byDueDate(LocalDate dueDate);
    List<Task> byPriority(TaskPriority priority);
}