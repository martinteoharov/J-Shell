import org.w3c.dom.stylesheets.LinkStyle;

import java.io.*;
import java.util.List;

// This class is the frontend for our shell. It parses the input and pipes it to the backend
public class SimpleShell {
    public static void main(String args[]) throws java.io.IOException {
        // Create jsh instance
        exec.createInstance();

        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print(exec.getPath());
            commandLine = console.readLine();
            if(commandLine.equals("")){
                continue;
            }

            String[] command = SimpleShell.parseCommand(commandLine);

            String result = "";
            switch(command[0]){
                case "ls":
                    List<String> _temp = exec.ls();
                    for (String str : _temp){
                        result += str + " ";
                    }
                    break;
                case "pwd":
                    result = exec.pwd();
                    break;
                case "cat":
                    try {
                        result = exec.cat(command[1]);
                    } catch(FileNotFoundException e) {
                        System.out.println("File not found.");
                    }
                    break;
                default:
                    result = "Something went wrong.";
            }

            System.out.println(result);

        }
    }
    // Returns an ArrayList: index 0 contains the command, index (1, inf) contain args
    private static String[] parseCommand(String str){
        String[] command = str.split("\\s+");
        return command;
    }

}