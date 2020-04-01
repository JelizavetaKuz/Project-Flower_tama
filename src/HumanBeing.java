import java.sql.SQLOutput;
import java.util.Scanner;

public class HumanBeing {
    // same as add

    /**
     * Method for adding resources in container
     * @param contain Container
     */
    public void addSomething(Container contain){
        double food = 5;
        double love = 5;
        double water = 5;
        int pos = 0;
        boolean send = false;
        while(!send) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Mida lisada?: ");
            String parameter = scan.next();
            switch (parameter) {
                case "toiduained":
                    contain.setFood(contain.getFood() + food);
                    break;
                case "armastus":
                    contain.setLove(contain.getLove() + love);
                    break;
                case "vesi":
                    contain.setWater(contain.getWater() + water);
                    break;
                case "":
                    send = true;
                    break;
            }
        }
    }

    public void checkstats(Flower flower){
        System.out.println(flower.getName() + "'s stats:");
        System.out.println("Health: " + flower.getCurrenthp());
        System.out.println("Stage of development (1- seed, 2-sprout, 3-youngster, 4-full age plant )"+flower.getStage());
        System.out.println("Right now it is "+ flower.getHeight()+" cm. high");
        System.out.println("it is "+flower.getTime()+" hours old");
        System.out.println("Container stats:");
        System.out.println("Water: "+ flower.getContainer().getWater());
        System.out.println("Get "+ flower.getContainer().getSunlight()+ " of sunlight");
        System.out.println("Have "+flower.getContainer().getLove()+ " of CO2 (love)");
        System.out.println("Nutrition of soil: " + flower.getContainer().getFood());
    }
}
