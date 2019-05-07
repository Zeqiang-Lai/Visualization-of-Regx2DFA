package automata;

import javafx.util.Pair;

import java.util.*;

public class DFA {
    public int initial_state;
    public Set<Integer> final_states;
    public Map<Integer, String> state2name;
    public Map<String, Integer> name2State;
    public Set<Integer> states;

    public Set<String> input_symbols = new HashSet<>();
    public Map<Pair<Integer, String>, Set<Integer>> transitions = new HashMap<>();

    public DFA(Set<Integer> states, Set<String> input_symbols, Map<Pair<Integer, String>, Set<Integer>> transitions,
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

    static public DFA from(NFA nfa) {
        int dfa_initial_state = 0;
        Set<Integer> dfa_final_states = new HashSet<>();
        Set<Integer> dfa_states = new HashSet<>();

        Map<Integer, String> dfa_state2name = new HashMap<>();
        Map<String, Integer> dfa_name2state = new HashMap<>();

        Map<Pair<Integer, String>, Set<Integer>> dfa_transitions = new HashMap<>();

        Set<String> dfa_input_symbols = nfa.input_symbols;

        Set<Integer> nfa_initial_states = nfa.closure(nfa.initial_state);
        dfa_states.add(dfa_initial_state);
        dfa_state2name.put(dfa_initial_state, NFA.stringfyStates(new ArrayList<>(nfa_initial_states)));
        dfa_name2state.put(NFA.stringfyStates(new ArrayList<>(nfa_initial_states)), dfa_initial_state);

        // 2. construct dfa states
        Queue<Set<Integer>> queue = new LinkedList<>();
        queue.add(nfa_initial_states);

        Set<Integer> current_states;
        String current_states_name;

        Map<String, Integer> visit = new HashMap<>();

        while (!queue.isEmpty()) {
            current_states = queue.poll();
            current_states_name = NFA.stringfyStates(new ArrayList<>(current_states));

            Set<Integer> intersect = new HashSet<>(current_states);
            intersect.retainAll(nfa.final_states);
            if(!intersect.isEmpty()) {
                dfa_final_states.add(dfa_name2state.get(current_states_name));
            }

            if(visit.containsKey(current_states_name))
                continue;

            enqueue_next_current_states(nfa, current_states, dfa_name2state.get(current_states_name),
                    queue, dfa_states, dfa_state2name, dfa_name2state, dfa_transitions);
            visit.put(current_states_name, 1);
        }

        return new DFA(dfa_states, dfa_input_symbols, dfa_transitions,
                dfa_initial_state, dfa_final_states, dfa_state2name);
    }

    static private void enqueue_next_current_states(NFA nfa, Set<Integer> current_states, Integer current_state_id,
                                                    Queue<Set<Integer>> queue,  Set<Integer> dfa_states,
                                                    Map<Integer, String> dfa_state2name, Map<String, Integer> dfa_name2state,
                                                    Map<Pair<Integer, String>, Set<Integer>> dfa_transitions) {
        Set<Integer> next_current_states;
        Integer next_current_states_id;
        String next_current_states_name;

        for(String input_symbol : nfa.input_symbols) {
            next_current_states = nfa.nextStates(current_states, input_symbol);
            if(next_current_states.isEmpty())
                continue;

            next_current_states_name = NFA.stringfyStates(new ArrayList<>(next_current_states));

            if(dfa_name2state.containsKey(next_current_states_name)) {
                next_current_states_id = dfa_name2state.get(next_current_states_name);
            } else {
                next_current_states_id = dfa_states.size();
                dfa_states.add(next_current_states_id);
                dfa_state2name.put(next_current_states_id, next_current_states_name);
                dfa_name2state.put(next_current_states_name, next_current_states_id);
            }

            if(!dfa_transitions.containsKey(new Pair<>(current_state_id, input_symbol)))
                dfa_transitions.put(new Pair<>(current_state_id, input_symbol), new HashSet<>());

            dfa_transitions.get(new Pair<>(current_state_id, input_symbol)).add(next_current_states_id);

            queue.add(next_current_states);
        }
    }
}
