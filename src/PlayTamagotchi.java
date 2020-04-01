import java.io.IOException;

public class PlayTamagotchi {
    public static void main(String[] args) throws IOException {
        Flower flower = new Flower("Basic");
        String fileName = "flower.txt";
        FileConnection.create(fileName);
        FileConnection.fileRead(fileName,flower);
        double hours = FileConnection.readTimePassed(fileName);
        for (int i = 0; i < hours; i++) {
            flower.grow();
            System.out.println(flower);
        }






    }
}
