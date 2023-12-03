package task6;

import java.util.concurrent.atomic.AtomicReference;

class Node<T> {
   private final  T value;
    private final AtomicReference<Node<T>> next;

    public Node(T value) {
        this.value = value;
        this.next = new AtomicReference<>(null);
    }

    public T getValue(){
        return this.value;
    }

    public AtomicReference<Node<T>> getNext(){
        return this.next;
    }

}
