package com.grubby.klc.rete;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ReteCore {

    public Object workMemory;

    public RootNode rootNode;

    public List<Rule> rules;

    public ReteCore(List<Rule> rules) {
        rootNode = new RootNode();
        rules.forEach(rule -> {
            LinkedList<AlphaMemory> alphaMemoryStack = new LinkedList<>();
            HashSet<Pattern> rulePatternSet = new HashSet<>();

            for (Pattern pattern : rule.patterns) {
                rulePatternSet.add(pattern);
                //typeNode不存在
                if (!rootNode.getTypeNodeHashMap().containsKey(pattern.attribute)) {
                    TypeNode typeNode = new TypeNode(this.rootNode);
                    AlphaNode alphaNode = new AlphaNode(typeNode, pattern);
                    AlphaMemory alphaMemory = new AlphaMemory(alphaNode);
                    rootNode.getChildren().add(typeNode);
                    typeNode.getChildren().add(alphaNode);
                    alphaNode.getChildren().add(alphaMemory);
                    alphaMemoryStack.push(alphaMemory);
                    rootNode.getTypeNodeHashMap().put(pattern.attribute, typeNode);
                } else {
                    //该节点已存在 创建对应的alpha节点
                    TypeNode typeNode = rootNode.getTypeNodeHashMap().get(pattern.attribute);

                    AlphaNode alpNode = (AlphaNode) (typeNode.getChildren().stream().filter(alpha -> ((AlphaNode) alpha).patternMatch(pattern)).findFirst().get());

                    if (alpNode == null) {
                        AlphaNode alphaNode = new AlphaNode(typeNode, pattern);
                        AlphaMemory alphaMemory = new AlphaMemory(alphaNode);
                        typeNode.getChildren().add(alphaNode);
                        alphaNode.getChildren().add(alphaMemory);
                        alphaMemoryStack.push(alphaMemory);
                    } else {
                        //这是撒子意思没懂
                        alphaMemoryStack.push((AlphaMemory) alpNode.getChildren().get(0));
                    }
                }

                //创建beta网络
                // 然后开始创建Beta网络的节点
                // 搜索能够复用的BetaMemory，该BetaMemory的token是 rulePatternSet的最大子集;
                BetaMemory maxMatchTokensNode;
                int maxMatchTokensNodeSize = 0;

                for (AlphaMemory alphaMemory : alphaMemoryStack) {
//                    List<ReteNode> joinNodes = alphaMemory.getChildren();
//
//                    for (ReteNode reteNode : joinNodes) {
//                        // 如果该alphaMemory链接的JoinNode其左输入的tokens比当前的rulePatternSet大，就跳过了。
//                        // 因为这意味着，假设当前规则的模式为c1&&c2&&c3, tokens.size为3， 而匹配的leftInputNode的模式数量比当前规则多，
//                        // 则该leftInputNode显然不能复用
//                        // 进行子集检查，看是否能否复用
//
//                        if (reteNode.getChildren().get(0).getType() != NodeType.end) {
//                        BetaMemory betaMemoryNode = (BetaMemory) ((JoinNode) reteNode).getChildren().get(0);
//                            // 该BetaMemory的tokens集合大小显然不能大于当前规则的模式数；
//                            if (betaMemoryNode.tokens != null && betaMemoryNode.tokens.size() <= rulePatternSet.size()) {
//                                // 匹配
//                                if (patternSubsetCheck(betaMemoryNode.tokens, rulePatternSet) && betaMemoryNode.tokens.size > maxMatchTokensNodeSize) {
//                                    maxMatchTokensNode = betaMemoryNode;
//                                    maxMatchTokensNodeSize = maxMatchTokensNode.tokens.size;
//                                }
//                            }
//                        }
//                    }
                }


            }
        });
    }
}
