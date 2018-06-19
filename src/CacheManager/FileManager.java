package CacheManager;

import Models.Board;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager<T> implements CacheManager<T> {

    String delimiter = ", ";
    final String solutionsFileName = "solutions.txt";
    final String problemsFileName = "problems.txt";

    public Integer createId(String problem) {
        Integer hashed = problem.hashCode();
        return hashed;
    }

    /**
     * Gets the board that we would like to save, generates it's id and saves it to the relevant file.
     * @param board - the board we would like to save
     * @param fileType - the type of the board we would like to save
     * @return the id of the saved board
     * @throws IOException - thrown only if something went wrong during closing the connection
     */
    @Override
    public Integer save(Board<T> board, FileType fileType) throws IOException {

        String problem = board.toString();
        Integer id = createId(problem);
        board.setId(id);

        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try {
            String line = "";
            String fileName = fileType == FileType.PROBLEM ? problemsFileName : solutionsFileName;
            fileWriter = new FileWriter(fileName);
            writer = new BufferedWriter(fileWriter);
            // Validate if it write string or int
            writer.write(board.getId() + this.delimiter);
            writer.write(problem);
        } catch (IOException exception) {
            System.out.println(String.join(": ", "Couldn't save file error", exception.toString()));
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
        return board.getId();
    }

    /**
     * This methods loads a problem or a solution according to the given param
     * @param id - The id of the requested board (level)
     * @param fileType - Problem or Solution
     * @return - The requested board or NULL if id doesn't exist.
     * @throws IOException Thrown only if something is going wrong during closing the file reader and buffer reader.
     */
    @Override
    public String load(Integer id, FileType fileType) throws IOException {
//
        try {

            String fileName = fileType == FileType.PROBLEM ? problemsFileName : solutionsFileName;

            String[] requestedLine = Files.lines(Paths.get(fileName))
                    .filter(line->line.startsWith(id.toString()))
                    .toString()
                    .split(this.delimiter);

            if (requestedLine.length == 2){
                return requestedLine[1];
            }

        } catch (IOException exception) {
            System.out.println(String.join(": ", "Couldn't load file error", exception.toString()));
        }
        System.out.println("Couldn't find the requested id.");
        return null;
    }

}
