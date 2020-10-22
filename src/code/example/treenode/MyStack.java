package code.example.treenode;

// Creating our own MyStack class using Layers - Each layers represents one element in MyStack
public class MyStack<T> {

    private Layers<T> top;
    private int size;

    // Check the stack if it's not empty return the top layer's value in it
    public T peek() throws Exception {
        checkNotEmpty();
        return top.getValue();
    }

    // Add new element to stack
    public void push(T val) {
        Layers<T> current = new Layers<>(val);
        current.setNext(top);
        top = current;
        size++;
    }

    // Remove the element from stack
    public T pop() throws Exception {
        checkNotEmpty();

        T poppedElement = top.getValue();
        top = top.getNext();
        size--;

        return poppedElement;
    }

    // Check the size if it is equal to zero return true
    public boolean isEmpty() {
        return size == 0;
    }

    // Check the size if it is equal to zero throw an exception
    private void checkNotEmpty() throws Exception {
        if (size == 0) {
            throw new Exception();
        }
    }
}