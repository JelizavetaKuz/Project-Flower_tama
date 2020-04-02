

public class Container {
    // counters for flower resources
    protected double water; // h2o
    protected double sunlight; // we do not affect this
    protected double love; // co2
    protected double food; // nutrition
    protected int position;

    public Container(double water, double sunlight, double love, double food, int position) {
        this.water = water;
        this.sunlight = sunlight;
        this.love = love;
        this.food = food;
        this.position = position;
    }

    public Container() {
        this.water = 0;
        this.sunlight = 0;
        this.love = 0;
        this.food = 0;
        this.position = 0;
    }

    // TODO - here we have todo the day change instead the same period night 0, midday 2, morning and evening 1, rotation
    // instead of change position

    /**
     * By time changes position, simulate flow of sunlight
     * @param time
     */
    public void updateposition(int time){
        if(time < 7 || time >= 22){
            position = 0;
            sunlight += 4;
        }
        else if(time < 11 || time>= 17){
            position = 1;
            sunlight += 15;
        }
        else {
            position = 2;
            sunlight += 30;
        }
        // update time to set position
        //LocalTime time = LocalTime();
    }



    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getSunlight() {
        return sunlight;
    }

    public void setSunlight(double sunlight) {
        this.sunlight = sunlight;
    }

    public double getLove() {
        return love;
    }

    public void setLove(double love) {
        this.love = love;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Container{" +
                "water=" + water +
                ", sunlight=" + sunlight +
                ", love=" + love +
                ", food=" + food +
                ", position=" + position +
                '}';
    }
}
