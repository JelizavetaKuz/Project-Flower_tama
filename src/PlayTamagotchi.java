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
            int daytime = FileConnection.readHours(fileName);
            for (int i = 0; i < hours; i++) {
                flower.setTime(flower.getTime()+1);
                if(flower.getCurrenthp()<=0) {
                    HumanBeing.endGame(fileName, flower);
                    break;
                }
                flower.grow(daytime);
                if (daytime > 23)
                    daytime = 1;
                else daytime++;

            }
            if(!HumanBeing.isRun())
                break;
            FileConnection.fileWrite(fileName, flower);
            HumanBeing.playerAction(flower);
        }

    }
}
