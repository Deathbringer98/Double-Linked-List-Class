
public class Main {
    public static void main(String[] args) {
        UndoRedoManager undoRedoManager = new UndoRedoManager();
        DoubleLinkedList dll = new DoubleLinkedList(undoRedoManager);

        dll.append(10);
        dll.append(20);
        dll.append(30);
        dll.append(40);
        dll.append(50);

        System.out.println("Original List:");
        dll.display();

        System.out.println("Inserting 25 at location 3");
        dll.insertDLL(25, 3);
        dll.display();

        System.out.println("Reverse Traversal:");
        dll.reverseTraverse();

        int searchKey = 30;
        System.out.println("Search for " + searchKey + ": " + dll.search(searchKey));

        int deleteKey = 2;
        System.out.println("Deleting node at location " + deleteKey);
        dll.deleteNode(deleteKey);
        dll.display();

        System.out.println("Deleting entire list");
        dll.deleteDLL();
        dll.display();

        // Undo and Redo operations
        System.out.println("Undoing the last operation");
        undoRedoManager.undo();
        dll = new DoubleLinkedList(undoRedoManager);  // Rebuild the DLL from the state
        rebuildListFromState(dll, undoRedoManager.getCurrentState());
        dll.display();

        System.out.println("Redoing the last operation");
        undoRedoManager.redo();
        dll = new DoubleLinkedList(undoRedoManager);  // Rebuild the DLL from the state
        rebuildListFromState(dll, undoRedoManager.getCurrentState());
        dll.display();
    }

    private static void rebuildListFromState(DoubleLinkedList dll, State state) {
        for (int value : state.getState()) {
            dll.append(value);
        }
    }
}
