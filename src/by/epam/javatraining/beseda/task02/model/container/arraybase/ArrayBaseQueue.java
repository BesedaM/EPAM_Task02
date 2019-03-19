package by.epam.javatraining.beseda.task02.model.container.arraybase;

import by.epam.javatraining.beseda.task02.model.container.MyCollection;
import java.util.Arrays;
import org.apache.log4j.Logger;

/**
 *
 * @author Beseda
 * @version 1.1 19/03/2019
 */
public class ArrayBaseQueue<T> extends AbstractArrayCollection<T> {

    private int start;
    private int end;

    {
        start = -1;
        end = -1;
    }

    public ArrayBaseQueue() {
    }

    public ArrayBaseQueue(int size) {
        super(size);
    }

    public ArrayBaseQueue(AbstractArrayCollection<T> collection) {
        super(collection);
    }
    
    @Override
    public T peek() {
        T obj = null;
        if (start >= 0) {
            obj = (T) arr[start];
        }
        return obj;
    }

    @Override
    public T get() {
        T obj = null;
        if (this.size > 0) {
            obj = (T) arr[start];
//            Logger.getLogger(this.getClass().getSimpleName()).trace("getting " + obj + " at [" + start + "] ");
            arr[start] = null;
            this.size--;
//            Logger.getLogger(this.getClass().getSimpleName()).trace("size: " + this.size);
            if (this.size == 0) {                             // !!!st=end!!!!!!
                start = -1;
                end = -1;
            } else if (start == this.MAX_SIZE - 1) {        // !!!end!!!!!!!st
                start = 0;
            } else {                                        // !!end!!!!st!!!! || !!!st!!!!end!!!
                start++;
            }
        }
        return obj;
    }

    @Override
    public boolean add(T obj) {
        if (obj != null) {
            end++;
//            Logger.getLogger(this.getClass().getSimpleName()).trace("adding " + obj + " at [" + end + "]");
            if ((end == this.MAX_SIZE - 1) && (start == 0)) {
                this.extendBase();
            } else if (this.size == this.MAX_SIZE) {        //  !!!st,end!!!!!
                relocateQueue();
                start = 0;
                end = this.size - 1;
            }
            this.arr[end] = obj;
            if (start == -1) {                              //  .............
                start = 0;
            } else if (end == this.MAX_SIZE - 1) {          //  !!!st!!!!!!end
                end = -1;
            }
            this.size++;
            return true;
        } else {
            return false;
        }
    }

    private void relocateQueue() {
        Object[] newArray = new Object[this.MAX_SIZE];
        for (int i = start; i < this.MAX_SIZE; i++) {
            newArray[i - start] = arr[i];
        }
        for (int i = 0; i <= end; i++) {
            newArray[i + this.MAX_SIZE - start - 1] = arr[i];
        }
        this.arr = newArray;
        start = 0;
        end = this.MAX_SIZE - 1;
        this.extendBase();
    }

    @Override
    public void clear() {
        super.clear();
        this.start = -1;
        this.end = 0;
    }

    @Override
    public boolean remove(T obj) {
        int index = this.getIndex(obj);
        if (index >= 0) {
            if (this.size == 1) {           //  !!!!!st=end!!!!!
                arr[index] = null;
                start = -1;
                end = -1;
            } else if (index >= start && index <= end) {    //  !!st!!!!index!!!!end!!
                for (int i = index; i < end; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[end] = null;
                end--;
            } else if (index <= end) {                      //  !!index!!!!!end!!!!st!!
                if (index == end && end == 0) {
                    arr[index] = null;
                    end = this.MAX_SIZE - 1;
                } else {
                    for (int j = index; j < end; j++) {
                        arr[j] = arr[j + 1];
                    }
                    arr[end] = null;
                    end--;
                }
            } else if (index >= start) {                    //  !!end!!!!!!st!!!index!!
                if (index == start && start == this.MAX_SIZE - 1) {
                    arr[index] = null;
                    start = 0;
                } else {
                    for (int j = start; j < index; j++) {
                        arr[j + 1] = arr[j];
                    }
                    arr[start] = null;
                    start++;
                }
            }
            this.size--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MyCollection<T> clone() {
        return new ArrayBaseQueue<>(this);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.start;
        hash = 17 * hash + this.end;
        return hash + 17 * super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final ArrayBaseQueue<?> other = (ArrayBaseQueue<?>) obj;
        if (this.start != other.start) {
            return false;
        }
        if (this.end != other.end) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArrayBaseQueue{ length: " + this.size + "}/n"
                + Arrays.deepToString(arr);
    }

}
