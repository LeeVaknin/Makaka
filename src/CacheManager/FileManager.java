package CacheManager;

import Models.Board;
import Models.Position;
import Models.Solution;
import Models.Step;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager<T> implements CacheManager<T> {

    String idDelimiter = ", ";
    String stepsDelimiter = ";";
    final String solutionsFileName = "solutions.txt";

    /**
     * Gets the board that we would like to saveSolution, generates it's id and saves it to the relevant file.
     *
     * @param board - the board we would like to saveSolution
     * @return the id of the saved board
     * @throws IOException - thrown only if something went wrong during closing the connection
     */
    @Override
    public String saveSolution(Board<T> board, Solution<Step> solution) throws IOException {

        String levelSolution;
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try {
            fileWriter = new FileWriter(solutionsFileName);
            writer = new BufferedWriter(fileWriter);
            // saves rotations, col and row as string.
            levelSolution = solutionToString(solution);
            writer.write(levelSolution);

        } catch (IOException exception) {
            System.out.println(String.join(": ", "Couldn't saveSolution file error", exception.toString()));
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
     *
     * @param id - The id of the requested board (level)
     * @return - The requested board or NULL if id doesn't exist.
     * @throws IOException Thrown only if something is going wrong during closing the file reader and buffer reader.
     */
    public Solution<Step> loadSolution(String id) throws IOException {
        try {
            String[] requestedLine = Files.lines(Paths.get(solutionsFileName))
                    .filter(line -> line.startsWith(id))
                    .toString()
                    .split(this.idDelimiter);
            // After the split of the result - array of id and solution
            if (requestedLine.length == 2) {
                return this.extractSolution(requestedLine[1]);
            }

        } catch (IOException exception) {
            System.out.println(String.join(": ", "Couldn't loadSolution file error", exception.toString()));
        }
        System.out.println("Couldn't find the requested id.");
        return null;
    }

    private Solution<Step> extractSolution(@NotNull String strSolution) {
        String[] strSteps = strSolution.split(this.stepsDelimiter);
        Solution<Step> solution = new Solution<>();
        try {
            for (String strStep : strSteps) {
                String[] splitStep = strStep.split(" ");
                solution.add(new Step(
                        new Position(Integer.valueOf(splitStep[1]),
                                Integer.valueOf(splitStep[2])),
                        Integer.valueOf(splitStep[0])));
            }
        } catch (Exception ex) {
            System.out.println("FileManager.extractSolution(): Error details: " + ex.getMessage());
        }
        return solution;
    }

    private String solutionToString(@NotNull Solution<Step> solution) {
        try {
            List<String> strSteps = solution.stream().map(Object::toString).collect(Collectors.toList());
            // saves rotations, col and row as string.
            return String.join(this.stepsDelimiter, strSteps);
        } catch (Exception ex) {
            System.out.println("FileManager.solutionToString(): Error details: " + ex.getMessage());
            return null;
        }
    }

}
