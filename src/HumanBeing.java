import java.util.Scanner;

public class HumanBeing {
    // same as add
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
}
