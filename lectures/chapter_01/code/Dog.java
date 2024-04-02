package lectures.chapter_01.code;
public class Dog {
    public int weightInPounds;

    public Dog(int x) {
        this.weightInPounds = x;
    }

    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyipyip");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark. ");
        } else {
            System.out.println("woof");
        }
    }

    public static void printDog() {
        System.out.println("This is a dog");
    }

    public static void main(String[] args) {
        Dog tinyDog = new Dog(20);
        tinyDog.makeNoise();

        // Instance can access the static method although it's not recommended
        tinyDog.printDog();

        Dog.printDog();
    }
}
