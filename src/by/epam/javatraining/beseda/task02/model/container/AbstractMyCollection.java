package by.epam.javatraining.beseda.task02.model.container;

/**
 *
 * @author Beseda
 * @version 1.0 10/03/2019
 * @param <T> Argument of reference type
 */
public abstract class AbstractMyCollection<T> implements MyCollection<T> {

    protected int size;

    protected AbstractMyCollection() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    abstract public T peek();
    
    @Override
    abstract public T get();

    @Override
    abstract public boolean remove(T obj);

    @Override
    abstract public boolean add(T obj);

    @Override
    abstract public boolean contains(T obj);

    @Override
    abstract public void clear();

    @Override
    abstract public MyCollection<T> clone();

    @Override
    abstract public T[] toArray();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.size;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractMyCollection<?> other = (AbstractMyCollection<?>) obj;
        if (this.size != other.size) {
            return false;
        }
        return true;
    }

}
