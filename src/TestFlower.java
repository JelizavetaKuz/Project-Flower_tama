public class TestFlower {
    public static void main(String[] args) {
        Flower flower1 = new Flower("Lilia");
        System.out.println(flower1);
        flower1.switchPlace("Valguse alla");
        flower1.addSomething("vesi",40);
        flower1.addSomething("armastus",40);
        flower1.addSomething("toiduained",40);
        System.out.println(flower1);
        flower1.lose();
        System.out.println(flower1);
    }
}
