package plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import constants.TaskTrackerConsts;
import model.Task;
import service.TaskService;

public class MarkdownExporter implements ITaskExporter {

    @Override
    public void export(String filename) {
        TaskService taskService = new TaskService();
        String fileLocation = TaskTrackerConsts.FILE_DIRECTORY + "//" + filename + ".md";

        // write column
        writeColumnToFile(fileLocation);

        // Read Data from file
        List<Task> dataList = taskService.readAllTasks();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation, true))) {
            for (Task task : dataList) {
                String data = "| " + task.getName() + " | " + task.getDueDate().toString() + " | "
                        + task.getPriority().getValue()
                        + " | " + task.getNote() + " |";
                bw.newLine();
                bw.write(data);
            }
            System.out.println(filename + ".md written successfully!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeColumnToFile(String fileLocation) {
        try {
            File f = new File(fileLocation);
            if (f.createNewFile()) {
                String columns = "| Name | Tag | Due Date | Priority | Note |\n| :---------- | :---------: | :---------: | :--------: | -----------:";
                FileWriter fw = new FileWriter(fileLocation);
                fw.write(columns);
                fw.close();
            } else {
                System.out.println("File exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
