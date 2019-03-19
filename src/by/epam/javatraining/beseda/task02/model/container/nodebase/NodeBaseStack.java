package by.epam.javatraining.beseda.task02.model.container.nodebase;

import by.epam.javatraining.beseda.task02.model.container.MyCollection;

/**
 *
 * @author Beseda
 * @version 1.0 11/03/2019
 */
public class NodeBaseStack<T> extends AbstractNodeCollection<T> {

    public NodeBaseStack() {
        super();
    }

    public NodeBaseStack(AbstractNodeCollection<T> collection) {
        super(collection);
    }

    @Override
    public T peek() {
        if (last != null) {
            return last.elem;
        }
        return null;
    }
    

    @Override
    public T get() {
        Node<T> temp = last;
        if (last != null) {
            last = last.prev;
            last.next = null;
            return temp.elem;
        }
        return null;
    }

    @Override
    public MyCollection<T> clone() {
        return new NodeBaseStack<>(this);
    }

}
