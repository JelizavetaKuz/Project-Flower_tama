import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class PlayTamagotchi {
    public static void main(String[] args) throws IOException {
        HumanBeing.satrtinfo();
        Flower flower = new Flower("Basic");
        String fileName = "flower.txt";
        FileConnection.create(fileName);
        FileConnection.fileRead(fileName,flower);
        while (HumanBeing.isRun()) {
            double hours = FileConnection.readTimePassed(fileName);
            int daytime = FileConnection.readHours(fileName);
            for (int i = 0; i < hours; i++) {
                flower.setTime(flower.getTime()+1);
                if(flower.getCurrenthp()<=0) {
                    endGame(fileName, flower);
                    break;
                }
                flower.grow(daytime);
                if (daytime > 23)
                    daytime = 1;
                else daytime++;
            }
            if(flower.getCurrenthp()<=0) {
                endGame(fileName, flower);
                break;
            }
            if(!HumanBeing.isRun())
                break;
            FileConnection.fileWrite(fileName, flower);
            HumanBeing.playerAction(flower);

        }

    }

    /**
     * Notify that flower is dead
     * @param fileName String
     * @param flower Flower
     * @throws IOException
     */
    public static void endGame(String fileName, Flower flower) throws IOException {
        System.out.println("\nI'm very sorry, but your flower has died in your absence.");
        System.out.println("It was " + flower.getTime() + " hours old and gain " + Utils.round(flower.getHigh()) + ". cm tall.");
        System.out.println("If you want to try again, rerun this game.");
        File save = new File(fileName);
        try (PrintWriter pw = new PrintWriter(save)) {
            pw.println("1");}
        HumanBeing.setRun(false);

    }
}
