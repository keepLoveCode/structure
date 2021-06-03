package com.grubby.klc.rete;

import java.util.List;
import java.util.Set;

public class BetaMemory extends ReteNode {

    public List<Object> items;

    public Set<Pattern> tokens;

    public BetaMemory(JoinNode joinNode, Set<Pattern> patterns) {
        super(NodeType.join, joinNode);
        this.tokens = patterns;
    }
}
