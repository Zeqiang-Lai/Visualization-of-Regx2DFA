# Visualization of Regx2DFA

Java program to convert regular expression to automata.DFA with visualization.

In this program, we use *antlr* to generate lexer and paser for regular expression. (Indeed, we can do the parsing with just a single stack).

For visulization, we generate dot code form nfs/dfa, and visualize it with *graphviz*.


## Getting started


## Regx grammar
```
* closure
& concatenation
| or
() parentheses

e.g.
(a|b)* & a & (a|b) & (a|b)
```

## Dependency
- antlr-4.7.2
- graphviz

## Todo

**Conversion**

- [x] regx to nfa
- [x] nfa to dfa
- [ ] minimize dfa
- [ ] try to generate dfa directly.

**Visualization**

- [x] nfa to dot
- [x] rearrange nodes
- [ ] integrate graphviz

## Reference

1. https://media.pragprog.com/titles/tpantlr2/code/listeners/CallGraph.java
2. https://www.graphviz.org/documentation/
3. https://graphviz.gitlab.io/_pages/pdf/dotguide.pdf
4. https://www.antlr.org

**Related Projects**

- Stack based Regx2DFA: https://github.com/felipemoura/RegularExpression-to-automata.NFA-to-automata.DFA
- Python automata: https://github.com/caleb531/automata
