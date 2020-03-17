// TODO read/write file txt
// TODO flower class: water, sunlight, love/talk, food
// TODO time functions, may use compareTo(Timestamp ts) or (Date o) ** Date is object
// TODO relations between functions of watering, putting into the sunlight, talk/love/cuddling, giving the flower nutrition

public class Flower {
    private String name;
    private double water;
    private double sunlight;
    private double love;
    private double food;
    private int place;
    private int stage;
    private double time;
    private int hp;

    Flower(String name){
        this.name = name;
        this.food = 0.0;
        this.water = 0.0;
        this.love = 0.0;
        this.sunlight = 0.0;
        this.place = 0;
        this.time = 0.0;
        this.hp = 100;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public void setLove(double love) {
        this.love = love;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSunlight(double sunlight) {
        this.sunlight = sunlight;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getStage() {
        return stage;
    }

    public double getFood() {
        return food;
    }

    public double getLove() {
        return love;
    }

    public double getSunlight() {
        return sunlight;
    }

    public double getWater() {
        return water;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        String kasvuTase = "";
        String positsioon = "";
        switch (stage){
            case 0:
                kasvuTase = "seemnetera";
                break;
            case 1:
                kasvuTase = "idu";
                break;
            case 2:
                kasvuTase = "väike lill";
                break;
            case 3:
                kasvuTase = "täiskasvanud lill";
                break;
        }

        switch (place){
            case 0:
                positsioon = "varjus";
                break;
            case 1:
                positsioon = "pool varjus";
                break;
            case 2:
                positsioon = "valguse all";
                break;
        }

        return "Teie lille nimi on " + name +
                "\nTema tase on " + kasvuTase +
                "\npraegu ta seisab " + positsioon +
                "\ntema seisund on järgmine:\nvesi = " + water +
                ", \nvalgus = " + sunlight +
                ", \narmastus = " + love +
                ", \ntoiduained = " + food +
                ", \ntervise punktid = " + hp +
                ", \nelu aeg tundides on " + time + "\n";
    }

    public void addSomething(String parameter, double value){
        switch (parameter){
            case "toiduained":
                this.setFood(this.getFood() + value);
                break;
            case "armastus":
                this.setLove(this.getLove() + value);
                break;
            case "vesi":
                this.setWater(this.getWater() + value);
         }
    }
    public void lose(){
        double points = 5;
        double waterMultiplier;
        double lightMultiplier;

        if(this.place == 0) {
            waterMultiplier = 0.5;
            lightMultiplier = 0.5;
        }
        else if(this.place == 1) {
            waterMultiplier = 1.0;
            lightMultiplier = 0.0;
        }
        else {
            waterMultiplier = 1.5;
            lightMultiplier = -1;
        }

        this.setFood(this.getFood() - points*0.02);
        this.setLove(this.getLove() - points*0.2);
        this.setWater(this.getWater() - points * waterMultiplier);
        this.setSunlight(this.getSunlight() - points * lightMultiplier);


    }

    public void switchPlace(String command){
        if(command == "Valguse alla")
            this.setPlace(2);
        else if(command == "Pimedusse")
            this.setPlace(0);
        else
            this.setPlace(1);
    }

}

/*
import java.io.File;  // Import the File class

public class GetFileInfo {
    public static void main(String[] args) {
        File myObj = new File("filename.txt");
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("Writeable: " + myObj.canWrite());
            System.out.println("Readable " + myObj.canRead());
            System.out.println("File size in bytes " + myObj.length());
        } else {
            System.out.println("The file does not exist.");
        }
    }
}*/