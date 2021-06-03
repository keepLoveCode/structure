package com.grubby.klc.rete;


/**
 * alpha
 *
 * @author grubby
 */
public class AlphaNode extends ReteNode {

    private Pattern pattern;

    public AlphaNode(ReteNode parent, Pattern pattern) {
        super(NodeType.type, parent);

        this.pattern = pattern;
    }

    public boolean patternMatch(Pattern fact) {
        if (pattern.identifier == fact.identifier && pattern.attribute == fact.attribute && pattern.value == fact.value) {
            return true;
        }
        return false;
    }


    public boolean check(Fact fact) {
        boolean checkResult = true;
        if (pattern.identifier == fact.identifier && pattern.attribute == fact.attribute && pattern.value == fact.value) {
            return true;
        }
        return false;
    }

    public boolean activation(Fact fact) {
        if (check(fact)) {
            ((AlphaMemory) getChildren().get(0)).activation(fact, pattern);
            return true;
        }
        return false;
    }
}
