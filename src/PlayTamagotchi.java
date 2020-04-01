import java.io.IOException;

public class PlayTamagotchi {
    public static void main(String[] args) throws IOException {
        HumanBeing.satrtinfo();
        Flower flower = new Flower("Basic");
        String fileName = "flower.txt";
        FileConnection.create(fileName);
        FileConnection.fileRead(fileName,flower);
        while (HumanBeing.isRun()) {
            double hours = FileConnection.readTimePassed(fileName);
            flower.setTime(flower.getTime()+hours);
            int daytime = FileConnection.readHours(fileName);
            for (int i = 0; i < hours; i++) {
                if (daytime > 24)
                    daytime = 1;
                else daytime++;
                flower.grow(daytime);
            }
            FileConnection.fileWrite(fileName, flower);
            HumanBeing.playerAction(flower);
        }






    }
}
