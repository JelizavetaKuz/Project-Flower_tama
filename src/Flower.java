// TODO read/write file txt
// TODO flower class: water, sunlight, love/talk, food
// TODO time functions
// TODO relations between functions of watering, putting into the sunlight, talk/love/cuddling, giving the flower nutrition

public class Flower {
    private String name;
    private double water;
    private double sunlight;
    private double love;
    private double food;

    Flower(String name){
        this.name = name;
        this.food = 0.0;
        this.water = 0.0;
        this.love = 0.0;
        this.sunlight = 0.0;
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

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", water=" + water +
                ", sunlight=" + sunlight +
                ", love=" + love +
                ", food=" + food +
                '}';
    }
}
