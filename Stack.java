public class Stack {
    Node top;
    int size = 0;

    Stack() {
        this.top = null;
    }

    public void push(Node newNode) {
        newNode.next = top;
        top = newNode;
        size++;
    }

    public void pop()
    {
        if (top == null) {
            System.out.print("\nStack Underflow");
            return;
        }
        top = (top).next;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
