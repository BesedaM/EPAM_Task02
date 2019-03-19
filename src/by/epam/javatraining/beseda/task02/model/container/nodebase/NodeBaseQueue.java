package by.epam.javatraining.beseda.task02.model.container.nodebase;

import by.epam.javatraining.beseda.task02.model.container.MyCollection;

/**
 *
 * @author Beseda
 * @version 1.0 11/03/2019
 */
public class NodeBaseQueue<T> extends AbstractNodeCollection<T> {

    public NodeBaseQueue() {
        super();
    }

    public NodeBaseQueue(AbstractNodeCollection<T> collection) {
        super(collection);
    }

    @Override
    public T peek() {
        T obj = null;
        if (first.next != null) {
            obj = first.next.elem;
        }
        return obj;
    }

    @Override
    public T get() {
        Node<T> temp;
        if (size > 1) {
            temp = first.next;
            bindNodes(first, temp.next);
            size--;
            return temp.elem;
        } else if (size == 1) {
            last = null;
            size--;
            return first.next.elem;
        } else {
            return null;
        }
    }

    @Override
    public MyCollection<T> clone() {
        return new NodeBaseQueue<>(this);
    }

}
