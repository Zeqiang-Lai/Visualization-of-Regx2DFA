package regx;

import automata.DFA;
import automata.NFA;
import javafx.util.Pair;

import java.util.*;

public class RegxStateGraph {

    static String epsilon = "&epsilon;";

    class Edge {
        int from, to;
        String label;

        Edge(int from, int to, String label) {
            this.from = from;
            this.to = to;
            this.label = label;
        }
    }

    //
    // Properties
    //

    public int getNum_of_state() {
        return num_of_state;
    }

    private int num_of_state = 0;

    public Vector<Edge> getEdges() {
        return edges;
    }

    private Vector<Edge> edges = new Vector<>();

    public int getSource() {
        return source;
    }

    private int source;

    private Set<Integer> destinations = new HashSet<>();

    private int rank[];

    //
    // Methods
    //

    /**
     * create a new state.
     *
     * @return an integer that represents a new state.
     */
    int getState() {
        int tmp = num_of_state;
        num_of_state += 1;
        return tmp;
    }

    /**
     * add a new edge into graph.
     *
     * @param source integer, source state.
     * @param target integer, target state.
     * @param label  String, edge label.
     */
    void addEdge(int source, int target, String label) {
        edges.add(new Edge(source, target, label));
    }

    /**
     * compute the rank for each node, using bfs.
     */
    private void computeRank() {
        // TODO: Optimize
        // Initialize rank array
        rank = new int[num_of_state];

        // Construct adjacent matrix.
        int A[][] = new int[num_of_state][num_of_state];
        for (Edge edge : edges) {
            A[edge.from][edge.to] = 1;
        }
        // Find the source and destinations.
        source = -1;
        int out_count, in_count;
        for (int i = 0; i < num_of_state; ++i) {
            out_count = 0;
            in_count = 0;
            for (int j = 0; j < num_of_state; ++j) {
                in_count += A[j][i];
                out_count += A[i][j];
            }
            if (in_count == 0) {
                source = i;
            }
            if (out_count == 0) {
                destinations.add(i);
            }
        }
        assert source == -1;

        // BFS
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean visit[] = new boolean[num_of_state];

        queue.add(source);
        rank[source] = 0;

        int head;
        while (!queue.isEmpty()) {
            head = queue.poll();
            visit[head] = true;
            for (int i = 0; i < num_of_state; ++i) {
                if (!visit[i] && A[head][i] == 1) {
                    queue.add(i);
                    rank[i] = rank[head] + 1;
                }
            }
        }
    }

    /**
     * convert graph into dot file, which can be viewd with graphviz.
     *
     * @return String, dot source file.
     */
    public String toDOT() {
        if (rank == null || rank.length != num_of_state)
            computeRank();

        StringBuilder buf = new StringBuilder();
        buf.append("digraph G {\n");
        buf.append("  ranksep=.25;\n");
        buf.append("  rankdir=\"LR\";\n");
        buf.append("  edge [arrowsize=.5]\n");
        buf.append("  node [shape=circle, fontname=\"ArialNarrow\",\n");
        buf.append("        fontsize=12, fixedsize=true, height=.45];\n");
        buf.append("  ");
        for (int i = 0; i < num_of_state; ++i) { // print all nodes first
            if(destinations.contains(i))
                continue;
            buf.append(i);
            buf.append("; ");
        }
        buf.append("\n");
        buf.append("  node [shape=doublecircle, fontname=\"ArialNarrow\",\n");
        buf.append("        fontsize=12, fixedsize=true, height=.45];\n");
        buf.append("  ");
        for(int state : destinations) {
            buf.append(state);
            buf.append("; ");
        }

        //TODO: generate edge using breadth first search
        for (Edge edge : edges) {
            buf.append("  ");
            buf.append(edge.from);
            buf.append(" -> ");
            buf.append(edge.to);
            buf.append(" [ label = \"");
            buf.append(edge.label);
            buf.append("\";");
            if (rank[edge.from] > rank[edge.to])
                buf.append("constraint=false;");
            buf.append("] ");
            buf.append(";\n");
        }

        buf.append("}\n");
        return buf.toString();
    }

    public NFA toNFA() {
        computeRank();
        int initial_state;
        Set<Integer> final_states;
        Map<Integer, String> state_names;
        Set<Integer> states;

        Set<String> input_symbols = new HashSet<>();
        Map<Pair<Integer, String>, Set<Integer>> transitions = new HashMap<>();

        // initialize transitions && input_symbols
        String label;
        for (RegxStateGraph.Edge edge : edges) {
//            label = edge.label == epsilon ? "" : edge.label;
            label = edge.label;
            if (!transitions.containsKey(new Pair<>(edge.from, label))) {
                transitions.put(new Pair<>(edge.from, label), new HashSet<>());
            }
            transitions.get(new Pair<>(edge.from, label)).add(edge.to);
            if (edge.label != epsilon)
                input_symbols.add(edge.label);
        }
        // other initialization
        initial_state = source;

        final_states = new HashSet<>();
        final_states.addAll(destinations);

        states = new HashSet<>();
        state_names = new HashMap<>();
        for (int i = 0; i < num_of_state; ++i) {
            states.add(i);
            state_names.put(i, String.valueOf(i));
        }

        return new NFA(states, input_symbols, transitions, initial_state, final_states, state_names);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    static public RegxStateGraph from(DFA dfa) {
        RegxStateGraph graph = new RegxStateGraph();
        Integer from, to;
        String label;
        for (Pair<Integer, String> key : dfa.transitions.keySet()) {
            from = key.getKey();
            label = key.getValue();
            to = dfa.transitions.get(key);
            graph.addEdge(from, to, label);
        }
        graph.num_of_state = dfa.states.size();
        graph.destinations.addAll(dfa.final_states);
        return graph;
    }

}

