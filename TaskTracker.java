import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import enums.TaskPriority;
import model.Task;
import plugin.CSVExporter;
import plugin.ITaskExporter;
import plugin.MarkdownExporter;
import service.FilterService;
import service.SearchService;
import service.SortService;
import service.TaskService;
import util.Configuration;

public class TaskTracker {
    static Scanner sc = new Scanner(System.in);
    static FilterService filterService = new FilterService();
    static SearchService searchService = new SearchService();
    static SortService sortService = new SortService();
    static TaskService taskService = new TaskService();

    public static void main(String[] args) {
        // Run the initial configuration
        Configuration conf = new Configuration();

        boolean flag = true;
        while (flag) {
            displayIntro();

            // Menu List
            System.out
                    .println("Select:\n1. Create Task\n2. List Tasks\n3. Filter\n4. Sort\n5. Search\n6. Export Data\n");

            try {
                int input = sc.nextInt();
                sc.nextLine();

                // switch case implementation
                switch (input) {
                    case 1:
                        createTask();
                        break;
                    case 2:
                        listTasks();
                        break;
                    case 3:
                        filterTask();
                        break;
                    case 4:
                        sortTask();
                        break;
                    case 5:
                        searchTask();
                        break;
                    case 6:
                        exportTask();
                        break;
                    default:
                        System.out.println("Wrong input");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("\nDo you want to start again, type 'y' for yes?");
            String userInp = sc.nextLine();
            if (userInp.equalsIgnoreCase("y")) {
                flag = true;
            } else {
                flag = false;
            }

        }
        sc.close();

    }

    public static void displayIntro() {
        System.out.println(
                "********************************************************************************************\n" +
                        "********************************************************************************************\n"
                        +
                        "*********\t\t\tWelcome to Task Tracker System\t\t************" +
                        "\n*********\t\t\tby\t\t\t\t\t\t************\n" +
                        "*********\t\t\tSAUGAT\t\t\t\t\t\t************\n" +
                        "********************************************************************************************\n"
                        +
                        "********************************************************************************************\n\n");
    }

    // Case 1: Creating Task
    public static void createTask() {
        Task task = new Task();

        try {
            System.out.println("Creating a new task. Enter\n");
            System.out.println("Name: ");
            String name = sc.nextLine();

            System.out.println("Tag: ");
            String tag = sc.nextLine();

            System.out.println("Due Date (YYYY-MM-DD): ");
            String dueDate = sc.nextLine();

            System.out.println("Select Priority[1. LOW\t2. MEDIUM\t3. HIGH]\nEnter: ");
            int priority = sc.nextInt();
            sc.nextLine();

            System.out.println("Note: ");
            String note = sc.nextLine();

            task.setName(name);
            task.setTag(tag);
            task.setNote(note);
            String[] values = dueDate.split("-");
            task.setDueDate(LocalDate.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
                    Integer.parseInt(values[2])));
            TaskPriority taskPriority = (priority == 1) ? TaskPriority.LOW
                    : (priority == 2) ? TaskPriority.MEDIUM : TaskPriority.HIGH;
            task.setPriority(taskPriority);

            // save task
            if (taskService.createTask(task)) {
                System.out.println("Task created successfully");
            } else {
                System.out.println("Task Creation Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Case 2: List all tasks
    public static void listTasks() {
        List<Task> tasks = taskService.readAllTasks();
        displayTasks(tasks);
    }

    // Case 3: Filter the Tasks
    public static void filterTask() {
        try {
            System.out.println("Filter by:\n1. Tag\n2. Due Date\n3. Priority\n");
            int input = sc.nextInt();
            sc.nextLine();

            if (input == 1) {
                System.out.println("Enter Tag name: ");
                String filterTagName = sc.nextLine();
                List<Task> tasks = filterService.byTag(filterTagName.toLowerCase());
                displayTasks(tasks);
            } else if (input == 2) {
                System.out.println("Enter Due Date(YYYY-MM-DD): ");
                String filterDueDate = sc.nextLine();
                String[] values = filterDueDate.split("-");
                LocalDate finalDueDate = LocalDate.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]));
                List<Task> tasks = filterService.byDueDate(finalDueDate);
                displayTasks(tasks);
            } else if (input == 3) {
                System.out.println("Select Priority (1. LOW\t2. MEDIUM\t3. HIGH)\nEnter: ");
                int priority = sc.nextInt();
                sc.nextLine();

                TaskPriority taskPriority = (priority == 1) ? TaskPriority.LOW
                        : ((priority == 2) ? TaskPriority.MEDIUM : TaskPriority.HIGH);
                List<Task> tasks = filterService.byPriority(taskPriority);
                displayTasks(tasks);
            } else {
                System.out.println("Wrong input");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Case 4: Sort the Tasks
    public static void sortTask() {
        try {
            System.out.println("Sort by:\n1. Tag\n2. Due Date\n3. Priority\n");
            int input = sc.nextInt();
            sc.nextLine();

            if (input == 1) {
                List<Task> tasks = sortService.byTag();
                displayTasks(tasks);
            } else if (input == 2) {
                List<Task> tasks = sortService.byDueDate();
                displayTasks(tasks);
            } else if (input == 3) {
                List<Task> tasks = sortService.byPriority();
                displayTasks(tasks);
            } else {
                System.out.println("Wrong input");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Case 5: Search Tasks
    public static void searchTask() {
        try {
            System.out.println("Search by:\n1. Tag\n2. Due Date\n3. Priority\n");
            int input = sc.nextInt();
            sc.nextLine();

            if (input == 1) {
                System.out.println("Enter Tag name: ");
                String filterTagName = sc.nextLine();
                List<Task> tasks = searchService.byTag(filterTagName);
                displayTasks(tasks);
            } else if (input == 2) {
                System.out.println("Enter Due Date(YYYY-MM-DD): ");
                String filterDueDate = sc.nextLine();
                String[] values = filterDueDate.split("-");
                LocalDate finalDueDate = LocalDate.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]));
                List<Task> tasks = searchService.byDueDate(finalDueDate);
                displayTasks(tasks);
            } else if (input == 3) {
                System.out.println("Select Priority (1. LOW\t2. MEDIUM\t2. HIGH): ");
                int priority = sc.nextInt();
                sc.nextLine();

                TaskPriority taskPriority = (priority == 1) ? TaskPriority.LOW
                        : ((priority == 2) ? TaskPriority.MEDIUM : TaskPriority.HIGH);
                List<Task> tasks = searchService.byPriority(taskPriority);
                displayTasks(tasks);
            } else {
                System.out.println("Wrong input");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Case 6: Export Tasks into CSV, Markdown, .............
    public static void exportTask() {
        try {
            System.out.println("which Exporter?\n1. CSV\n2. Markdown\nSelect: ");
            int userInput = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter filename: ");
            String filename = sc.nextLine();
            if (filename != "") {
                if (userInput == 1) {
                    ITaskExporter exporter = new CSVExporter();
                    exporter.export(filename);

                } else if (userInput == 2) {
                    ITaskExporter exporter = new MarkdownExporter();
                    exporter.export(filename);

                } else if (userInput == 2) {
                } else {
                    System.out.println("Wrong Input");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Display Tasks
    public static void displayTasks(List<Task> dataList) {
        // Displaying header column
        System.out.println(
                "\nName\tTag\tDue Date\tPriority\tNote\n-----------------------------------------------------------------\n");

        // Displaying Data
        for (Task task : dataList) {
            System.out.println(task.getName() + "\t" + task.getTag() + "\t" + task.getDueDate().toString() + "\t"
                    + task.getPriority().getValue() + "\t" + task.getNote() + "\n");
        }
    }
}