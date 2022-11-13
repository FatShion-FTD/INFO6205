public class LinkList {

    public Node head;

    public int length = 0;

    public LinkList() {
        head = null;
    }

    public void insert(Integer data){
        // Insert in sorted manner
        if (head == null) {
            head = new Node(data);
            return;
        }
        Node node = head;
        Node prev = new Node(-1);
        prev.next = node;
        while (node != null && node.data <= data) {
            node = node.next;
            prev = prev.next;
        }

        if (node == null) {
            prev.next = new Node(data);
            return;
        } else {
            Node newNode = new Node(data);
            prev.next = newNode;
            newNode.next = node;
        }
    }

    public void printOut() {
        Node node = head;
        while (node.next != null) {
            System.out.print(node.data + "->");
            node = node.next;
        }
        System.out.println(node.data);
    }
}
