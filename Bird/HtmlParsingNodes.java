package com.codility;

import java.util.List;
import java.util.ArrayList;

// you can also use imports, for example:
// import java.util.*;

public class Solution {

    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        Node root = new Node("html");

        Node head = new Node("head");
        Node body1 = new Node("body");
        Node body2 = new Node("body");

        Node div1 = new Node("div");
        div1.content = "Foo Bar";
        Node div2 = new Node("div");
        div2.content = "Hello World";

        root.children.add(head);
        root.children.add(body1);
        root.children.add(body2);
        body1.children.add(div1);
        body1.children.add(div2);

        Node div3 = new Node("div");
        div3.content = "Bird Rides";
        body2.children.add(div3);

        // System.out.println("foo");

        List<Node> result = queryAll(root, "html body div");
        System.out.println(result.size());
        for (Node node : result) {
            System.out.println(node.content);
        }

    }

    private static Node parse(String s) {
        dfs(s, 0);
    }
    private static Node dfs(String s, int index) {
        if (index >= s.length()) {
            return null;
        }
        int left = s.firstIndexOf(s, "<") + 1;
        String tag = "";
        while (left < s.length() && s.charAt(left) != '>') {
            tag += s.charAt(left++);
        }
        Node root = new Node("tag");

        while (s.charAt(index)
    }

    private static List<Node> queryAll(Node root, String input) {
        String[] arr = input.split(" "); // [tag1, tag2]
        // System.out.println("input string: " + input);
        List<Node> result = new ArrayList<>();
        recursion(root, arr, 0, result);
        return result;
    }
    private static void recursion(Node root, String[] arr, int index, List<Node> result) {
        // System.out.println("check " + index + " := " + arr[index] + ", and node is " + root.tag);
        if (index == arr.length - 1 && arr[index].equals(root.tag)) {
            // found the last target node
            System.out.println("found div");
            result.add(root.copyNode());
            return;
        }
        if (!arr[index].equals(root.tag)) {
            return;
        }
        for (Node next : root.children) {
            recursion(next, arr, index + 1, result);
        }
    }


}
class Node {
    String tag;
    String content;
    List<Node> children;

    Node() {
        this.tag = null;
        this.content = null;
        children = new ArrayList<>();
    }

    Node(String tagName) {
        this.tag = tagName;
        this.content = null;
        children = new ArrayList<>();
    }

    Node(String tagName, String content) {
        this.tag = tagName;
        this.content = content;
        children = new ArrayList<>();
    }

    Node copyNode() {
        Node copy = new Node();
        copy.tag = this.tag;
        copy.content = this.content;
        copy.children = new ArrayList<>(this.children);
        return copy;
    }
}
// <div> = "Foo Bar"
// <body> = [<div>, <div>]
// <hello>
// /[a-zA-Z0-9]+/
