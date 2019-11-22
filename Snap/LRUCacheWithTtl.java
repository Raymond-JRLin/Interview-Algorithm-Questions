import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        System.out.println("Hello World");
    }


}
class LRUCacheWithTtl {
    Map<Integer, ListNode> map; // <key, listnode>
    int size;
    ListNode head;
    ListNode tail;


    LRUCache(int n) {
        this.size = n;
        map = new HashMap<>();
        head = new ListNode(-1, -1, 0);
        tail = new ListNode(-1, -1, 0);
    }

    boolean put(int key, int val, long ttl) {
        if (map.containsKey(key)) {
            // exist this value
            ListNode node = map.get(key);
            node.val = val;
            node.ttl = ttl;
            return true;
        }
        // it's full, remove the oldest value
        if (map.size() == size) {
            remove(head.next);
            // map.remove(head.next.key);
            // head.next.prev = null;
            // head.next = head.next.next;
        }

        // no such k-v
        ListNode node = new ListNode(key, val, ttl);
        map.put(key, node);

        moveToTail(node); // this node is the latest one
        return true;
    }

    Integer get(int key) {
        if (!map.containsKey(key)) {
            return null;
        }
        ListNode node = map.get(key);
        if (node.ttl > System.currentTimeMillis()) {
            moveToTail(node);
            return node.val;
        } else {
            remove(node);
            map.remove(key);
            return null;
        }
    }



    boolean remove(ListNode node) {
        ListNode next = node.next;
        next.prev = node.prev;
        node.prev = next;
    }

    void moveToTail(ListNode node) {
        Node prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
    }
}
class ListNode {
    int key;
    int val;
    long ttl;
    ListNode prev;
    ListNode next;

    ListNode(int k, int v, long t) {
        this.key = k;
        this.val = v;
        this.ttl = t;
        this.prev = null;
        this.next = null;
    }
}
