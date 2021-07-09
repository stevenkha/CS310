import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;

public class FrequencyCount {
    private final HashMap<String, Integer> words;
    private final int parameterListSize;

    public FrequencyCount(List<String> text) {
        this.words = new HashMap<>();
        this.parameterListSize = text.size();
        addToMap(text);
    }

    public FrequencyCount(List<String> text, int degree) {
        this.words = new HashMap<>();
        this.parameterListSize = text.size();
        addToMap(nGramBuilder(text, degree));
    }

    //method returns a list of at most 20 of the most frequent tokens in the map
    public List<String> head() {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (String n : this.words.keySet()) {
            valueList.add(this.words.get(n));
        }

        valueList.sort(Collections.reverseOrder());
        return frequentListHelper(valueList);
    }

    //method returns a list of at most 20 of the least frequent tokens in the map
    public List<String> tail() {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (String n : this.words.keySet()) {
            valueList.add(this.words.get(n));
        }

        //if there are more than 20 items in the list, sort it and then remove everything after the 20th token
        if (valueList.size() > 20) {
            Collections.sort(valueList);
            valueList.subList(20, valueList.size()).clear();
        }

        valueList.sort(Collections.reverseOrder());
        return frequentListHelper(valueList);
    }

    //returns a random token present in the map
    public String randomToken() {
        ArrayList<String> keys = new ArrayList<>(this.words.keySet());
        int randSelection = (int) (Math.random() * keys.size());
        return keys.get(randSelection);
    }

    //returns the frequency of the chosen token
    public int count(String token) {
        return this.words.get(token);
    }

    //returns the percentage of the token present in the map compared to the total amount of tokens in the map
    public double percent(String token) {
        if (!this.words.containsKey(token)) {
            return -1;
        }

        return ((double) count(token) / this.parameterListSize);
    }

    //method adds the parameter token to the map. If the token is already present, the map will update the token's value and return the previous value
    public int add(String token) {
        int prevCount = 0;
        if (!this.words.containsKey(token)) {
            this.words.put(token, 1);
        } else {
            prevCount = this.words.get(token);
            this.words.put(token, this.words.get(token) + 1);
        }

        return prevCount;
    }

    //helper method adds every string in the list to the map using the dupChecker method
    private void addToMap(List<String> text) {
        for (String x : text) {
            dupChecker(x);
        }
    }

    //helper method adds a string the map and checks if there it is already present in the map. If it is, the method updates the value for the token
    private void dupChecker(String word) {
        if (this.words.containsKey(word)) {
            this.words.put(word, this.words.get(word) + 1);
        } else {
            this.words.put(word , 1);
        }
    }

    //method helps build a list based on the specified degree and returns the list
    private List<String> nGramBuilder(List<String> text, int degree) {
        ArrayList<String> nGramList = new ArrayList<>();
        for (int i = 0; i < text.size(); i+=degree) {
            //adds first word to the string
            StringBuilder str = new StringBuilder(text.get(i) + " ");
            //adds the next three words to the string
            for (int j = i; j < i + degree - 1; j++) {
                if (j + 1 >= text.size()) {
                    break;
                } else if (j + 1 == i + degree - 1) {
                    str.append(text.get(j + 1));
                } else {
                    str.append(text.get(j + 1)).append(" ");
                }
            }

            nGramList.add(str.toString());
        }

        return nGramList;
    }

    //helper method calculates the maximum of 20 tokens to add to a list depending on the type of list (i.e least frequent or most frequent)
    private ArrayList<String> frequentListHelper(ArrayList<Integer> valueList) {
        ArrayList<String> freqList = new ArrayList<>();
        int count = 20;
        int i = 0;
        while (count > 0) {
            for (String k : this.words.keySet()) {
                //if freqTokens is maxed out at 20 break out of loop
                if (freqList.size() == 20) {
                    break;
                } else if (this.words.get(k).compareTo(valueList.get(i)) == 0) {
                    if (freqList.contains(k)) {
                        break;
                    } else {
                        freqList.add(k);
                    }
                }
            }
            //break out of while loop if there are no more words to add
            if (freqList.size() == this.words.keySet().size()) {
                break;
            }

            i++;
            count--;
        }

        return freqList;
    }
}
