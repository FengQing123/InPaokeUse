package com.fq.inpaokeuse.algorithm;

/**
 * @author fengqing
 * @date 2018/12/19
 */


public class LinkedListAlgo {

    public static void main(String[] args) {

    }

    // 单链表反转
    public static Node reverse(Node list) {
        Node headNode = null;
        Node previousNode = null;
        Node currentNode = list;
        while (currentNode != null) {
            Node nextNode = currentNode.next;
            if (nextNode == null) {
                headNode = currentNode;
            }
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return headNode;
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

}
