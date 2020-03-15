public class TestFlower {
    public static void main(String[] args) {
        Flower flower1 = new Flower("Lilia");
        System.out.println(flower1);
        flower1.setFood(56);
        System.out.println(flower1);
        flower1.addFood(9);
        System.out.println(flower1);
        flower1.loseFood();
        System.out.println(flower1);
    }
}
