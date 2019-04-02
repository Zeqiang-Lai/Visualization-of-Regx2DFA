# Visualization of Regx2DFA

Java program to convert regular expression to DFA with visualization.

In this program, we use *antlr* to generate lexer and paser for regular expression. (Indeed, we can do the parsing with just a single stack).

For visulization, we generate dot code form nfs/dfa, and visualize it with *graphviz*.

## Dependency
- antlr-4.7.2

## Usage



## Todo

**Conversion**

- [x] regx to nfa
- [ ] nfa to dfa
- [ ] minimize dfa

**Visualization**

- [x] nfa to dot
- [ ] integrate graphviz

## Reference

1. https://media.pragprog.com/titles/tpantlr2/code/listeners/CallGraph.java
2. https://www.graphviz.org/documentation/
3. https://graphviz.gitlab.io/_pages/pdf/dotguide.pdf
4. https://www.antlr.org

**Related Projects**

- Stack based: https://github.com/felipemoura/RegularExpression-to-NFA-to-DFA

