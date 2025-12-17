package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import constants.TaskTrackerConsts;
import enums.TaskPriority;
import model.Task;

public class TaskService {
    String fileLocation = TaskTrackerConsts.FILE_LOCATION;

    /*
     * This method will read the content from csv file and return the list of
     * Tasks.
     */
    public List<Task> readAllTasks() {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            String line;

            // Header Escape
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Task task = new Task(values[0], values[1], LocalDate.parse(values[2]), TaskPriority.valueOf(values[3]),
                        values[4]);
                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /*
     * This method will write the content to csv file.
     */
    public void writeToFile(Task task) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation, true))) {
            String data = task.getName() + "," + task.getDueDate().toString() + "," + task.getPriority().getValue()
                    + "," + task.getNote();
            bw.newLine();
            bw.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    This method will create the new task in the CSV file and on success returns true; else false.
    */
    public boolean createTask(Task task) {
        try {
            writeToFile(task);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    

}
