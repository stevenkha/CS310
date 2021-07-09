import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("No command line argument found");
        }

        List<String> labels = new ArrayList<>();
        List<List<Integer>> matrix = readGraph(args[0], labels);
        GenAlg genAlg = new GenAlg(matrix, labels);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Adjacency Matrix: ");
        System.out.println(genAlg);
        int popSize = userInputPopSize();
        int epochs = userInputEpoch();
        System.out.println();
        System.out.println("----------------------------------------------\n");
        System.out.println(genAlg.shortPath(popSize, epochs));
        System.out.println("Complete!");
    }

    //method reads the file from the command line and forms a List<List<Integer>> which makes a matrix of the data and returns it. Displays an error if the file is not found
    public static List<List<Integer>> readGraph(String args, List<String> labels) {
        List<List<Integer>> matrix = new ArrayList<>();
        try (Scanner scan = new Scanner(Paths.get(args))) {
            while (scan.hasNext()) {
                List<Integer> prices = new ArrayList<>();
                String input = scan.nextLine();
                String[] parts = input.split(",");
                labels.add(parts[0].toLowerCase());
                for (int i = 1; i < parts.length; i++) {
                    int x = Integer.parseInt(parts[i].trim());
                    prices.add(x);
                }

                matrix.add(prices);
            }
        } catch (Exception e) {
            System.out.println("Error: File not found");
            System.exit(0);
        }

        return matrix;
    }
    //returns the user input for population size
    public static int userInputPopSize() {
        int popSize = 0;
        Scanner scan = new Scanner(System.in);
        String input;
        while (popSize == 0) {
            System.out.print("Enter population size (Default = 10): ");
            input = scan.nextLine();
            if (input.equals("")) {
                popSize = 10;
            } else if (Integer.parseInt(input) == 1) {
                System.out.println("Invalid population size");
            } else {
                popSize = Integer.parseInt(input);
            }
        }

        return popSize;
    }
    //returns the user input for the number of epochs
    public static int userInputEpoch() {
        int epochs = 0;
        Scanner scan = new Scanner(System.in);
        String input;
        while (epochs == 0) {
            System.out.print("Enter number of epochs (Default = 10): ");
            input = scan.nextLine();
            if (input.equals("")) {
                epochs = 10;
            } else {
                epochs = Integer.parseInt(input);
            }
        }

        return epochs;
    }

}
