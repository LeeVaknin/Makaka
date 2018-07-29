package CacheManager;
import Models.Solution;
import State.State;
import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class FileManager<T> implements CacheManager<T> {

    private String idDelimiter = ", ";
    private String stepsDelimiter = ";";
    private final String solutionsFileName = "solutions.txt";

    /**
     * Gets the board that we would like to saveSolution, generates it's id and saves it to the relevant file.
     *
     * @param id - the id of the problem we would like to save
     * @return the saved solution of the saved board
     */
    @Override
    public String saveSolution(String id, Solution<T> solution) {

        try (FileWriter fileWriter = new FileWriter(solutionsFileName); BufferedWriter writer = new BufferedWriter(fileWriter)) {
            // saves rotations, col and row as string.
            String levelSolution = solutionToString(solution);
            String strToSave = String.join(this.idDelimiter, id, levelSolution);
            System.out.println(String.join(" ", "Saving the following solution:\n", strToSave));
            writer.write(strToSave);
            return levelSolution;

        } catch (IOException exception) {
            System.out.println(String.join(": ", "Couldn't saveSolution file error", exception.toString()));
            return null;
        }
    }

    /**
     * This methods loads a problem or a solution according to the given param
     *
     * @param id - The id of the requested board (level)
     * @return - The requested board or NULL if id doesn't exist.
     * @throws IOException Thrown only if something is going wrong during closing the file reader and buffer reader.
     */
    public String loadSolution(String id) {
        try {
            String[] requestedLine = Files.lines(Paths.get(solutionsFileName))
                    .filter(line -> line.startsWith(id))
                    .toString()
                    .split(this.idDelimiter);
            // After the split of the result - array of id and solution
            if (requestedLine.length == 2) {
                return requestedLine[1];
//                return this.extractSolution(requestedLine[1]);
            }
        } catch (IOException exception) {
            System.out.println(String.join(": ", "Couldn't loadSolution file error", exception.toString()));
        }
        System.out.println("Couldn't find the requested id.");
        return null;
    }

    private Solution<T> extractSolution(@NotNull String strSolution) {
        String[] strSteps = strSolution.split(this.stepsDelimiter);
        Solution<T> solution = new Solution<>();
        try {
            for (String strStep : strSteps) {
                String[] splitStep = strStep.split(" ");
//                solution.add(new Step(
//                        new Position(Integer.valueOf(splitStep[1]),
//                                Integer.valueOf(splitStep[2])),
//                        new Position(Integer.valueOf(splitStep[3]),
//                                Integer.valueOf(splitStep[4])),
//                        Integer.valueOf(splitStep[0])));
            }
        } catch (Exception ex) {
            System.out.println("FileManager.extractSolution(): Error details: " + ex.getMessage());
        }
        return solution;
    }

    private String solutionToString(@NotNull Solution<T> solution) {
        try {
            List<String> strSteps = solution.stream().map(Object::toString).collect(Collectors.toList());
            // saves rotations, col and row as string.
            return String.join(this.stepsDelimiter, strSteps);
        } catch (Exception ex) {
            System.out.println("FileManager.solutionToString(): Error details: " + ex.getMessage());
            return null;
        }
    }

    public static <T> T parse(String strObject, Class<T> objectClass) {
        try {
            // Create a Constructor
            Constructor constructor = objectClass.getConstructor(new Class[]{String.class});
            // create an instance
            T parsedObject = ((T) constructor.newInstance(strObject));
            return parsedObject;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException ex) {
            System.out.println("Couldn't parse ");
        }
        return null;
    }

}
