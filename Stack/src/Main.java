public class Main {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        System.out.println(s.capacity());
        s.push(5);
        s.push(3);
        s.push(3);
        s.push(3);
        s.push(3);
        s.push(3);
        s.push(3);
        s.push(3);
        s.push(3);
        s.push(3);
        s.push(3);
        s.push(3);
        System.out.println(s.capacity());
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        System.out.println(s.capacity());
        s.pop();
        s.pop();
        s.pop();
        s.pop();


        System.out.println(s.capacity());


    }
}
