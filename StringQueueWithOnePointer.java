import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueWithOnePointer implements StringQueue{

    private Node tail;
    private int size;

    private class Node{
        private Node next;
        private String data;


        public Node(String data){
            this.data = data;
        }
    }

    public StringQueueWithOnePointer(){
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }


    public void put(String data) {
        Node temp = new Node(data);
        if(isEmpty()){
            tail = temp;
        }else{
            if(tail.next == null){
                tail.next = temp;
                temp.next = tail;
            }else{
                temp.next = tail.next;
                tail.next = temp;
            }
        }
        tail = temp;
        size++;
        
    }


    public String get() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("Queue is already empty!");
        }

        Node temp = tail.next;
        String result = temp.data;
        if(tail.next == null){
            tail = null;
        }else{
            tail.next = temp.next;
        }
        size--;
        return result;

    }


    public String peek() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("Queue is already empty");
        }
        return tail.next.data;
    }


    public void printQueue(PrintStream stream) {
        if(tail == null){
            return;
        }

        Node head = tail.next;
        while(head!=tail){
            System.out.print(head.data+ " --> ");
            head=head.next;
        }
        System.out.println(head.data+" --> again");
    }


}


            

