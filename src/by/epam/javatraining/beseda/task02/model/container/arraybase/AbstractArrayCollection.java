package by.epam.javatraining.beseda.task02.model.container.arraybase;

import by.epam.javatraining.beseda.task02.model.container.AbstractMyCollection;
import by.epam.javatraining.beseda.task02.model.container.MyCollection;
import java.util.Arrays;
import java.util.ListIterator;

/**
 *
 * @author Beseda
 * @version 1.0 09/03/2019
 * @param <T> Argument of reference type
 */
public abstract class AbstractArrayCollection<T> extends AbstractMyCollection<T> {

    public final int DEFAULT_SIZE = 16;

    protected int MAX_SIZE;
    protected Object[] arr;

    private ListIterator<T> iterator = null;

    public AbstractArrayCollection() {
        super();
        this.MAX_SIZE = DEFAULT_SIZE;
        arr = new Object[MAX_SIZE];
    }

    public AbstractArrayCollection(int size) {
        super();
        if (size > 0) {
            this.MAX_SIZE = size;
        } else {
            this.MAX_SIZE = DEFAULT_SIZE;
        }
        arr = new Object[MAX_SIZE];
    }

    private class MyIterator<T> implements ListIterator<T> {

        private int index = -1;
        AbstractArrayCollection<T> collection;

        public MyIterator(AbstractArrayCollection<T> collection) {
            this.collection = collection;
        }

        @Override
        public boolean hasNext() {
            return index < size - 1;
        }

        @Override
        public T next() {
            if (index < size - 1) {
                return (T) collection.arr[++index];
            }
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return index >= 0;
        }

        @Override
        public T previous() {
            if (index >= 0) {
                return (T) collection.arr[index];
            }
            return null;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index;
        }

        @Override
        public void remove() {
            if (hasPrevious()) {
                if (index < size) {
                    size--;
                    for (int i = index; i < size - 1; i++) {
                        collection.arr[i] = collection.arr[i + 1];
                    }
                    collection.arr[size] = null;
                }
            }
        }

        @Override
        public void set(T element) {
            if (element != null) {
                arr[index] = element;
            }
        }

        @Override
        public void add(T element) {
            if (element != null) {
                index++;
                if (size != arr.length) {
                    for (int i = size - 1; i <= index; i--) {
                        collection.arr[i] = collection.arr[i - 1];
                    }
                } else {
                    collection.extendBase();
                    collection.arr[index] = element;
                }
            }
        }

    }

    public AbstractArrayCollection(AbstractArrayCollection<T> collection) {
        this.MAX_SIZE = collection.MAX_SIZE;
        this.size = collection.size;
        System.arraycopy(collection.arr, 0, this.arr, 0, this.size);
    }

    protected void extendBase() {
        this.MAX_SIZE = (int) (this.MAX_SIZE * (3.0 / 2));
        this.changeBaseSize(this.MAX_SIZE);
    }

    protected void trimBaseToCurrentSize() {
        this.MAX_SIZE = this.size;
        this.changeBaseSize(this.size);
    }

    protected void changeBaseSize(int size) {
        Object[] newArr = new Object[size];
        System.arraycopy(this.arr, 0, newArr, 0, this.arr.length);
        this.arr = newArr;
    }

    protected ListIterator<T> getIterator() {
        return new MyIterator<>(this);
    }

    @Override
    abstract public T peek();

    @Override
    abstract public T get();

    @Override
    abstract public boolean add(T obj);

    @Override
    public boolean contains(T obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
        arr = new Object[MAX_SIZE];
    }

    @Override
    public boolean remove(T obj) {
        if (obj != null) {
            for (int i = 0; i < size; i++) {
                if (arr[i].equals(obj)) {
                    for (int j = i + 1; j < size; j++) {
                        arr[j - 1] = arr[j];
                    }
                    arr[size - 1] = null;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    protected int getIndex(T obj) {
        if (obj != null) {
            for (int i = 0; i < this.MAX_SIZE; i++) {
                if (obj.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    abstract public MyCollection<T> clone();

    @Override
    public T[] toArray() {
        Object[] arrCopy = new Object[this.size];
        System.arraycopy(this.arr, 0, arrCopy, 0, this.size);
        return (T[]) arrCopy;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 7 * hash + this.DEFAULT_SIZE;
        hash = 7 * hash + this.MAX_SIZE;
        hash = 7 * hash + Arrays.deepHashCode(this.arr);
        return hash + 7 * super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (!super.equals(obj)) {
            return false;
        }
        final AbstractArrayCollection<?> other = (AbstractArrayCollection<?>) obj;
        if (this.DEFAULT_SIZE != other.DEFAULT_SIZE) {
            return false;
        }
        if (this.MAX_SIZE != other.MAX_SIZE) {
            return false;
        }
        if (!Arrays.deepEquals(this.arr, other.arr)) {
            return false;
        }
        return true;
    }

}
