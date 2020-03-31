// TODO read/write file txt
// TODO flower class: water, sunlight, love/talk, food
// TODO time functions, may use compareTo(Timestamp ts) or (Date o) ** Date is object
// TODO relations between functions of watering, putting into the sunlight, talk/love/cuddling, giving the flower nutrition
// TODO do we make difference between seasons?
// TODO should we do grow speed? this is just param for regulation of resource consumption
// TODO sholud we add height
// flower by itself is consuming sources to grow

// Structure Human being inserts parameters to add in container, flower takes resources from there and grow.
// In text file we save each timestamp when flower game has closed or stopped. On turning on we calculate all counters.


public class Flower{
    private String name;

    // do have critical, normal and perfect  level
    private int hp; // regulate the amount of resource consumption
    // make max level of counters, take from text file hp
    protected double maxwater; // h2o
    protected double maxsunlight; // we do not affect this
    protected double maxlove; // co2
    protected double maxfood; // nutrition

    private double growSpeed;
    private int height;


    private double time; // type ?  timestamp regulate cycling of consumption

    private int stage; // regulate needed amount of resources for hp by * ... all counters
    private  double periodTime; // max time of each stage, if not -> dead


    Flower(String name){
        this.name = name;
        this.time = 0.0;
        this.hp = 100;

    }

    public Flower(String name, int hp, double growSpeed, int height, double time, int stage, double periodTime) {
        this.name = name;
        this.hp = hp;
        this.growSpeed = growSpeed;
        this.height = height;
        this.time = time;
        this.stage = stage;
        this.periodTime = periodTime;
    }


    //
    public void consume(Container container){
        switch (container.getPosition()){
            case 0:
                growSpeed = 2.5; //
                container.setSunlight(container.getSunlight() - maxsunlight);
                container.setLove(container.getLove() - maxlove);
                container.setWater(container.getWater() - maxwater*4);
                container.setFood(container.getFood() - maxfood*0.3);
                break;
            case 1:
                growSpeed = 1.0; //
                container.setSunlight(container.getSunlight() - maxsunlight*2);
                container.setLove(container.getLove() - maxlove*2);
                container.setWater(container.getWater() - maxwater*2);
                container.setFood(container.getFood() - maxfood*0.2);
                break;
            case 2:
                growSpeed = 0.5; //
                container.setSunlight(container.getSunlight() - maxsunlight*3);
                container.setLove(container.getLove() - maxlove*4);
                container.setWater(container.getWater() - maxwater*1);
                container.setFood(container.getFood() - maxfood*0.1);
                break;
        }

    }
    // lose naturally
//    public void lose(){
//        double points = 5;
//        double waterMultiplier;
//        double lightMultiplier;
//
//        if(this.daytime == 0) {
//            waterMultiplier = 0.5;
//            lightMultiplier = 0.5;
//        }
//        else if(this.daytime == 1) {
//            waterMultiplier = 1.0;
//            lightMultiplier = 0.0;
//        }
//        else {
//            waterMultiplier = 1.5;
//            lightMultiplier = -1;
//        }
//
//        this.setFood(this.getFood() - points*0.02);
//        this.setLove(this.getLove() - points*0.2);
//        this.setWater(this.getWater() - points * waterMultiplier);
//        this.setSunlight(this.getSunlight() - points * lightMultiplier);
//
//    }

    // TODO - here we have todo the day change instead the same period night 0, midday 2, morning and evening 1
    // have relation with grow speed
//    public void switchPlace(String command){
//        if(command == "Valguse alla")
//            this.setDaytime(2);
//        else if(command == "Pimedusse")
//            this.setDaytime(0);
//        else
//            this.setDaytime(1);
//    }

}

