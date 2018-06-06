package CacheManager;

import java.io.*;

public class File implements CacheManager {


    @Override
    public void save(String problem) {

        try {
            BufferedWriter writer = null;
            String line;
            FileWriter fileWriter = new FileWriter("in.txt");
            writer = new BufferedWriter(fileWriter);
            writer.write(problem);
            writer.close();
        } catch (IOException exception) {
            System.out.println(String.join(": ", "Couldn't save file error", exception.toString()));
        }

    }

    @Override
    public String load() {
        String line = "";
        try {
            BufferedReader reader = null;
            FileReader fileReader = new FileReader("in.txt");
            reader = new BufferedReader(fileReader);
            while ((reader.readLine()) != null) {
                String.join("\n", line, reader.readLine());
            }
            reader.close();
        } catch (IOException exception) {
            System.out.println(String.join(": ", "Couldn't load file error", exception.toString()));

        }
        return line;
    }


}
