package util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Task;

public class SortUtil {

    public class SortByDate implements Comparator {

        @Override
        public int compare(Object arg0, Object arg1) {
            Task task1 = (Task) arg0;
            Task task2 = (Task) arg1;

            if (task1.getDueDate().isBefore(task2.getDueDate()))
                return 1;
            if (task2.getDueDate().isBefore(task1.getDueDate()))
                return -1;
            return 0;
        }
    }

    public class SortByTag implements Comparator {
        @Override
        public int compare(Object arg0, Object arg1) {
            Task task1 = (Task) arg0;
            Task task2 = (Task) arg1;
            int value = task1.getTag().compareTo(task2.getTag());
            if (value < 0)
                return 1;
            if (value > 0)
                return -1;
            return 0;
        }
    }

    public class SortByPriority implements Comparator {
        @Override
        public int compare(Object arg0, Object arg1) {
            Task task1 = (Task) arg0;
            Task task2 = (Task) arg1;
            int value = task1.getPriority().getValue().compareTo(task2.getPriority().getValue());
            if (value < 0)
                return 1;
            if (value > 0)
                return -1;
            return 0;
        }
    }

    public static List<Task> sortFromDate(List<Task> dataList) {
        Comparator sortDateComparator = new SortUtil().new SortByDate();
        Collections.sort(dataList, sortDateComparator);
        return dataList;
    }

    public static List<Task> sortFromTag(List<Task> dataList) {
        Comparator sortTagComparator = new SortUtil().new SortByTag();
        Collections.sort(dataList, sortTagComparator);
        return dataList;
    }

    public static List<Task> sortFromPriority(List<Task> dataList) {
        Comparator sortPriorityComparator = new SortUtil().new SortByPriority();
        Collections.sort(dataList, sortPriorityComparator);
        return dataList;
    }

}
