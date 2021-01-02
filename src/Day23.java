import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day23 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String input = "284573961";

        Node prev = new Node(input.charAt(0) - '0');
        Node head = prev;
        TreeMap<Integer, Node> map = new TreeMap<>();
        map.put(prev.val, prev);
        for (int i = 1; i < input.length(); i++) {
            Node node = new Node(input.charAt(i) - '0');
            prev.next = node;
            prev = node;
            map.put(prev.val, prev);
        }
        //for part 2
        for (int i = 10; i <= 1000000; i++) {
            Node node = new Node(i);
            prev.next = node;
            prev = node;
            map.put(prev.val, prev);
        }
        prev.next = head;
        //part1(head);
        part2(head, map );
    }

    static void part1(Node head) {
        int i=1;
        while (i<=100) {
            Node selection = head;
            Node pickup = head.next;
            List<Integer> pickupList = Arrays.asList(pickup.val, pickup.next.val, pickup.next.next.val);

            int destination = selection.val -1;
            while (pickupList.contains(destination)) destination-=1;

            Node destinationNode = findDestinationNode(selection, destination);
            if(destinationNode == null) destinationNode = findMaxNode(selection);
            Node temp = destinationNode.next;
            destinationNode.next = pickup;
            head.next = pickup.next.next.next;
            pickup.next.next.next = temp;

            head = head.next;
            i++;
        }
        Node curr = head.next;
        Node oneNode = head;
        while (curr != head) {
            if(curr.val == 1) oneNode = curr;
            curr = curr.next;
        }
        curr = oneNode.next;
        while (curr != oneNode) {
            System.out.print(curr.val);
            curr = curr.next;
        }
    }

    static Node findMaxNode(Node selection) {
        Node maxNode = selection.next.next.next.next;
        Node nextNode = maxNode.next;
        while (selection !=nextNode) {
            if(nextNode.val> maxNode.val) maxNode = nextNode;
            nextNode = nextNode.next;
        }
        return maxNode;
    }
    static Node findDestinationNode(Node selection, int destination) {
        Node nextNode = selection.next.next.next.next;
        while (nextNode != selection) {
            if(nextNode.val == destination) return nextNode;
            nextNode = nextNode.next;
        }
        return null;
    }

    static void part2(Node head, TreeMap<Integer, Node> map) {
        int i=1;
        while (i<=10000000) {
            Node selection = head;
            Node pickup = head.next;
            List<Integer> pickupList = Arrays.asList(pickup.val, pickup.next.val, pickup.next.next.val);

            int destination = selection.val -1;
            while (pickupList.contains(destination)) destination-=1;

            Node destinationNode = map.get(destination);
            if(destinationNode == null) {
                destinationNode = map.get(map.lastKey());
                int k=1;
                while (pickupList.contains(destinationNode.val)) {
                    destinationNode = map.get(map.lastKey()-k++);
                }

            }
            Node temp = destinationNode.next;
            destinationNode.next = pickup;
            head.next = pickup.next.next.next;
            pickup.next.next.next = temp;

            head = head.next;
            i++;
        }
        Node curr = head.next;
        Node oneNode = head;
        while (curr != head) {
            if(curr.val == 1) {
                oneNode = curr;
                break;
            }
            curr = curr.next;
        }
        long ans = (long) oneNode.next.val * (long) oneNode.next.next.val;
        System.out.println("ans is " + ans);
    }

    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

}
