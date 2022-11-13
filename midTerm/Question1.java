public class Question1 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        BinTree input = getTree();
        input.populateNextRight();
    }

    private static BinTree getTree() {
        BinTree tree = new BinTree();

        tree.root = new Node(1);

        tree.root.left = new Node(2);
        tree.root.right = new Node(3);

        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        tree.root.left.left.left = new Node(8);
        tree.root.left.right.left = new Node(9);
        tree.root.right.left.right = new Node(10);
        tree.root.right.right.right = new Node(11);

        return tree;
    }
}
