package automata;

import javafx.util.Pair;

import java.lang.reflect.Parameter;
import java.util.*;

public class DFA {
    public int initial_state;
    public Set<Integer> final_states;
    public Map<Integer, String> state2name;
    public Map<String, Integer> name2State;
    public Set<Integer> states;

    public Set<String> input_symbols = new HashSet<>();
    public Map<Pair<Integer, String>, Integer> transitions = new HashMap<>();

    private Integer dead_state = -1;

    public DFA(Set<Integer> states, Set<String> input_symbols, Map<Pair<Integer, String>, Integer> transitions,
               int initial_state, Set<Integer> final_states, Map<Integer, String> state_names) {
        this.states = states;
        this.input_symbols = input_symbols;
        this.transitions = transitions;
        this.initial_state = initial_state;
        this.final_states = final_states;
        this.state2name = state_names;
        this.name2State = new HashMap<>();
        for (Integer key : state_names.keySet()) {
            this.name2State.put(state_names.get(key), key);
        }
    }

    public DFA minimize() {
        // TODO: 1. Remove unreachable states.

        // 2. Remove nondistinguishable states using Hopcroft's algorithm.
        List<Set<Integer>> partition = new LinkedList<>();

        Set<Integer> p = new HashSet<>(states);
        p.removeAll(final_states);
        partition.add(final_states);
        partition.add(p);

        List<Set<Integer>> new_partition = get_new_partition(partition);
        while (!new_partition.equals(partition)) {
            partition = new_partition;
            new_partition = get_new_partition(partition);
        }
        // 3. Construct minimized graph with the new partition.
        // Build the hash map of equivalent states
        HashMap<Integer, Integer> equal_states = new HashMap<>();
        for(Set<Integer> part : new_partition) {
            Integer min_state = 999999;
            for(Integer state : part) {
                if(state < min_state)
                    min_state = state;
            }
            for(Integer state : part)
                equal_states.put(state, min_state);
        }
        // Remap state
        Set<Integer> states = new HashSet<>();
        Set<Integer> final_states = new HashSet<>();
        Map<Integer, String> state2name = new HashMap<>();
        Map<Pair<Integer, String>, Integer> transitions = new HashMap<>();

        for(Integer state : this.states)
            states.add(equal_states.get(state));

        for(Integer state : this.final_states)
            final_states.add(equal_states.get(state));

        for(Integer state : this.state2name.keySet()) {
            int eq_s = equal_states.get(state);
            state2name.put(eq_s, state2name.get(eq_s));
        }

        for(Pair<Integer, String> key : this.transitions.keySet()) {
            Pair<Integer, String> eq_key = new Pair<>(equal_states.get(key.getKey()), key.getValue());
            transitions.put(eq_key, equal_states.get(this.transitions.get(key)));
        }

        return new DFA(states, this.input_symbols, transitions, this.initial_state, final_states, state2name);
    }

    private List<Set<Integer>> get_new_partition(List<Set<Integer>> partition) {
        // TODO: optimize
        List<Set<Integer>> new_partition = new LinkedList<>();
        for (Set<Integer> set : partition) {
            if (set.size() == 1) {
                new_partition.add(set);
                continue;
            }

            boolean is_div = false;
            for (String symbol : input_symbols) {
                int[] idxs = new int[set.size()];
                int[] nexts = new int[set.size()];
                ArrayList<Integer> list_set = new ArrayList<>(set);

                for (int i = 0; i < list_set.size(); ++i) {
                    nexts[i] = nextState(list_set.get(i), symbol);
                    if(nexts[i] == -1) {
                        idxs[i] = -1;
                        continue;
                    }
                    // Find the subset that next state belongs to.
                    for (int k = 0; k < partition.size(); ++k) {
                        if (partition.get(k).contains(nexts[i])) {
                            idxs[i] = k;
                            break;
                        }
                    }
                }
                HashMap<Integer, Set<Integer>> div_sets = new HashMap<>();
                for (int i = 0; i < idxs.length; ++i) {
                    if (!div_sets.containsKey(idxs[i]))
                        div_sets.put(idxs[i], new HashSet<>());
                    div_sets.get(idxs[i]).add(list_set.get(i));
                }

                if (div_sets.keySet().size() > 1) {
                    for (Integer key : div_sets.keySet()) {
                        new_partition.add(div_sets.get(key));
                    }
                    is_div = true;
                    break;
                }
            }
            if(is_div == false)
                new_partition.add(set);
        }
        return new_partition;
    }

    public Integer nextState(Integer current_state, String input_symbol) {
        if (transitions.containsKey(new Pair<>(current_state, input_symbol)))
            return transitions.get(new Pair<>(current_state, input_symbol));
        return dead_state;
    }

    static public DFA from(NFA nfa) {
        // 1. Initialize basic variables.
        int dfa_initial_state = 0;
        Set<Integer> dfa_final_states = new HashSet<>();
        Set<Integer> dfa_states = new HashSet<>();

        Map<Integer, String> dfa_state2name = new HashMap<>();
        Map<String, Integer> dfa_name2state = new HashMap<>();

        Map<Pair<Integer, String>, Integer> dfa_transitions = new HashMap<>();

        Set<String> dfa_input_symbols = nfa.input_symbols;

        // 2. Construct dfa states.
        // 2.1. Construct the initial states
        Set<Integer> nfa_initial_states = nfa.closure(nfa.initial_state);
        dfa_states.add(dfa_initial_state);
        dfa_state2name.put(dfa_initial_state, NFA.stringfyStates(new ArrayList<>(nfa_initial_states)));
        dfa_name2state.put(NFA.stringfyStates(new ArrayList<>(nfa_initial_states)), dfa_initial_state);

        Queue<Set<Integer>> queue = new LinkedList<>();
        queue.add(nfa_initial_states);

        Set<Integer> current_states;
        String current_states_name;

        Map<String, Integer> visit = new HashMap<>();

        // 2.2 Repeat generate states until no new state is generated.
        while (!queue.isEmpty()) {
            current_states = queue.poll();
            current_states_name = NFA.stringfyStates(new ArrayList<>(current_states));

            // Skip the state that we have processed.
            if (visit.containsKey(current_states_name))
                continue;

            // Check if this state is the final state.
            Set<Integer> intersect = new HashSet<>(current_states);
            intersect.retainAll(nfa.final_states);
            if (!intersect.isEmpty()) {
                dfa_final_states.add(dfa_name2state.get(current_states_name));
            }

            // Compute and enqueue the next states.
            enqueue_next_current_states(nfa, current_states, dfa_name2state.get(current_states_name),
                    queue, dfa_states, dfa_state2name, dfa_name2state, dfa_transitions);

            // Mark the visited state.
            visit.put(current_states_name, 1);
        }

        return new DFA(dfa_states, dfa_input_symbols, dfa_transitions,
                dfa_initial_state, dfa_final_states, dfa_state2name);
    }

    static private void enqueue_next_current_states(NFA nfa, Set<Integer> current_states, Integer current_state_id,
                                                    Queue<Set<Integer>> queue, Set<Integer> dfa_states,
                                                    Map<Integer, String> dfa_state2name, Map<String, Integer> dfa_name2state,
                                                    Map<Pair<Integer, String>, Integer> dfa_transitions) {
        Set<Integer> next_current_states;
        Integer next_current_states_id;
        String next_current_states_name;

        for (String input_symbol : nfa.input_symbols) {
            // Compute the next states based on current input symbol.
            next_current_states = nfa.nextStates(current_states, input_symbol);

            // If it is empty, it means that there is no transition between
            // current states via the current input symbol.
            if (next_current_states.isEmpty())
                continue;

            next_current_states_name = NFA.stringfyStates(new ArrayList<>(next_current_states));

            // Add the next states to the state set of the new dfa.
            if (dfa_name2state.containsKey(next_current_states_name)) {
                next_current_states_id = dfa_name2state.get(next_current_states_name);
            } else {
                next_current_states_id = dfa_states.size();
                dfa_states.add(next_current_states_id);
                dfa_state2name.put(next_current_states_id, next_current_states_name);
                dfa_name2state.put(next_current_states_name, next_current_states_id);
            }

            // Construct the transition of the new dfa.
//            if(!dfa_transitions.containsKey(new Pair<>(current_state_id, input_symbol)))
//                dfa_transitions.put(new Pair<>(current_state_id, input_symbol), new HashSet<>());
            dfa_transitions.put(new Pair<>(current_state_id, input_symbol), next_current_states_id);

            // Enqueue the next states.
            queue.add(next_current_states);
        }
    }
}
