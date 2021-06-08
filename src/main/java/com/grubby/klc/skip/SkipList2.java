package com.grubby.klc.skip;

/**
 * 自己的跳表实现
 *
 * @author grubby
 */
public class SkipList2 {

    private SkipNode head;

    private Integer max_level;


    public SkipNode search(int value) {
//        if (head == null) {
//            return null;
//        }
//        SkipNode p = head;
//        while (p != null) {
//            if (p.data == value) {
//                while (p.down != null) {
//                    p = p.down;
//                }
//                return p;
//            } else if (p.data < value) {
//
//            }
//
//        }
        return null;
    }


    public static class SkipNode {

        private int level;

        private SkipNode left;

        private SkipNode right;

        private SkipNode down;

        private int data;

        public SkipNode() {
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public SkipNode getLeft() {
            return left;
        }

        public void setLeft(SkipNode left) {
            this.left = left;
        }

        public SkipNode getRight() {
            return right;
        }

        public void setRight(SkipNode right) {
            this.right = right;
        }

        public SkipNode getDown() {
            return down;
        }

        public void setDown(SkipNode down) {
            this.down = down;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
