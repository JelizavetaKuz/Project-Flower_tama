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
            System.out.print("What to add?: ");
            String parameter = scan.next();
            switch (parameter) {
                case "nutrition":
                    contain.setFood(contain.getFood() + food);
                    break;
                case "love":
                    contain.setLove(contain.getLove() + love);
                    break;
                case "water":
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

    public void sartinfo(){
        System.out.println("Hello there, plant lover, lil gamer or whatever you are!");
        System.out.println("This game here, is simulation of growing  plants.");
        System.out.println("You just need to plant a little seed and watch it grow.");
        System.out.println("Do not forget to check it time to time and water, talk or feed it. \n You even can give him a name!");
        System.out.println("So lets start!");
    }
}
