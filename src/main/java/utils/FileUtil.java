package utils;

//writing characters to a file
import java.io.FileWriter;
//handle input/output related errors
import java.io.IOException;

public class FileUtil {

    private static final String FILE_PATH = "C:/Users/user/OneDrive/Desktop/OOP Project/payments.txt";

    public static void savePayment(String data) throws IOException {

        //adding data end of the files
        FileWriter writer = new FileWriter(FILE_PATH, true);
        writer.close();
    }
}
