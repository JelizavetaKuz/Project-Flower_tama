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
    protected String name;
    private Container container;

    // do have critical, normal and perfect  level
    private int hp; // regulate the amount of resource consumption maxhp of flower; from txt
    private int currenthp;// counter


    // needed resourses
    protected double waterunit; // h2o; from txt
    protected double sunlightunit; // we do not affect this; from txt
    protected double loveunit; // co2; from txt
    protected double foodunit; // nutrition; from txt

    //for grow
    private double growSpeed;
    private double height;
    private double maxheight;//from txt

    private double time; // type ?  timestamp regulate cycling of consumption

    private int stage; // regulate needed amount of resources for hp by * ... all counters
    private  double periodTime; // max time of each stage, if not -> dead; from txt


    /**
     * Constructor for test
     * @param name String
     */
    Flower(String name){
        this.name = name;
        this.time = 0.0;
        this.hp = 100;

        this.waterunit = 10; // h2o; from txt
        this.sunlightunit = 10; // we do not affect this; from txt
        this.loveunit = 10; // co2; from txt
        this.foodunit = 3; // nutrition; from txt

        this.maxheight = 200;
        this.stage = 1;
        this.container = new Container();
    }

    /**
     * Constructor for serialaze fill
     * @param name String
     * @param container Container
     * @param hp int
     * @param currenthp int
     * @param waterunit double
     * @param sunlightunit double
     * @param loveunit double
     * @param foodunit double
     * @param growSpeed double
     * @param height double
     * @param maxheight double
     * @param time ?
     * @param stage int
     * @param periodTime ?
     */
    public Flower(String name, Container container, int hp, int currenthp, double waterunit, double sunlightunit, double loveunit, double foodunit, double growSpeed, double height, double maxheight, double time, int stage, double periodTime) {
        this.name = name;
        this.container = container;
        this.hp = hp;
        this.currenthp = currenthp;
        this.waterunit = waterunit;
        this.sunlightunit = sunlightunit;
        this.loveunit = loveunit;
        this.foodunit = foodunit;
        this.growSpeed = growSpeed;
        this.height = height;
        this.maxheight = maxheight;
        this.time = time;
        this.stage = stage;
        this.periodTime = periodTime;
    }

    /**
     * Method for activation of flower methods
     */
    public void grow(){
        consume();
        sethp();
        setSpeed();
        height += height*growSpeed;
        int currentstage = stage;
        setstage();
        if(currentstage != stage ){
            int modifier = setmodifier();
            waterunit *= modifier;
            foodunit *= modifier;
            loveunit *= modifier;
            sunlightunit *= modifier;
        }
    }

    /**
     * Method for level switcher creation
     */
    protected void setstage(){
        double changevalue = maxheight * 0.25;
        if(height > changevalue*3) stage = 4;
        else if(height > changevalue*2) stage = 3;
        else if(height > changevalue) stage = 2;
        else stage = 1;
    }

    /**
     * Method for initialize speed by hp
     */
    protected void setSpeed(){
        if (currenthp < hp*0.1){
            growSpeed = -5;
        }else if(currenthp < hp*0.55){
            growSpeed += 0.5;
        }else{
            growSpeed += 1;
        }
    }

    /**
     * Method for setting up hp level/counter by container resources
     */
    protected void sethp(){
        double maxdifer = 0.15;
        if(container.getFood()< foodunit*maxdifer || container.getFood() > foodunit*maxdifer ){
            currenthp -= 5;
        }else if(currenthp < hp) currenthp+= 5;
        if(container.getWater()< waterunit*maxdifer || container.getWater() > waterunit*maxdifer ){
            currenthp -= 5;
        }else if(currenthp < hp) currenthp+= 5;
        if(container.getLove()< loveunit*maxdifer || container.getLove() > loveunit*maxdifer ){
            currenthp -= 5;
        }else if(currenthp < hp) currenthp+= 5;
        if(container.getSunlight()< waterunit*maxdifer || container.getFood() > waterunit*maxdifer ){
            currenthp -= 5;
        }else if(currenthp < hp) currenthp+= 5;

    }

    /**
     * Method for consuming all resources
     */
    protected void consume(){
        switch (container.getPosition()){
            case 0:
                growSpeed = 1.5; //
                container.setSunlight(container.getSunlight() - sunlightunit);
                container.setLove(container.getLove() - loveunit);
                container.setWater(container.getWater() - waterunit *4);
                container.setFood(container.getFood() - foodunit *0.3);
                break;
            case 1:
                growSpeed = 1.0; //
                container.setSunlight(container.getSunlight() - sunlightunit *2);
                container.setLove(container.getLove() - loveunit *2);
                container.setWater(container.getWater() - waterunit *2);
                container.setFood(container.getFood() - foodunit *0.2);
                break;
            case 2:
                growSpeed = 0.5; //
                container.setSunlight(container.getSunlight() - sunlightunit *4);
                container.setLove(container.getLove() - loveunit *3);
                container.setWater(container.getWater() - waterunit *1);
                container.setFood(container.getFood() - foodunit *0.1);
                break;
        }
    }

    /**
     * Method for level parameter switcher creation
     * @return
     */
    protected int setmodifier(){
        switch (stage){
            case 4: return 4;
            case 3: return 3;
            case 2: return 2;
            default:return 1;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCurrenthp() {
        return currenthp;
    }

    public void setCurrenthp(int currenthp) {
        this.currenthp = currenthp;
    }

    public double getWaterunit() {
        return waterunit;
    }

    public void setWaterunit(double waterunit) {
        this.waterunit = waterunit;
    }

    public double getSunlightunit() {
        return sunlightunit;
    }

    public void setSunlightunit(double sunlightunit) {
        this.sunlightunit = sunlightunit;
    }

    public double getLoveunit() {
        return loveunit;
    }

    public void setLoveunit(double loveunit) {
        this.loveunit = loveunit;
    }

    public double getFoodunit() {
        return foodunit;
    }

    public void setFoodunit(double foodunit) {
        this.foodunit = foodunit;
    }

    public double getGrowSpeed() {
        return growSpeed;
    }

    public void setGrowSpeed(double growSpeed) {
        this.growSpeed = growSpeed;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getMaxheight() {
        return maxheight;
    }

    public void setMaxheight(double maxheight) {
        this.maxheight = maxheight;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public double getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(double periodTime) {
        this.periodTime = periodTime;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", container=" + container +
                ", hp=" + hp +
                ", currenthp=" + currenthp +
                ", waterunit=" + waterunit +
                ", sunlightunit=" + sunlightunit +
                ", loveunit=" + loveunit +
                ", foodunit=" + foodunit +
                ", growSpeed=" + growSpeed +
                ", height=" + height +
                ", maxheight=" + maxheight +
                ", time=" + time +
                ", stage=" + stage +
                ", periodTime=" + periodTime +
                '}';
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

