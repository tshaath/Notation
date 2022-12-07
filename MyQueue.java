import java.util.ArrayList;
import java.util.Queue;

public class MyQueue<T>implements QueueInterface<T> {
    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private int size;
    private int capacity;
    private int length;
    private static final int DEF_CAPACITY=100;
    /**
     * constructor that gives the user the ability to determine the desired capacity
     */
    public MyQueue(int initialCapacity)
    {
     queue=(T[])new Object[initialCapacity+1];
     size = 0;
     frontIndex=0;
     backIndex=initialCapacity;
     this.capacity = initialCapacity;
     this.length = capacity+1;
    }
    /**
     * Default constructor
     */
    public MyQueue()
    {
        this(DEF_CAPACITY);
    }
    /**
     * Determines if Queue is empty
     * @return true if Queue is empty, false if not
     */
    public boolean isEmpty()
    {
        return frontIndex == ((backIndex+1) % length);
    }
    /**
     * Determines of the Queue is Full
     * @return true if Queue is full, false if not
     */
    public boolean isFull()
    {
        return (backIndex-frontIndex+1)%length == capacity;
    }

    /**
     * Adds an element to the end of the Queue
     * @param e the element to add to the end of the Queue
     * @return true if the add was successful
     * @throws QueueOverflowException if queue is full
     */
    public boolean enqueue(T e) throws QueueOverflowException
    {
        if (isFull()){
            throw new QueueOverflowException();
        } else {
            backIndex = (backIndex+1)%length;
            queue[backIndex] = e;
            size+=1;
            return true;
        }
    }
    /**
     * Deletes and returns the element at the front of the Queue
     * @return the element at the front of the Queue
     * @throws QueueUnderflowException if queue is empty
     */
    public T dequeue() throws QueueUnderflowException
    {
        if (isEmpty())
            throw new QueueUnderflowException();
        else
        {
            T tempQueue= queue[frontIndex];
            queue[frontIndex]=null;
            frontIndex = (frontIndex + 1) % length;
            size--;
            return tempQueue;
        }
    }

    /**
     * Returns number of elements in the Queue
     * @return the number of elements in the Queue
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns the string representation of the elements in the Queue,
     * the beginning of the string is the front of the queue
     * @return string representation of the Queue with elements
     */
    public String toString()
    {
        return toString("");
    }
    /**
     * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
     * Place the delimiter between all elements of the Queue
     * @return string representation of the Queue with elements separated with the delimiter
     */
    public String toString(String delimiter)
    {
        String str="";
        int i = frontIndex;
        while (!(queue[i]==null))
        {
            str+=queue[i].toString();
            if (i != size()-1) {
                str += delimiter;
            }
            i = (i + 1) % length;

        }
        return str;
    }
    /**
     * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
     * is the first element in the Queue
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
     * list reference within your Queue, you will be allowing direct access to the data of
     * your Queue causing a possible security breech.
     * @param list elements to be added to the Queue
     * @throws QueueOverflowException if queue is full

     */
    public void fill(ArrayList<T> list) throws QueueOverflowException
    {
        for(T c: list) {
            enqueue(c);
        }
    }
}
