package edu.sdsu.cs.datastructures;

class Node<E> {
    E datum;
    Node<E> next;

    Node(E datum, Node<E> next) {
        this.datum = datum;
        this.next = next;
    }
}

public class SinglyLinkedList<E extends Comparable<E>> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //Copy constructor. Instantiates a new list with the same contents as the parameter list
    public SinglyLinkedList(List<E> aList) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        add(aList);
    }

    //method will append the given datum parameter to the END of the list and increase the size
    @Override
    public boolean add(E datum) {
        addLast(datum);
        return true;
    }

    //method will append the contents of the parameter list to the end of the current list and increase size accordingly
    @Override
    public boolean add(List<E> other) {
        if (other.size() == 0) {
            return false;
        } else if (isEmpty()) {
            addFirst(other.get(0));
            for (int i = 1; i < other.size(); i++) {
                this.tail.next = new Node<E>(other.get(i), null);
                this.tail = this.tail.next;
                this.size++;
            }

            return true;
        }

        for (int i = 0; i < other.size(); i++) {
            this.tail.next = new Node<E>(other.get(i), null);
            this.tail = this.tail.next;
            this.size++;
        }

        return true;
    }

    //method should append the given datum parameter to the FRONT of the list and increase the size. Also updates head pointer
    @Override
    public boolean addFirst(E datum) {
        if (isEmpty()) {
            this.head = new Node<E>(datum, null);
            this.tail = this.head;
            this.size++;
            return true;
        }
;
        this.head = new Node<E>(datum, this.head);
        this.size++;
        return true;
    }

    //method should append the given datum parameter to the END of the list and increase the size. It should also keep track of the tail pointer
    @Override
    public boolean addLast(E datum) {
        if (isEmpty()) {
            addFirst(datum);
            this.tail = this.head;
            return true;
        }

        this.tail.next = new Node<E>(datum, null);
        this.tail = this.tail.next;
        this.size++;
        return true;
    }

    //method checks if the list is empty a.k.a if size is 0
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    //method clears the list of all arguments
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //method includes a counter that tracks the count of the parameter data in the list. Should return the counter/amount of the parameter data in the list
    @Override
    public int count(E target) {
        int counter = 0;
        Node<E> tracker = this.head;
        for (int i = 0; i < this.size; i++) {
            if (tracker.datum.compareTo(target) == 0) {
                counter++;
            }

            tracker = tracker.next;
        }

        return counter;
    }

    //method returns the data of the chosen index node
    @Override
    public E get(int index) {
        index = checkIndex(index);
        Node<E> tracker = findIndex(index);
        E item = tracker.datum;
        return item;
    }

    //method returns the data that is to be removed from the chosen index node and updates the size
    @Override
    public E remove(int index) {
        index = checkIndex(index);
        if (index == 0 || index == -1 * this.size) {
            E itemToRemove = this.head.datum;
            this.head = this.head.next;
            this.size--;
            return itemToRemove;
        }

        Node<E> tracker = findIndex(index-1);
        E itemToRemove = tracker.next.datum;
        tracker.next = tracker.next.next;
        this.size--;
        return itemToRemove;
    }

    //method should reverse the contents of the list and update pointers
    @Override
    public void reverse() {
        Node<E> oldTail = this.tail;
        int revIndex = this.size - 2;
        while (revIndex > -1) {
            Node<E> tracker = findIndex(revIndex);
            this.tail.next = tracker;
            tracker.next = null;
            this.tail = this.tail.next;
            revIndex--;
        }

        this.head = oldTail;
    }

    //method updates the data in the index node with the given parameter data
    @Override
    public E set(int index, E value) {
        index = checkIndex(index);
        Node<E> tracker = findIndex(index);
        E prevValue = tracker.datum;
        tracker.datum = value;
        return prevValue;
    }

    //method returns the size of the list
    @Override
    public int size() {
        return this.size;
    }

    //method sorts the list using insertion sort.
    @Override
    public void sort() {
        if (isEmpty() || this.size == 1) {
            return;
        }
        Node<E> curr = this.head;

        int i = 0;
        while (curr.next != null) {
            int x = i;
            if (curr.next.datum.compareTo(get(i)) < 0) {
                while (get(x+1).compareTo(get(x)) < 0) {
                    E tmp = get(x);
                    set(x, get(x+1));
                    set(x+1, tmp);
                    x--;
                    if (x < 0) {
                        break;
                    }
                }
            }

            curr = curr.next;
            i++;
        }
    }

    //helper method is used to find the node of the given index
    private Node<E> findIndex(int index) {
        index = checkIndex(index);
        if (index >= 0) {
            Node<E> tracker = this.head;
            for (int i = 0; i < index; i++) {
                tracker = tracker.next;
            }
            return tracker;
        }

        return null;
    }

    //helper method is used to check whether the given index is considered a valid index
    private int checkIndex(int index) {
        int theIndex = index;
        if (theIndex < 0) {
            theIndex += this.size;
        }

        if (theIndex < 0 || theIndex > this.size - 1) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }

        return theIndex;
    }
}
