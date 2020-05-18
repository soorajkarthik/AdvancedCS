import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaLab {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<String> list = new ArrayList<>(Arrays.asList("hello", "and", "goodbye"));

        nums.forEach(System.out::println);

        nums.removeIf(n -> n % 2 == 0);
        System.out.println(nums);

        System.out.println(nums.stream().filter(n -> n % 2 != 0).count());

        list.forEach(s -> {
            if (s.startsWith("a"))
                System.out.println(s);
        });

        List<Integer> numsDoubled = nums.stream().map(n -> n * 2).collect(Collectors.toList());
        numsDoubled.forEach(System.out::println);

        square56(Arrays.asList(3, 1, 4)).forEach(System.out::println);

        List<Double> prices = new ArrayList<>(Arrays.asList(3.58, 6.76, 12.45));
        List<Double> adjustedPrices = prices.stream().map(n -> n * 1.12).collect(Collectors.toList());

        int sum = Arrays.asList(1, 2, 3, 4, 5).stream().reduce((a, b) -> a + b).get();
        System.out.println(sum);

        double totalAdjustedPrices = prices.stream().map(n -> n * 1.12).reduce((a, b) -> a + b).get();
        System.out.println(totalAdjustedPrices);

        JFrame frame = new JFrame("Button Test");
        JButton button = new JButton("Click Here");

        button.addActionListener(a -> System.out.println("Button Clicked!"));

        frame.setSize(200, 200);
        frame.add(button);
        frame.setVisible(true);

        List<Person> users = new ArrayList<>();
        users.add(new Person("Sarah", 40));
        users.add(new Person("Peter", 50));
        users.add(new Person("Lucy", 60));
        users.add(new Person("Albert", 20));
        users.add(new Person("Charlie", 30));

        int oldestAge = users.stream().mapToInt(Person::getAge).max().getAsInt();
        System.out.println(oldestAge);


    }

    public static List<Integer> square56(List<Integer> nums) {

        return nums.stream().map(n -> n * n + 10).filter(n -> {
            String s = n + "";
            return !s.endsWith("5") && !s.endsWith("6");
        }).collect(Collectors.toList());

    }


    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return this.name + ", " + this.age;
        }

        int getAge() {
            return this.age;
        }
    }

}
