
import java.util.ArrayList;
import java.util.List;

public class State {
    List<Integer> state;

    public State(List<Integer> state) {
        this.state = new ArrayList<>(state);
    }

    public List<Integer> getState() {
        return state;
    }
}
