package by.epam.javatraining.beseda.task02.model.container;

/**
 *
 * @author Beseda
 * @version 1.1 19/03/2019
 * @param <T> Argument of reference type
 */
public interface MyCollection<T> {

    /**
     *
     * @return collections actual size
     */
    int size();

    /**
     * Defines whether the collection is empty
     *
     * @return true if the collection is empty, else - false
     */
    boolean isEmpty();

    
    /**
     * Method retrieves an element from collection, without removing it
     * 
     * @return the next element of collection
     */
    T peek();
    
    /**
     * Method retrieves an element from collection, removing it
     *
     * @return the next element of collection
     */
    T get();

    /**
     * Removes a specified element of collection
     *
     * @param obj Element to remove
     * @return true if the element was removed
     */
    boolean remove(T obj);

    /**
     * Adds an element to collection
     *
     * @param obj Element to add
     * @return true if the element was added
     */
    boolean add(T obj);

    /**
     * Defines whether the collection contains the certain element
     *
     * @param obj Searching element
     * @return true or false
     */
    boolean contains(T obj);

    /**
     * Clears the collection. Sets the size of collection to null
     */
    void clear();

    /**
     * Returns a clone of collection
     *
     * @return new collection object
     */
    MyCollection<T> clone();

    /**
     * Returns an array representation of collection
     *
     * @return an array, representing all the collection's elements
     */
    T[] toArray();
}
