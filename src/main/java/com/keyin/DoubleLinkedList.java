
import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedList {
    Node head;
    Node tail;
    int size;
    UndoRedoManager undoRedoManager;

    public DoubleLinkedList(UndoRedoManager undoRedoManager) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.undoRedoManager = undoRedoManager;
    }

    private void saveState() {
        List<Integer> currentState = new ArrayList<>();
        Node current = head;
        while (current != null) {
            currentState.add(current.data);
            current = current.next;
        }
        undoRedoManager.saveState(new State(currentState));
    }

    // Create a new DLL with a single node
    public void createDLL(int nodeValue) {
        saveState();
        Node newNode = new Node(nodeValue);
        head = newNode;
        tail = newNode;
        size = 1;
    }

    // Insert a node at a specific location
    public void insertDLL(int nodeValue, int location) {
        saveState();
        Node newNode = new Node(nodeValue);
        if (head == null) {
            createDLL(nodeValue);
            return;
        } else if (location == 0) {
            newNode.next = head;
            newNode.prev = null;
            head.prev = newNode;
            head = newNode;
        } else if (location >= size) {
            newNode.next = null;
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node tempNode = head;
            for (int i = 0; i < location - 1; i++) {
                tempNode = tempNode.next;
            }
            newNode.next = tempNode.next;
            newNode.prev = tempNode;
            tempNode.next.prev = newNode;
            tempNode.next = newNode;
        }
        size++;
    }

    // Append a node to the end of the list
    public void append(int data) {
        saveState();
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    // Reverse traverse the list
    public void reverseTraverse() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node last = tail;
        while (last != null) {
            System.out.print(last.data + " ");
            last = last.prev;
        }
        System.out.println();
    }

    // Search for a value in the list
    public boolean search(int key) {
        Node current = head;
        while (current != null) {
            if (current.data == key) {
                System.out.println("Found node: " + current.data);
                return true;
            }
            current = current.next;
        }
        System.out.println("Node not found.");
        return false;
    }

    // Delete a node with a specific value
    public void deleteNode(int location) {
        saveState();
        if (head == null) {
            System.out.println("DLL does not exist");
            return;
        } else if (location == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
            if (size == 0) {
                tail = null;
            }
        } else if (location >= size - 1) {
            Node tempNode = tail;
            if (tempNode == head) {
                head = tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            size--;
        } else {
            Node tempNode = head;
            for (int i = 0; i < location - 1; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next = tempNode.next.next;
            if (tempNode.next != null) {
                tempNode.next.prev = tempNode;
            }
            size--;
        }
    }

    // Method to delete an entire DoubleLinkedList
    public void deleteDLL() {
        saveState();
        if (head == null) {
            System.out.println("DLL does not exist");
        } else {
            head = null;
            tail = null;
            size = 0;
            System.out.println("The DLL has been deleted.");
        }
    }

    // Display the list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
