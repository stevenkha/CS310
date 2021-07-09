import edu.sdsu.cs.datastructures.List;
import edu.sdsu.cs.datastructures.SinglyLinkedList;

public class Driver {
    public static void main(String[] args) {
        int numOfEpochs = 5;
        int testSize = 16000;

        addFirstTestOutput(testSize, numOfEpochs);
        System.out.println("-------------------------------------------------------");
        addLastTestOutput(testSize, numOfEpochs);
        System.out.println("-------------------------------------------------------");
        removeFirstTestOutput(testSize, numOfEpochs);
        System.out.println("-------------------------------------------------------");
        removeLastTestOutput(testSize, numOfEpochs);
        System.out.println("-------------------------------------------------------");
        testClear(testSize, 4);
        System.out.println("-------------------------------------------------------");
        testAddList();
        System.out.println("-------------------------------------------------------");
        removeTest();
        System.out.println("-------------------------------------------------------");
        sortTest();
        System.out.println("-------------------------------------------------------");
        setTest();
        System.out.println("-------------------------------------------------------");
        reverseTest();
        System.out.println("HAVE A NICE DAY!");
    }

    //calls the addFirst method on the list a certain number of times
    public static void addFirstTest(List<Integer> testList, int testSize) {
        for (int i = 1; i <= testSize; i++) {
            testList.addFirst(i);
        }
    }

    //calls the addLast method on the list a certain number of times
    public static void addLastTest(List<Integer> testList, int testSize) {
        for (int i = 1; i <= testSize; i++) {
            testList.addLast(i);
        }
    }

    //method should run the addFirstTest and note the timing
    public static int runAddFirstTest(List<Integer> testList, int testSize) {
        long clockStart = System.currentTimeMillis();
        addFirstTest(testList, testSize);
        long clockEnd = System.currentTimeMillis();

        int elapsed = (int) (clockEnd - clockStart);
        return elapsed;
    }

    //method should run the addLastTest and returns the timing
    public static int runAddLastTest(List<Integer> testList, int testSize) {
        long clockStart = System.currentTimeMillis();
        addLastTest(testList, testSize);
        long clockEnd = System.currentTimeMillis();

        int elapsed = (int) (clockEnd - clockStart);
        return elapsed;
    }

    //method runs the remove method on the first item of the list and returns the timing
    public static int runRemoveFirst(List<Integer> testList, int testSize) {
        addFirstTest(testList, testSize);
        long clockStart = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            testList.remove(0);
        }
        long clockEnd = System.currentTimeMillis();
        int elapsed = (int) (clockEnd - clockStart);

        return elapsed;
    }

    //method runs the remove method on the last time of the list and returns the timing
    public static int runRemoveLast(List<Integer> testList, int testSize) {
        addFirstTest(testList, testSize);
        long clockStart = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            testList.remove(-1);
        }
        long clockEnd = System.currentTimeMillis();
        int elapsed = (int) (clockEnd - clockStart);

        return elapsed;
    }

    //outputs the results of the addFirst test
    public static void addFirstTestOutput(int testSize, int numOfEpochs) {
        int factor = 0;
        int prevTime = 0;

        System.out.println("addFirst Test:");
        for (int i = 0; i < numOfEpochs; i++) {
            int elapsedTime = runAddFirstTest(new SinglyLinkedList<>(), testSize);

            if (prevTime != 0) {
                factor = elapsedTime / prevTime;
            }

            timingOutputTemplate(testSize, elapsedTime, factor, i);

            testSize *= 2;
            prevTime = elapsedTime;
        }
    }

    //outputs the results of the addLast test
    public static void addLastTestOutput(int testSize, int numOfEpochs) {
        int factor = 0;
        int prevTime = 0;

        System.out.println("addLast Test:");
        for (int i = 0; i < numOfEpochs; i++) {
            int elapsedTime = runAddLastTest(new SinglyLinkedList<>(), testSize);

            if (prevTime != 0) {
                factor = elapsedTime / prevTime;
            }

            timingOutputTemplate(testSize, elapsedTime, factor, i);

            testSize *= 2;
            prevTime = elapsedTime;
        }
    }

    //outputs the results of the removeFirst test
    public static void removeFirstTestOutput(int testSize, int numOfEpochs) {
        int factor = 0;
        int prevTime = 0;

        System.out.println("removeFirst Test: ");
        for (int i = 0; i < numOfEpochs; i++) {
            int elapsedTime = runRemoveFirst(new SinglyLinkedList<>(), testSize);

            if (prevTime != 0) {
                factor = elapsedTime / prevTime;
            }

            timingOutputTemplate(testSize, elapsedTime, factor, i);

            testSize *= 2;
            prevTime = elapsedTime;
        }
    }

    //outputs the results of the removeLast test
    public static void removeLastTestOutput(int testSize, int numOfEpochs) {
        int factor = 0;
        int prevTime = 0;

        System.out.println("removeLast Test: ");
        for (int i = 0; i < numOfEpochs; i++) {
            int elapsedTime = runRemoveLast(new SinglyLinkedList<>(), testSize);

            if (prevTime != 0) {
                factor = elapsedTime / prevTime;
            }

            timingOutputTemplate(testSize, elapsedTime, factor, i);

            testSize *= 2;
            prevTime = elapsedTime;
        }
    }

    //outputs the results of testing the clear method while using the size method and count method
    public static void testClear(int testSize, int testNumber) {
        List<Integer> testList = new SinglyLinkedList<>();
        for (int i = 0; i < testSize; i++) {
            testList.add(testNumber);
        }

        System.out.println("Testing Clear:");
        System.out.println("Repeated item in List: " + testNumber);
        System.out.println("Before Clear Size: " + testList.size());
        System.out.println("Before Clear Count: " + testList.count(testNumber));
        System.out.println("Clearing.....");
        testList.clear();
        System.out.println("New Size: " + testList.size());
        System.out.println("New Count: " + testList.count(testNumber));
    }

    //outputs the results of testing the addList method
    public static void testAddList() {
        System.out.println("Testing AddList:");
        List<Integer> firstList = new SinglyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            firstList.add(i);
        }

        List<Integer> secondList = new SinglyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            int randNum = (int) (Math.random() * 20);
            secondList.add(randNum);
        }

        System.out.print("First List: ");
        for (int i = 0; i < firstList.size(); i++) {
            System.out.print(firstList.get(i) + " ");
        }

        System.out.println();
        System.out.println("Size: " + firstList.size());
        System.out.print("Second List: ");
        for (int i = 0; i < secondList.size(); i++) {
            System.out.print(secondList.get(i) + " ");
        }

        System.out.println();
        System.out.println("Size: " + secondList.size());
        secondList.add(firstList);
        System.out.print("Second List addList With First List Result: ");
        for (int i = 0; i < secondList.size(); i++) {
            System.out.print(secondList.get(i) + " ");
        }

        System.out.println();
        System.out.println("Size: " + secondList.size());
    }

    //outputs the results of testing the remove method with negative and positive indices
    public static void removeTest() {
        List<Integer> testList = new SinglyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            int randNum = (int) (Math.random() * 20);
            testList.add(randNum);
        }

        System.out.println("Testing Remove: ");
        System.out.print("List: ");
        for (int i = 0; i < testList.size(); i++) {
            System.out.print(testList.get(i) + " ");
        }

        System.out.println();
        System.out.println("Removing index 0...");
        testList.remove(0);
        System.out.print("List: ");
        for (int i = 0; i < testList.size(); i++) {
            System.out.print(testList.get(i) + " ");
        }

        System.out.println();
        System.out.println("Removing index -1...");
        testList.remove(-1);
        System.out.print("List: ");
        for (int i = 0; i < testList.size(); i++) {
            System.out.print(testList.get(i)+ " ");
        }

        System.out.println();
        System.out.println("Removing index -2...");
        testList.remove(-2);
        System.out.print("List: ");
        for (int i = 0; i < testList.size(); i++) {
            System.out.print(testList.get(i)+ " ");
        }
        System.out.println();
    }

    //outputs the results of testing the sort method
    public static void sortTest() {
        System.out.println("Testing Sort: ");
        List<Integer> testList = new SinglyLinkedList<>();
        System.out.print("List: ");
        for (int i = 0; i < 5; i++) {
            int randNum = (int) (Math.random() * 20) - 5;
            testList.add(randNum);
        }

        for (int i = 0; i < 5; i++) {
            System.out.print(testList.get(i) + " ");
        }

        System.out.println();
        System.out.println("Sorting....");
        testList.sort();
        System.out.print("List: ");
        for (int i = 0; i < testList.size(); i++) {
            System.out.print(testList.get(i) + " ");
        }
        System.out.println();
    }

    //outputs the result of testing the set method with using the count method
    public static void setTest() {
        System.out.println("Testing Set: ");
        List<Integer> testList = new SinglyLinkedList<>();
        System.out.print("List: ");

        for (int i = 0; i < 5; i++) {
            testList.add(69);
        }

        for (int i = 0; i < testList.size(); i++) {
            System.out.print(testList.get(i) + " ");
        }
        System.out.println();
        System.out.println("Count of 69: " + testList.count(69));
        System.out.println("Count of -69: " + testList.count(-69));
        System.out.println("Setting elements to -69...");
        for (int i = 0; i < testList.size(); i++) {
            testList.set(i, -69);
        }
        System.out.print("List: ");
        for (int i = 0; i < testList.size(); i++) {
            System.out.print(testList.get(i) + " ");
        }
        System.out.println();
        System.out.println("Count of 69: " + testList.count(69));
        System.out.println("Count of -69: " + testList.count(-69));

    }

    //outputs the result of calling the reverse method on an empty list
    public static void reverseTest() {
        System.out.println("Testing Reverse: ");
        System.out.println("Calling reverse on an empty list...");
        List<Integer> testList = new SinglyLinkedList<>();
        testList.reverse();
        System.out.println("No compiler errors. Test was successful");

    }

    //helper method that organizes the output of the timing tests
    private static void timingOutputTemplate(int testSize, int elapsedTime, int factor, int iteration) {
        System.out.println("Size: " + testSize);
        System.out.println("Elapsed: " + elapsedTime + " milliseconds");

        if (iteration >= 1) {
            System.out.println("Factor: " + factor);
        }
        System.out.println("-------------");
    }



}


