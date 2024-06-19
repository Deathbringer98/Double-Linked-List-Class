
import java.util.Stack;

public class UndoRedoManager {
    Stack<State> undoStack;
    Stack<State> redoStack;
    State currentState;

    public UndoRedoManager() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        currentState = new State(new ArrayList<>());
    }

    public void saveState(State state) {
        undoStack.push(currentState);
        currentState = state;
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentState);
            currentState = undoStack.pop();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentState);
            currentState = redoStack.pop();
        }
    }

    public State getCurrentState() {
        return currentState;
    }
}
