package com.grubby.klc.rete;

import java.util.HashMap;
import java.util.Map;

/**
 * @author grubby
 */
public class RootNode extends ReteNode {

    private Map<String, TypeNode> typeNodeHashMap = new HashMap<>();

    public RootNode() {
        super(NodeType.root, null);
    }

    public void addFact(Fact fact) {
        TypeNode typeNode = typeNodeHashMap.get(fact.attribute);
        if (typeNode != null) {
            typeNode.activation(fact);
        }
    }

    public Map<String, TypeNode> getTypeNodeHashMap() {
        return typeNodeHashMap;
    }

    public void setTypeNodeHashMap(Map<String, TypeNode> typeNodeHashMap) {
        this.typeNodeHashMap = typeNodeHashMap;
    }
}
