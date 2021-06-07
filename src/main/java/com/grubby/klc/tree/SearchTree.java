package com.grubby.klc.tree;

public class SearchTree {

    private TreeNode root;

    public TreeNode searchNode(int value) {
        TreeNode parent = root;
        while (parent != null) {
            if (parent.value < value) {
                parent = parent.left;
            } else if (parent.value > value) {
                parent = parent.right;
            } else {
                return parent;
            }
        }
        return null;
    }

    public void addNode(int value) {
        TreeNode treeNode = new TreeNode(value);
        TreeNode head = root;
        if (head == null) {
            head = treeNode;
        }

        TreeNode p = root;

        while (p != null) {
            if (p.value < value) {
                if (p.left == null) {
                    p.left = treeNode;
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = treeNode;
                }
                p = p.right;
            }
        }
    }

    /**
     * 假设没有相同value的节点
     * <p>
     * 分几种情况:
     * <p>
     * 删除节点就是头节点
     * <p>
     * 删除节点没有右子树
     * <p>
     * 删除节点 右子树中没有最小左子节点
     * 删除节点 右子树中有最小左子节点
     *
     * @param value
     */
    public void deleteNode(int value) {
        TreeNode p = root;
        TreeNode pp = null;

        while (p != null && p.value != value) {
            if (value > p.value) {
                pp = p;
                p = p.right;
            } else {
                pp = p;
                p = p.left;
            }
        }

        if (p == null) {
            return;
        }

        //找到右子节点最小子节点
        if (p.left != null && p.right != null) {
            TreeNode minP = p.right;
            TreeNode minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }

            p.value = minP.value;
            p = minP;
            pp = minPP;
        }


        TreeNode child;

        if (p.left != null) { //说明 p的层级树只有左子树
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        if (pp == null) {
            root = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }

    }

    /**
     * 核心思路降层，将所有情况合并为一个节点得处理
     *
     * @param value
     */
    public void deleteNodSelf(int value) {
        //找到对应叶子节点

        TreeNode p = root;
        TreeNode pp = null;

        while (p != null) {
            if (value > p.value) {
                pp = p;
                p = p.right;
            } else if (value < p.value) {
                pp = p;
                p = p.left;
            } else {
                break;
            }
        }
        if (p == null) {
            return;
        }

        if (p.left != null && p.right != null) {
            TreeNode minP = p.right;
            TreeNode minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.value = minP.value;
            p = minP;
            pp = minPP;
        }

        // 当所有情况都降为一层之后，先判断子节点
        TreeNode child;

        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        //判断节点左右
        if (pp == null) {
            root = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }

}
