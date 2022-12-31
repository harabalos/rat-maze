import java.io.PrintStream;
import java.util.NoSuchElementException;


public class StringStackImpl implements StringStack{
    
    private Node head;
    private int size;

    private class Node {
        private String data; 
        private Node next;

        public Node(String data) {
            this.data = data; 
        } 
    }

    public StringStackImpl(){
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {

        return (head == null); 
    }

    public void push(String data) {
        Node temp = new Node(data);
        temp.next = head;
        head = temp;
        size++;
    }

    public String pop() {
        if(isEmpty()){
            throw new NoSuchElementException("Stack is already empty");
        }
        String result = head.data;
        head = head.next;
        size--;
        return result;
    } 

    public String peek() {
        if(isEmpty()){
            throw new NoSuchElementException("Stack is already empty");
        }
        return head.data;
    }

    public void printStack(PrintStream stream) {
        if(isEmpty()){
            return;
        }
        Node current = head;
        while(current!=null){
            stream.println(current.data + " ");
            current = current.next;
        }

        
    }

    public int size() {
            return size;

    }


}
