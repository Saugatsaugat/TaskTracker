package util;

import java.io.File;
import java.io.FileWriter;

import constants.TaskTrackerConsts;

public class Configuration {
    public Configuration() {

        String fileLocation = TaskTrackerConsts.FILE_LOCATION;

        File f = new File(fileLocation);
        try {
            if (f.createNewFile()) {
                System.out.println("File Created.");
                String columnName = "Name, Tag, Due Date, Priority, Note";
                FileWriter fw = new FileWriter(fileLocation);
                fw.write(columnName);
                System.out.println("File header written!!\n");
                fw.close();
            } else {
                System.out.println("File already Exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}