import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoApp {


//START TODOAPP

public void start(String[] args) throws IOException {
    TodoApp todoApp = new TodoApp();

    if (args.length == 0) {
        todoApp.printUsage();
    } else if (args[0].equals("-l")) {
        todoApp.listTasks();
    } /*else if (args[0].equals("-c")) {
            TodoApp.markComplete();
        }*/ else if (args.length > 1 && args[0].equals("-r")) {
        todoApp.removeTasks(args);
    } else if (args[0].length() > 1 && args[0].equals("-a")) {
        todoApp.addNewTask(args);
    } else System.out.println("Please enter valid argument!");
}

// PRINT USAGE

    public void printUsage() {
        try {
            Path myPath = Paths.get("PrintUsage.txt");
            List<String> lines = Files.readAllLines(myPath);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
        }

//  LISTING TASKS

        System.out.println("Unable to read file: PrintUsage.txt");
        }
    public void listTasks() {
        try {
            Path myPath = Paths.get("TodoList.txt");
            List<String> lines = Files.readAllLines(myPath);
            if (lines.isEmpty()) {
                System.out.println("No todos for today! :)");
            } else {
                for (int i = 0; i < lines.size(); i++) {
                    System.out.println(i + 1 + ". " + lines.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to read file: Todolist.txt");
        }
    }

//  ADDING NEW TASK

    public void addNewTask(String[] aargs){
        Path myPath = Paths.get("TodoList.txt");
        List<String> list = new ArrayList<>(Arrays.asList(aargs));
        list.remove(0);
        String newTask = String.join(" ", list);
        if (aargs.length < 2) {
        System.out.println("Unable to add: no task provided");
        } else
        try {
            List<String> lines = Files.readAllLines(myPath);
            lines.add(newTask);
            Files.write(myPath, lines);
        } catch (Exception e) {
            System.out.println("Unable to read file: Todolist.txt");
        }
    }

//  REMOVE TASK

    public void removeTasks(String[] aargs) throws IOException {
        int lineNumber = Integer.parseInt(aargs[1].toString()) - 1;
        try {
            Path myPath = Paths.get("TodoList.txt");
            List<String> lines = Files.readAllLines(myPath);
            if (aargs.length < 2) {
                System.out.println("Unable to remove: no index provided");
            } else if (Integer.parseInt(aargs[1].toString()) > lines.size()) {
                System.out.println("Unable to remove: index is out of bound");
            } else
                lines.remove(lineNumber);
            Files.write(myPath, lines);
        } catch (Exception e) {
            System.out.println("Unable to read file: Todolist.txt");
        }
    }



//RUNNING TODOAPP

    public static void main(String[] args) throws IOException {

        TodoApp todoApp = new TodoApp();
        todoApp.start(args);
        
    }
}
