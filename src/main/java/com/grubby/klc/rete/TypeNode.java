package com.grubby.klc.rete;

public class TypeNode extends ReteNode {
    public TypeNode(ReteNode parent) {
        super(NodeType.type, parent);
    }

    public void activation(Fact fact) {
        getChildren().forEach(reteNode -> {
            ((TypeNode) reteNode).activation(fact);
        });
    }
}
