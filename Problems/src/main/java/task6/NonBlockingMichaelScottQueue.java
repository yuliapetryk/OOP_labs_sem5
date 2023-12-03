package task6;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingMichaelScottQueue<T> {
    private final AtomicReference<Node<T>> head;
    private final AtomicReference<Node<T>> tail;
    public NonBlockingMichaelScottQueue () {
        Node<T> node = new Node<>(null);
        head = new AtomicReference<>(node);
        tail = new AtomicReference<>(node);
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        while (true) {
            Node<T> last = tail.get();
            Node<T> next = last.getNext().get();
            if (last == tail.get()) {
                if (next == null) {
                    if (last.getNext().compareAndSet(null, newNode)) {
                        tail.compareAndSet(last, newNode);
                        return;
                    }
                } else {
                    tail.compareAndSet(last, next);
                }
            }
        }
    }

    public T dequeue() {
        while (true) {
            Node<T> first = head.get();
            Node<T> last = tail.get();
            Node<T> next = first.getNext().get();
            if (first == head.get()) {
                if (first == last) {
                    if (next == null) {
                        return null;
                    }
                    tail.compareAndSet(last, next);
                } else {
                    T value = next.getValue();
                    if (head.compareAndSet(first, next)) {
                        return value;
                    }
                }
            }
        }
    }
}
