package com.grubby.klc;

import com.grubby.klc.link.SinglyLinkedList;

public class LinkedListTest {

//    @Test
    public void testInvert() {
        SinglyLinkedList.Node node = new SinglyLinkedList.Node("d", null);
        SinglyLinkedList.Node node1 = new SinglyLinkedList.Node("c", node);
        SinglyLinkedList.Node node2 = new SinglyLinkedList.Node("b", node1);
        SinglyLinkedList.Node node3 = new SinglyLinkedList.Node("a", node2);
        node3.printAll();
        SinglyLinkedList.Node node4 = SinglyLinkedList.invertNode(node3);
        node4.printAll();
    }
}
