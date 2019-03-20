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

        if (size > 0) {
            T elem = last.elem;
            last = last.prev;
            unbindNodes(last, last.next);
            size--;
            return elem;
        }
        return null;
    }

    @Override
    public MyCollection<T> clone() {
        return new NodeBaseStack<>(this);
    }

}
