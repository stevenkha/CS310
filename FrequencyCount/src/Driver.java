import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        //if no command line argument is found, throw an exception
        if (args.length == 0) {
            throw new Exception("No command line argument found");
        } else if (parseFile(args[0]).isEmpty()) {
            //if the file isn't found, exit the program
            System.exit(0);
        }

        output(parseFile(args[0]));
    }

    //parses the file given in the command line and adds the strings to a list
    public static LinkedList<String> parseFile(String args) {
        LinkedList<String> aList = new LinkedList<>();
        try (Scanner scanner = new Scanner(Paths.get(args))) {
            while(scanner.hasNextLine()) {
                String word = scanner.nextLine().trim().toLowerCase();
                String[] parts = word.split(" ");
                aList.addAll(Arrays.asList(parts));
            }
        } catch (Exception e) {
            System.out.println("Error: File was not found");
        }

        return aList;
    }

    public static void output(LinkedList<String> text) {
        FrequencyCount singleCounts = new FrequencyCount(text);
        FrequencyCount fourCounts = new FrequencyCount(text, 4);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Single Words (DEMO 1)");
        System.out.println("Total number of tokens in the file: " + text.size());
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("20 most frequent keys in the list" + "\t" + " Frequency" + "\t" + "  Percent");
        System.out.println("----------------------------------------------------------------------------------");
        for (String n : singleCounts.head()) {
            System.out.printf("%-41s", n);
            System.out.printf("%5d", singleCounts.count(n));
            System.out.printf("\t \t  " + "%.05f\n", singleCounts.percent(n));
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("20 least frequent keys in the list" + "\t" + " Frequency" + "\t" + "  Percent");
        System.out.println("----------------------------------------------------------------------------------");
        for (String n : singleCounts.tail()) {
            System.out.printf("%-41s", n);
            System.out.printf("%5d", singleCounts.count(n));
            System.out.printf("\t \t  " + "%.05f\n", singleCounts.percent(n));
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Four consecutive words (DEMO 4)");
        System.out.println("Total number of tokens in the file: " + text.size());
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("20 most frequent keys in the list" + "\t" + " Frequency" + "\t" + "  Percent");
        System.out.println("----------------------------------------------------------------------------------");
        for (String n : fourCounts.head()) {
            System.out.printf("%-41s", n);
            System.out.printf("%5d", fourCounts.count(n));
            System.out.printf("\t \t  " + "%.05f\n", fourCounts.percent(n));
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("20 least frequent keys in the list" + "\t" + " Frequency" + "\t" + "  Percent");
        System.out.println("----------------------------------------------------------------------------------");
        for (String n : fourCounts.tail()) {
            System.out.printf("%-41s", n);
            System.out.printf("%5d", fourCounts.count(n));
            System.out.printf("\t \t  " + "%.05f\n", fourCounts.percent(n));
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Random Poetry Generator:");
        System.out.println(fourCounts.randomToken());
        System.out.println(fourCounts.randomToken() + " " + fourCounts.randomToken());
        System.out.println(fourCounts.randomToken());
    }
}
