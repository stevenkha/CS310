import java.util.AbstractList;

public class SortedArrayList<E extends Comparable<E>> extends AbstractList<E> {

    private E[] storage;
    private final int initialCapacity;
    private int currCapacity;
    private int currSize;

    @SuppressWarnings("unchecked")
    public SortedArrayList() {
        this.initialCapacity = 16;
        this.currCapacity = 16;
        this.storage = (E[]) new Comparable[this.initialCapacity];
        this.currSize = 0;
    }

    @SuppressWarnings("unchecked")
    public SortedArrayList(int cap) {
        this.initialCapacity = cap;
        this.currCapacity = cap;
        this.storage = (E[]) new Comparable[cap];
    }

    //function adds value to the generic array and sorts the array
    public boolean add(E item) {
        checkFull();

        //if there are no elements in the array add the first item
        if (this.currSize == 0) {
            this.storage[this.currSize] = item;
            this.currSize++;
            return true;
        }
        
        int cursor = size() - 1;
        this.currSize++;

        if (cursor < 0) {
            cursor = 0;
        }

        //check if item is less than the last element and shift array accordingly
        while (item.compareTo(this.storage[cursor]) < 0 ) {
            this.storage[cursor + 1] = this.storage[cursor];
            cursor--;

            if (cursor < 0) {
                break;
            }

        }

        this.storage[cursor + 1] = item;
        return true;
    }

    //method returns the current capacity value. 
    public int capacity() {
        return this.currCapacity;
    }

    //method creates a new array with initial capacity and clears the old array
    public void clear() {
        this.storage = (E[]) new Comparable[this.initialCapacity];
        this.currSize = 0;
        this.currCapacity = this.initialCapacity;
    }

    //method checks if array is empty. Expected return is true if array is empty and false if not empty
    public boolean isEmpty() {
        return this.currSize == 0;
    }

    /*
        method removes item at the specified index, makes a new copy of the array, and shrinks the size of the array.
        Expected return is the item at the specified index.
    */
    public E remove(int index) {
        E itemToRemove = this.storage[index];
        System.arraycopy(this.storage, index + 1, this.storage, index, size() - index);
        this.currSize--;

        //if the current size is less than half the current capacity, half the current capacity
        if (size() < this.currCapacity/2) {
        	this.currCapacity = this.currCapacity/2;

        	//the current capacity will never be less than the initial capacity
        	if (this.currCapacity < this.initialCapacity) {
        		this.currCapacity = this.initialCapacity;
        	}
        }
        return itemToRemove;
    }

    //method returns the item at the specified index.
    @Override
    public E get(int index) {
        return this.storage[index];
    }

    //method returns the current size of the array.
    @Override
    public int size() {
        return this.currSize;
    }

    //method checks if the current array is at max capacity. Creates new array and transfers items if true
    @SuppressWarnings("unchecked")
    public void checkFull() {
        if (this.currSize >= this.currCapacity) {
            this.currCapacity = this.currCapacity * 2;
            E[] newStorage = (E[]) new Comparable[this.currCapacity];

            System.arraycopy(this.storage, 0, newStorage, 0, this.currSize);
            this.storage = newStorage;
        }
    }

}