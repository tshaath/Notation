import java.util.ArrayList;

/**
 * @author Tariq Shaath
 */
public class MyStack<T> implements StackInterface<T> {
    private static final int DEF_CAPACITY = 100;
    /**
     * @param <T> data type
     */
    private final T[] stack;
    private int topIndex = -1;

    /**
     * constructor that gives the user the ability to determine the desired capacity
     */
    public MyStack(int capacity) {
        stack = (T[]) new Object[capacity];//Creating a stack of objects with the desired capacity
    }

    /**
     * default constructor
     */

    public MyStack() {
        this(DEF_CAPACITY);
    }

    /**
     * Determines if Stack is empty
     *
     * @return true if Stack is empty, false if not
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Determines if Stack is full
     *
     * @return true if Stack is full, false if not
     */
    public boolean isFull() {
        return topIndex == stack.length-1;
    }

    /**
     * Deletes and returns the element at the top of the Stack
     *
     * @return the element at the top of the Stack
     * @throws StackUnderflowException if stack is empty
     */
    public T pop() throws StackUnderflowException {
        if (isEmpty())
            throw new StackUnderflowException();
        else {
            T tempObject = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return tempObject;

        }
    }

    /**
     * Returns the element at the top of the Stack, does not pop it off the Stack
     *
     * @return the element at the top of the Stack
     * @throws StackUnderflowException if stack is empty
     */
    public T top() throws StackUnderflowException {
        return stack[topIndex];
    }

    /**
     * Number of elements in the Stack
     *
     * @return the number of elements in the Stack
     */
    public int size() {
        return topIndex+1;
    }

    /**
     * Adds an element to the top of the Stack
     *
     * @param e the element to add to the top of the Stack
     * @return true if the add was successful, false if not
     * @throws StackOverflowException if stack is full
     */
    public boolean push(T e) throws StackOverflowException {
        if (isFull())
            throw new StackOverflowException();
        else {
            topIndex++;
            stack[topIndex] = e;
            return true;
        }
    }

    /**
     * Returns the elements of the Stack in a string from bottom to top, the beginning
     * of the String is the bottom of the stack
     *
     * @return an string which represent the Objects in the Stack from bottom to top
     */
    public String toString() {
        return toString("");
    }

    /**
     * Returns the string representation of the elements in the Stack, the beginning of the
     * string is the bottom of the stack
     * Place the delimiter between all elements of the Stack
     *
     * @return string representation of the Stack from bottom to top with elements
     * separated with the delimiter
     */
    public String toString(String delimiter) {
        String str = "";
        for (int i = 0; i < size(); i++) {
            str += stack[i].toString();
            if (i != size()-1) {
                str += delimiter;
            }
        }
        return str;
    }

    /**
     * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
     * is the first bottom element of the Stack
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
     * list reference within your Stack, you will be allowing direct access to the data of
     * your Stack causing a possible security breech.
     *
     * @param list elements to be added to the Stack from bottom to top
     * @throws StackOverflowException if stack gets full
     */
    public void fill(ArrayList<T> list) throws StackOverflowException {
        if (list.size() > stack.length) {
            throw new StackOverflowException();
        }
        for (T t : list) {
            push(t);
        }
    }


}
