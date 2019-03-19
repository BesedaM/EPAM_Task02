package by.epam.javatraining.beseda.task02.model.container.arraybase;

import by.epam.javatraining.beseda.task02.model.container.MyCollection;
import java.util.Arrays;

/**
 *
 * @author Beseda
 * @version 1.0 11/03/2019
 */
public class ArrayBaseStack<T> extends AbstractArrayCollection<T> {

    public ArrayBaseStack() {
    }

    public ArrayBaseStack(int size) {
        super(size);
    }

    public ArrayBaseStack(AbstractArrayCollection<T> collection) {
        super(collection);
    }

    @Override
    public T peek() {
        T obj = null;
        if (this.size != 0) {
            obj = (T) arr[size];
        }
        return obj;
    }

    @Override
    public T get() {
        T obj = null;
        if (this.size != 0) {
            this.size--;
            obj = (T) arr[size];
        }
        return obj;
    }

    @Override
    public boolean add(T obj) {
        if (obj != null) {
            this.arr[size] = obj;
            size++;
            if (this.size == this.MAX_SIZE) {
                this.extendBase();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MyCollection<T> clone() {
        return new ArrayBaseStack<>(this);
    }

    @Override
    public String toString() {
        return "ArrayBaseStack{ size: " + this.size + "}\n" 
                + Arrays.deepToString(arr);
    }

}
