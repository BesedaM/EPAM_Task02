package by.epam.javatraining.beseda.task02.model.container.nodebase;

import by.epam.javatraining.beseda.task02.model.container.AbstractMyCollection;
import by.epam.javatraining.beseda.task02.model.container.MyCollection;
import java.util.ListIterator;

/**
 *
 * @author Beseda
 * @version 1.0 09/03/2019
 * @param <T> Argument of reference type
 */
public abstract class AbstractNodeCollection<T> extends AbstractMyCollection<T> {

    protected Node<T> first = null;
    protected Node<T> last = null;

    private ListIterator<T> iterator = null;

    public AbstractNodeCollection() {
        super();
        first = new Node<>();
    }

    public AbstractNodeCollection(AbstractNodeCollection<T> collection) {
        this();
        ListIterator<T> it = collection.getIterator();
        Node<T> nodePrev = null;
        Node<T> nodeNext = null;
        while (it.hasNext()) {
            nodeNext = new Node<>(it.next());
            if (it.hasPrevious()) {
                bindNodes(nodePrev, nodeNext);
            } else {
                bindNodes(first, nodeNext);
            }
            nodePrev = nodeNext;
        }
        last = nodeNext;
    }

    private class MyIterator implements ListIterator<T> {

        private Node<T> node = null;
        private int index = 0;

        public MyIterator(AbstractNodeCollection<T> collection) {
            this.node = collection.first;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                node = node.next;
                index++;
                return node.elem;
            } else {
                return null;
            }
        }

        @Override
        public boolean hasPrevious() {
            return node != first;
        }

        @Override
        public T previous() {
            T elem;
            if (hasPrevious()) {
                elem = node.elem;
                node = node.prev;
                index--;
                return elem;
            } else {
                return null;
            }
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            index--;
            size--;
            if (hasPrevious()) {
                if (hasNext()) {
                    bindNodes(node.prev, node.next);
                } else {
                    node.prev.next = null;
                }
            }
        }

        @Override
        public void set(T elem) {
            this.node.elem = elem;
        }

        @Override
        public void add(T elem) {
            Node<T> newNode = new Node<>(elem);
            size++;
            index++;
            if (hasNext()) {
                if (hasPrevious()) {
                    bindNodes(newNode, node.next);
                    bindNodes(node, newNode);
                } else {
//                    bindNodes(newNode, first.next);
                    bindNodes(first, newNode);
                }
            } else if (hasPrevious()) {
                bindNodes(node, newNode);
                node = newNode;
            }
        }
    }

    protected class Node<T> {

        public T elem;
        public Node<T> next;
        public Node<T> prev;

        public Node() {
        }

        public Node(T obj) {
            this.elem = obj;
        }

        @Override
        public String toString() {
            return elem.toString();
        }
    }

    protected final void bindNodes(Node<T> previous, Node<T> next) {
        previous.next = next;
        next.prev = previous;
    }

    protected final void unbindNodes(Node<T> previous, Node<T> next) {
        next.prev = null;
        previous.next = null;
    }

    public ListIterator<T> getIterator() {
        return new MyIterator(this);
    }

    @Override
    abstract public T peek();

    @Override
    abstract public T get();

    @Override
    public boolean add(T obj) {
        if (obj != null) {
            if (last == null) {
                last = new Node<>(obj);
                bindNodes(first, last);
            } else {
                bindNodes(last, new Node<>(obj));
                last = last.next;
            }
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T obj) {
        if (obj != null) {
            iterator = this.getIterator();
            while (iterator.hasNext()) {
                if (obj.equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        last = null;
        first.next = null;
        size = 0;
    }

    @Override
    abstract public MyCollection<T> clone();

    @Override
    public T[] toArray() {
        Object[] arr = new Object[size];
        iterator = this.getIterator();
        while (iterator.hasNext()) {
            arr[iterator.nextIndex()] = iterator.next();
        }
        return (T[]) arr;
    }

    @Override
    public boolean remove(T obj) {
        if (contains(obj)) {
            iterator.remove();
            return true;
        }
        return false;
    }

}
