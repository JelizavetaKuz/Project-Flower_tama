import java.sql.SQLOutput;
import java.util.Scanner;

public class HumanBeing {
    // same as add

    private static boolean run = true;

    public static boolean isRun() {
        return run;
    }

    public static void setRun(boolean run) {
        HumanBeing.run = run;
    }

    /**
     * Method for adding resources in container
     * @param contain Container
     */
    public static void addSomething(Container contain){
        double food = 15;
        double love = 15;
        double water = 15;
        //int pos = 0;
        boolean send = false;
        while(!send) {
            Scanner scan = new Scanner(System.in);
            System.out.print("What whould you like to add?\n(type \"food\", \"love\", \"water\" or exit to finish adding)\n");
            String parameter = scan.next();
            switch (parameter) {
                case "food":
                    contain.setFood(contain.getFood() + food);
                    break;
                case "love":
                    contain.setLove(contain.getLove() + love);
                    break;
                case "water":
                    contain.setWater(contain.getWater() + water);
                    break;
                case "exit":
                    send = true;
                    break;
            }
        }
    }


    public static void playerAction(Flower flower){
        Scanner scan = new Scanner(System.in);
        System.out.print("\nWhould you like to check your flower, add something or exit the game? \n(write \"check\", \"add\" or \"exit\")\n");
        String choise = scan.next();
        if(choise.toLowerCase().equals("add"))
            addSomething(flower.getContainer());
        else if (choise.toLowerCase().equals("check"))
            checkstats(flower);
        else if (choise.toLowerCase().equals("exit"))
            run = false;
        else
            System.out.println("\nInvalid command");
    }

    public static void checkstats(Flower flower){
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

    public static void satrtinfo(){
        System.out.println("Hello there, plant lover, lil gamer or whatever you are!");
        System.out.println("This game here, is simulation of growing  plants.");
        System.out.println("You just need to plant a little seed and watch it grow.");
        System.out.println("Do not forget to check it time to time and water, talk or feed it.");
    }
}
