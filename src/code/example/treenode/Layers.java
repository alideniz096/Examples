package code.example.treenode;

// Need Layers class for generify our MyStack class
public class Layers<T> {

    private T value;
    private Layers<T> next;

    Layers(T val) {
        this.value = val;
    }

    T getValue() {
        return value;
    }

    Layers<T> getNext() {
        return next;
    }

    void setNext(Layers<T> nextLayer) {
        this.next = nextLayer;
    }
}