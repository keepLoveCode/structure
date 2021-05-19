package com.grubby.klc.link;

/**
 * 再写一次
 *
 * @author grubby
 */
public class SinglyLinkList {

    private Node header;

    public void addLast(String value) {
        Node node = new Node(value);
        addLast(node);
    }


    private void addLast(Node node) {
        if (header == null) {
            header = node;
            return;
        }

        Node tail = header;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(String value) {
        Node node = new Node(value, null);
        addFirst(node);
    }

    private void addFirst(Node node) {
        if (header == null) {
            header = node;
        } else {
            node.next = header;
            header = node;
        }
    }

    public int findValueIndex(String value) {
        Node curr = header;
        int i = 0;
        while (curr != null) {
            if (curr.getValue().equals(value)) {
                return i;
            }
            curr = curr.next;
            i++;
        }
        return -1;
    }

    public String findByIndex(int index) {
        Node curr = header;

        int pos = 0;

        while (curr != null) {
            if (pos == index) {
                return curr.getValue();
            } else {
                curr = curr.next;
                pos++;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    //left right
    public static Node lfReverse(Node head) {
        Node l = null;
        Node r = head;
        Node swap = null;

        while (r != null) {
            swap = r;
            r = r.next;
            swap.next = l;
            l = swap;
        }
        return l;
    }

    //position

    public static Node posReverse(Node node) {
        if (node == null) {
            return null;
        }
        Node head = node;
        Node swap = null;
        while (node.next != null) {
            swap = node.next;
            node.next = node.next.next;
            swap.next = head;
            head = swap;
        }
        return head;
    }

    //recursion

    public static Node recursion(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node swap = head.next;
        Node newHead = recursion(swap);
        swap.next = head;
        head.next = null;
        return newHead;
    }


//    public Node reverse(Node head) {
//        if (head == null) {
//            return null;
//        }
//
//        Node p = head;
//        Node pre = head;
//        Node cur = pre.next;
//
//        while (cur != null) {
//            pre.next = cur.next;
//            cur.next = p;
//            p = cur;
//            cur = pre.next;
//        }
//        return p;
//    }


    /**
     * 添加前置节点
     *
     * @param pre
     * @param value
     */
    public void addBefore(Node pre, String value) {
        if (pre == null) {
            return;
        }
        Node node = new Node(value);
        insertBefore(pre, node);
    }

    public void addAfter(Node p, String value) {
        if (p == null) {
            return;
        }
        Node newNode = new Node(value);
        insertAfter(p, newNode);
    }

    public static boolean tfResult(Node left, Node right) {
        if (left == null && right == null) {
            return true;
        }
        Node l = left;
        Node r = right;

        while ((l != null && r != null) && l.getValue() == r.getValue()) {
            l = l.next;
            r = r.next;
        }
        if (l == null && r == null) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否有回文
     *
     * @return
     */
    public static boolean palindrome(Node node) {
        if (node == null || node.next == null) {
            return true;
        }

        // 找中点

        Node p = node;
        Node q = node;
        while (q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }

        Node left = node;
        Node right = null;
        if (q.next == null) {
            right = p.next;
        } else {
            right = p.next.next;
        }
        p.next = null;
        Node recursion = recursion(right);

        return tfResult(left, recursion);
    }


    private void insertAfter(Node p, Node newNode) {
        if (p == null) {
            return;
        }
        Node q = header;

        //这次的目的仅仅是找到p
        while (q != null && q != p) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    private void insertBefore(Node p, Node newNode) {

        // 这种方式多定义了指针
//        Node target = header;
//        Node pPre = null;
//        while (target != null) {
//            if (target == p) {
//                break;
//            } else {
//                pPre = target;
//                target = target.next;
//            }
//        }
//        if (target == null) {
//            return;
//        }
//        //head
//        if (pPre == null) {
//            inert.next = p;
//            header = inert;
//        } else {
//            pPre.next = inert;
//            inert.next = p;
//        }
        //这种方式目的是找到前驱节点，可以
        //提到前面
        if (p == header) {
            addFirst(newNode);
        }

        Node q = header;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        q.next = newNode;
        newNode.next = p;
    }


    private static class Node {

        String value;

        Node next;

        Node(String value) {
            this(value, null);
        }

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public String getValue() {
            return value;
        }

        //带头反转
        public Node invert() {
            if (next == null) {
                return new Node(value);
            }
            Node head = new Node(null);
            Node p = this;

            while (p != null) {
                Node q = new Node(p.value);
                q.next = head.next;
                head.next = q;
                p = p.next;
            }
            return head.next;
        }

        public Node invert1() {
            if (next == null) {
                return new Node(value);
            }

            Node head = null;
            Node p = this;
            while (p != null) {
                Node q = new Node(p.value);
                q.next = head;
                head = q;
                p = p.next;
            }
            return head;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node("A");
        Node node2 = new Node("B");
        Node node3 = new Node("C");
        node1.next = node2;
        node2.next = node3;
        Node node = SinglyLinkList.lfReverse(node1);
        Node node4 = SinglyLinkList.posReverse(node);
        Node recursion = SinglyLinkList.recursion(node4);
        System.out.println(recursion);
    }


}

