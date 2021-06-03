package com.grubby.klc.rete;

import java.util.List;

public class EndNode extends ReteNode {

    private List<Object> rhs;

    public EndNode(ReteNode parent, List<Object> objectList) {
        super(NodeType.end, parent);
        //右手元 就是action
        this.rhs = objectList;
    }
}
