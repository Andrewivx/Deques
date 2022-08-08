package deques;

/**
 * @see Deque
 */
public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    Node<T> front;
    Node<T> back;
    // Feel free to add any additional fields you may need, though.

    public LinkedDeque() {
        size = 0;
        front = new Node<T>(null, null, null);
        back = new Node<T>(null, null, null);
        front.next = back;
        back.prev = front;
    }

    public void addFirst(T item) {
        size += 1;
        Node<T> first = new Node<T>(item, null, front.next);
        if (size != 0) {
            front.next = first;
            front.next.next.prev = first;
        }
        //if it's the only thing you need to put it in the center
        else {
            back.prev = first;
            front.next = first;
        }
        first.prev = front;
    }

    public void addLast(T item) {
        size += 1;
        Node<T> last = new Node<T>(item, back.prev, null);
        if (size != 0) {
            last.next = back;
            back.prev = last;
            back.prev.prev.next = last;
        }
        else {
            back.prev = last;
            last.next = back;
            front.next = last;
        }
    }

    //random comment so i can commit10
    public T removeFirst() {
        size -= 1;

        T holder = null;
        if (size < 0) {
            size = 0;
            return null;
        }
        if (size == 0) {
            holder = front.next.value;
            front.next = front.next.next;
            back.prev = back.prev.prev;
            return holder;
        }
        else {
            holder = front.next.value;
            front.next = front.next.next;
            front.next.prev = front;
            return holder;
        }
    }

    public T removeLast() {
        T holder = null;
        size -= 1;
        if (size < 0) {
            size = 0;
            return null;
        }
        if (size == 0) {
            holder = front.next.value;
            front.next = front.next.next;
            back.prev = back.prev.prev;
            return holder;
        }
        else {
            holder = back.prev.value;
            back.prev = back.prev.prev;
            back.prev.next = back;
            return holder;
        }
    }

    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        Node<T> temp = null;
        int mid = size / 2;
        if (mid > index) {
            int count = 0;
            temp = front.next;
            while (index > count) {
                temp = temp.next;
                count++;
            }
            T value = temp.value;
            return value;
        }
        else {
            temp = back.prev;
            int count = 0;
            int limit = size - (index + 1);
            while (limit > count) {
                temp = temp.prev;
                count++;
            }
            T value = temp.value;
            return value;
        }
    }

    public int size() {
        return size;
    }
}
