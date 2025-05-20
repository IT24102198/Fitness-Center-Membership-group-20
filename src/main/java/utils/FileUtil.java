package utils;


import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    private static final String FILE_PATH = "C:/Users/user/OneDrive/Desktop/OOP Project/payments.txt";

    public static void savePayment(String data) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH, true); // append mode
        writer.write(data);
        writer.close();
    }
}
