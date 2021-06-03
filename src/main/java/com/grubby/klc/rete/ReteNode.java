package com.grubby.klc.rete;

import java.util.List;

/**
 * @author grubby
 */
public class ReteNode {

    private List<ReteNode> children;

    private NodeType type;

    private ReteNode parent;

    private String id;

    public ReteNode(NodeType type, ReteNode parent) {
        this.type = type;
        this.parent = parent;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public ReteNode getParent() {
        return parent;
    }

    public void setParent(ReteNode parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ReteNode> getChildren() {
        return children;
    }

    public void setChildren(List<ReteNode> children) {
        this.children = children;
    }
}
