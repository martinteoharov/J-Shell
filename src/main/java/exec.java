import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// This is the backend of our shell. It implements different bash commands like: pwd, ls, cat, cd...
public class exec {
    private static exec instance = null;

    // Username of our user
    private static String username = null;
    // This is the current directory location, it should probably have its own class, but we are using String instead.
    private static String currLocation = null;
    // In the format "jsh: [currLocation]>
    private static String path = null;
    // Stored command by command
    private static List<String> history = null;

    private exec () {
        exec.currLocation = System.getProperty("user.dir");
        exec.path = "jsh " + exec.currLocation + ">";
    }

    public static void createInstance() {
        if (exec.instance == null)
            exec.instance = new exec();
    }

    // The frontend is supposed to handle how the output looks, so here we return a List instead of a String
    public static List ls() {
        List<String> res = new ArrayList<String>();

        File folder = new File(exec.currLocation);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                res.add(file.getName());
            }
        }

        return res;
    }

    public static String pwd() {
        return currLocation;
    }

    public static String cat(String fname) throws java.io.FileNotFoundException, java.io.IOException {
        String res = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            while ((line = br.readLine()) != null) {
                res += line + "\n";
            }
        }
        return res;
    }

    public static void cd(String folderName) {
        // TODO:
    }

    public static String getPath(){
        return exec.path;
    }
}
