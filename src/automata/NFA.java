package automata;

import javafx.util.Pair;
import org.antlr.v4.semantics.SymbolChecks;
import regx.RegxStateGraph;

import java.util.*;

public class NFA {
    public int initial_state;
    public Set<Integer> final_states;
    public Map<Integer, String> state2name;
    public Map<String, Integer> name2State;
    public Set<Integer> states;

    public Set<String> input_symbols = new HashSet<>();
    public Map<Pair<Integer, String>, Set<Integer>> transitions = new HashMap<>();

    private String epsilon = "&epsilon;";

    public NFA(Set<Integer> states, Set<String> input_symbols, Map<Pair<Integer, String>,Set<Integer>> transitions,
               int initial_state, Set<Integer> final_states, Map<Integer, String> state_names) {
        this.states = states;
        this.input_symbols = input_symbols;
        this.transitions = transitions;
        this.initial_state = initial_state;
        this.final_states = final_states;
        this.state2name = state_names;
        this.name2State = new HashMap<>();
        for(Integer key : state_names.keySet()) {
            this.name2State.put(state_names.get(key), key);
        }
    }

    public Set<Integer> closure(int start_state) {
        Set<Integer> new_states = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start_state);

        int state;
        while(!stack.empty())
        {
            state = stack.pop();
            if(!new_states.contains(state)) {
                new_states.add(state);
                if(transitions.containsKey(new Pair<>(state, epsilon))){
                    stack.addAll(transitions.get(new Pair<>(state, epsilon)));
                }
            }
        }
        return new_states;
    }

    public Set<Integer> nextStates(Set<Integer> states, String input_symbol) {
        Set<Integer> new_states = new HashSet<>();

        for(Integer state : states) {
            if(transitions.containsKey(new Pair<>(state, input_symbol))) {
                Set<Integer> symbol_end_states = transitions.get(new Pair<>(state, input_symbol));
                for(Integer end_state : symbol_end_states) {
                    new_states.addAll(closure(end_state));
                }
            }
        }

        return new_states;
    }

    static public String stringfyStates(List<Integer> states) {
        states.sort(null);
        return states.toString();
    }
}
