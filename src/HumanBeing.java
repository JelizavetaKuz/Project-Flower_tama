import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        double food = 10;
        double love = 15;
        double water = 15;
        //int pos = 0;
        boolean send = false;
        while(!send) {
            Scanner scan = new Scanner(System.in);
            System.out.print("What whould you like to add?\n(type \"food\", \"love\", \"water\" or exit to finish adding)\n");
            String parameter = scan.next();
            switch (parameter.toLowerCase()) {
                case "food":
                    System.out.println("Soil got nutrients\n");
                    contain.setFood(contain.getFood() + food);
                    break;
                case "love":
                    System.out.println("Flower feels your love and gets CO2 from your breath\n");
                    contain.setLove(contain.getLove() + love);
                    break;
                case "water":
                    System.out.println("Flower got water\n");
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
        System.out.println("If you'd like to have some tips about a game, type \"tips\"");
        String choise = scan.next();
        if(choise.toLowerCase().equals("add"))
            addSomething(flower.getContainer());
        else if (choise.toLowerCase().equals("check"))
            checkstats(flower);
        else if (choise.toLowerCase().equals("exit"))
            run = false;
        else if (choise.toLowerCase().equals("exit")) {
            System.out.println("1. Changes happend every hour.");
            System.out.println("2. Your flower's needs depend on a part of a day.");
            System.out.println("3. More - does not mean better. Do not overfeed your flower.");
        }
        else
            System.out.println("\nInvalid command");
    }

    public static void checkstats(Flower flower){
        System.out.println(flower.getName() + "'s stats:");
        System.out.println("Health: " + flower.getCurrenthp());
        System.out.println("Stage of development (1- seed, 2-sprout, 3-youngster, 4-full age plant) is "+flower.getStage());
        System.out.println("Right now it is "+ round(flower.getHeight())+" cm. high");
        System.out.println("it is "+flower.getTime()+" hours old");
        System.out.println("Container stats:");
        System.out.println("Water: "+ round(flower.getContainer().getWater()));
        System.out.println("Got "+ round(flower.getContainer().getSunlight())+ " of sunlight");
        System.out.println("Have "+ round(flower.getContainer().getLove())+ " of CO2 (love)");
        System.out.println("Nutrition of soil: " + round(flower.getContainer().getFood()));
    }

    public static void satrtinfo(){
        System.out.println("Hello there, plant lover, lil gamer or whatever you are!");
        System.out.println("This game here, is simulation of growing  plants.");
        System.out.println("You just need to plant a little seed and watch it grow.");
        System.out.println("Do not forget to check it time to time and water, talk or feed it.");
    }

    public static double round(double number){
        double rounded = Math.round(number*10000)/10000.0;
        return rounded;
    }

    public static void endGame(String fileName, Flower flower) throws IOException {
        System.out.println("\nI'm very sorry, but your flower has died in your absence.");
        System.out.println("It was " + flower.getTime() + " hours old and gain " + round(flower.getHeight()) + ". cm tall.");
        System.out.println("If you want to try again, rerun this game.");
        File save = new File(fileName);
        try (PrintWriter pw = new PrintWriter(save)) {
            pw.println("1");}
        run = false;

    }
}
