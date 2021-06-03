package com.grubby.klc.rete;

import java.util.LinkedList;

/**
 * @author grubby
 */
public class AlphaMemory extends ReteNode {

    public LinkedList<Fact> items;

    public AlphaMemory(ReteNode parent) {
        super(NodeType.alphaMemory, parent);
        this.items = new LinkedList<>();
    }

    public void addFact(Fact fact) {
        items.add(fact);
    }

    public void activation(Fact fact, Pattern pattern) {
        addFact(fact);

        // this.children.forEach((joinNode) => joinNode.rightActivation(e, p));
        // TODO: 2021/6/1 grubby
    }
}
