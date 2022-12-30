import java.io.PrintStream;
import java.util.NoSuchElementException;


public class StringQueueImpl implements StringQueue{

    private Node head;
    private Node tail;
    private int size;

    public StringQueueImpl(){
        this.head = null;
        this.tail = null;
        this.size=0;
    }

    private class Node{
        private String data;
        private Node next;

        public Node(String data){
            this.data = data;
            this.next = null;
        }
    }


    public boolean isEmpty() {
        return (size==0);
    }

    public void put(String data) {
        Node temp = new Node(data);
        if(isEmpty()){
            head = temp;
        }else{
            tail.next = temp;
        }

        tail = temp;
        size++;
    }

    public String get(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is already empty");
        }

        String result = head.data;
        head = head.next;
        if(head == null){
            tail = null;
        }
        size--;
        return result;
    }

    public String peek() {
        if(isEmpty()){
            throw new NoSuchElementException("Queue is already empty");
        }
        return head.data; 
    }

    public void printQueue(PrintStream stream) {
        if(isEmpty()){
            return;
        }
        Node current = head;
        while(current!=null){
            stream.print(current.data + " --> ");
            current = current.next;
        }
        stream.println("null");
        
    }

    public int size() {
        
        return size;
    }

}
