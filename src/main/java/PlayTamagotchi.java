package main.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class PlayTamagotchi {


    /**
     * Method that creates new flower and file for it
     * @param flowerName String, flower name for search
     * @return Flower, new flower
     * @throws IOException
     */
    public static Flower createflower(String flowerName) throws IOException {
        Flower flower = new Flower("Basic");
        String fileName = "flower.txt";
        //FileConnection.fileCreate(fileName,flowerName);
        FileConnection.fileRead(fileName,flower);
        double hours = FileConnection.readTimePassed(fileName);
        int daytime = FileConnection.readHours(fileName);
        for (int i = 0; i < hours; i++) {
            if(flower.getCurrenthp()<=0)
                break;
            flower.setTime(flower.getTime()+1);
            flower.grow(daytime);
            if (daytime > 23)
                daytime = 1;
            else daytime++;
        }
        FileConnection.fileWrite(fileName, flower);
        return flower;

    }


    /**
     *Method, that up dates all the stats for time and file write
     * @param fileName String, constant filename
     * @param flower Flower, created flower
     * @throws IOException
     */
    public static void updateFile(String fileName, Flower flower) throws IOException {
        double hours = FileConnection.readTimePassed(fileName);
        int daytime = FileConnection.readHours(fileName);
        for (int i = 0; i < hours; i++) {
            if(flower.getCurrenthp()<=0)
                break;
            flower.setTime(flower.getTime()+1);
            flower.grow(daytime);
            if (daytime > 23)
                daytime = 1;
            else daytime++;
        }
        FileConnection.fileWrite(fileName, flower);
    }
/*
    public static void startGame() throws IOException {
        System.out.println(HumanBeingCopy.satrtinfo());
        Flower flower = new Flower("Basic");
        String fileName = "flower.txt";
        FileConnection.create(fileName);
        FileConnection.fileRead(fileName,flower);
        while (HumanBeingCopy.isRun()) {
            double hours = FileConnection.readTimePassed(fileName);
            int daytime = FileConnection.readHours(fileName);
            for (int i = 0; i < hours; i++) {
                flower.setTime(flower.getTime()+1);
                if(flower.getCurrenthp()<=0) {
                    endGame(flower);
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
            if(!HumanBeingCopy.isRun())
                break;
            FileConnection.fileWrite(fileName, flower);
            //HumanBeingCopy.playerAction(flower);

        }

    }*/

    /**
     * Notify that flower is dead
     * @param flower Flower
     * @return String, message
     * @throws IOException
     */
    public static String endGame(Flower flower) throws IOException {
        String fileName = "flower.txt";
        //System.out.println("I'm very sorry, but your flower has died in your absence.");
        //System.out.println("It was " + flower.getTime() + " hours old and gain " + Utils.round(flower.getHigh()) + ". cm tall.");
        //System.out.println("If you want to try again, rerun this game.");
        File save = new File(fileName);
        try (PrintWriter pw = new PrintWriter(save)) {
            pw.println("1");}
        //HumanBeingCopy.setRun(false);

        return ("I'm very sorry, but your flower has died in your absence.\n It was "
                + flower.getTime() + " hours old and gain " + Utils.round(flower.getHigh())+ ". cm tall. \nIf you want to try again, rerun this game.");

    }


}
