package com.grubby.klc.rete;

public class JoinNode extends ReteNode {

    private ReteNode rightInput;

    public JoinNode(ReteNode parent) {
        super(NodeType.join, parent);
        this.rightInput = parent;
    }


}
