import java.util.Scanner;

public class Josephus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //input
        int num = scanner.nextInt();
        
        for (int k = 1; k <= num; k++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
        
            CircLinkedList circList = new CircLinkedList(1); //List creation
            for (int i = 2; i <= n; i++) {
                circList.append(i);
            }

            Node current = circList.head(); //Josephus problem solution
            Node nextNode;
            while (circList.length() != 1) {
                nextNode = circList.findNextNode(current, m);
                if (nextNode == current) {
                    nextNode = current.next;
                }

                current = circList.removeNode(nextNode);
            }

            String output = "Usando n=" + n + ", m=" + m + ", resultado=" + circList.head().data;
            System.out.println(output);
        }
        scanner.close();
    }
}

class Node { 
    public int data; //Defined as public to make the implementation easier. For this problem, in my opinion, there's no need to use getters and setters.
    public Node next;
}

class CircLinkedList {
    Node head;
    int length;

    public CircLinkedList(int headData) { //Constructor.
        this.head = new Node();
        this.head.data = headData;
        this.head.next = head;
        this.length += 1;
    }

    public int length() {
        return this.length;
    }

    public Node head() {
        return this.head;
    }

    public void append(int data) { //Inserts a node to the end of the linked list.
        Node newNode = new Node();
        newNode.data = data;
        this.length += 1;

        Node itr = this.head;
        if (itr.next == this.head) {
            itr.next = newNode;
            newNode.next = this.head;

            return;
        } else {
            while (itr.next != this.head) {
                itr = itr.next;
            }
            itr.next = newNode;
            newNode.next = this.head;

            return;
        }
    }

    public Node findNextNode(Node current, int m) {
        int counter = 0;
        Node nextNode = current;

        while (counter < m) {
            nextNode = nextNode.next;

            counter += 1;
        }
        return nextNode;
    }

    public Node removeNode(Node nodeToRemove) {
        Node current = this.head;

        while (current.next != nodeToRemove) {
            current = current.next;
        }

        if (nodeToRemove == this.head) {
            this.head = nodeToRemove.next;
        }

        current.next = nodeToRemove.next;
        this.length -= 1;

        return current.next;
    }

    public String toString() {
        Node node = head;
        String objectString = "";

        while (node.next != this.head) {
            objectString += "" + node.data + "->" + " ";
            node = node.next;
        }
        objectString += "" + node.data;

        return objectString;
    }
}
