
public class ArrayDriver {
    public static void main(String[] args) {
        
        int numOfEpochs = 5;
        int testSize = 16000;
        
        testAddInOrderOutput(testSize, numOfEpochs);
        testAddReverseOutput(testSize, numOfEpochs);
        testRemoveOutput(testSize, numOfEpochs);
        testClearOutput(testSize, numOfEpochs);

    }

    private static void addInOrder(SortedArrayList<Integer> test, int testSize) {
        for (int i = 1; i <= testSize; i ++) {
            test.add(i);
        }

    }
    
    private static void addReverse(SortedArrayList<Integer> test, int testSize) {
    	for (int i = testSize; i >= 1; i--) {
    		test.add(i);
    	}
    }
    
    private static void remove(SortedArrayList<Integer> test) {
    	while (!test.isEmpty()) {
    		test.remove(0);
    	}
    }
    
    private static void clear(SortedArrayList<Integer> test) {
    	test.clear();
    }
    
    private static int runTestAddInOrder(SortedArrayList<Integer> test, int testSize) {
    	
    	long clockStart = System.currentTimeMillis();
    	addInOrder(test, testSize);
    	long clockEnd = System.currentTimeMillis();

    	int elapsed = (int) ((clockEnd - clockStart));

    	return elapsed;
    	
    }
    
    private static int runTestAddReverse(SortedArrayList<Integer> test, int testSize) {
    	
    	long clockStart = System.currentTimeMillis();
    	addReverse(test, testSize);
    	long clockEnd = System.currentTimeMillis();

    	int elapsed = (int) ((clockEnd - clockStart));

    	return elapsed;
    	
    }

    private static int runTestRemove(SortedArrayList<Integer> test) {
    	
    	long clockStart = System.currentTimeMillis();
    	remove(test);
    	long clockEnd = System.currentTimeMillis();

		int elapsed = (int) ((clockEnd - clockStart));

		return elapsed;
    }
    
    private static int runTestClear(SortedArrayList<Integer> test) {
    	
    	long clockStart = System.currentTimeMillis();
    	clear(test);
    	long clockEnd = System.currentTimeMillis();
    	
    	int elapsed = (int) ((clockEnd - clockStart));
    	
    	return elapsed;
    }
    
    private static void testAddInOrderOutput(int testSize, int numOfEpochs) {

    	int factor = 0;
    	int prevTime = 0;
		System.out.println("Add In Order:");
        for (int i = 0; i < numOfEpochs; i++) {
        	
        	int elapsedTime = runTestAddInOrder(new SortedArrayList<>(16), testSize);
        	
        	if (prevTime != 0) {
        		factor = elapsedTime / prevTime;
        	}

			outputTemplate(testSize, elapsedTime, factor, i);

        	testSize *= 2;
        	prevTime = elapsedTime;
        	
        }

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }
    
    private static void testAddReverseOutput(int testSize, int numOfEpochs) {

		int factor = 0;
		int prevTime = 0;
		System.out.println("Add In Reverse:");
		for (int i = 0; i < numOfEpochs; i++) {

			int elapsedTime = runTestAddReverse(new SortedArrayList<>(16), testSize);

			if (prevTime != 0) {
				factor = elapsedTime / prevTime;
			}

			outputTemplate(testSize, elapsedTime, factor, i);

			testSize *= 2;
			prevTime = elapsedTime;

		}

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    private static void testRemoveOutput(int testSize, int numOfEpochs) {

    	SortedArrayList<Integer> testList = new SortedArrayList<>(16);
    	int factor = 0;
    	int prevTime = 0;
		System.out.println("Remove");
        for (int i = 0; i < numOfEpochs; i++) {

			addInOrder(testList, testSize);
        	int elapsedTime = runTestRemove(testList);

        	if (prevTime != 0) {
        		factor = elapsedTime / prevTime;
        	}

			outputTemplate(testSize, elapsedTime, factor, i);

        	testSize *= 2;
        	prevTime = elapsedTime;

        }

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    private static void testClearOutput(int testSize, int numOfEpochs) {

    	SortedArrayList<Integer> testList = new SortedArrayList<>(16);
    	int factor = 0;
    	int prevTime = 0;
		System.out.println("Clear:");
        for (int i = 0; i < numOfEpochs; i++) {
			addInOrder(testList, testSize);
        	int elapsedTime = runTestClear(testList);
        	
        	if (prevTime != 0) {
        		factor = elapsedTime / prevTime;
        	}

			outputTemplate(testSize, elapsedTime, factor, i);

        	testSize *= 2;
        	prevTime = elapsedTime;
        	
        }
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    private static void outputTemplate(int testSize, int elapsedTime, int factor, int iteration) {

    	System.out.println("Size: " + testSize);
    	System.out.println("Elapsed: " + elapsedTime + " milliseconds");

		if (iteration >= 1) {
			System.out.println("Factor: " + factor);
		}
		System.out.println("-------------");
	}
}
