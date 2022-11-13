public class Question4 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int[] test = new int[] { 1, 5, 3, 10, 7};
        LinkList list = new LinkList();
        for (int t : test) {
            list.insert(t);
            list.printOut();
        }
    }
}