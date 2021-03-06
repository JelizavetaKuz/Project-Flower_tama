package main.java;
public class HumanBeing {

    private static boolean run = true;

    public static boolean isRun() {
        return run;
    }

    public static void setRun(boolean run) {
        HumanBeing.run = run;
    }

    /**
     * method that adds resources
     * @param flower Flower, needed flower
     */
    public static void addFood (Flower flower) {
        Container container = flower.getContainer();
        double food = 10;
        container.setFood(container.getFood() + food);
        flower.setContainer(container);
    }
    /**
     * method that adds resources
     * @param flower Flower, needed flower
     */
    public static void addLove(Flower flower) {
        Container container = flower.getContainer();
        double love = 15;
        container.setLove(container.getLove() + love);
        flower.setContainer(container);

    }
    /**
     * method that adds resources
     * @param flower Flower, needed flower
     */
    public static void addWater(Flower flower) {
        Container container = flower.getContainer();
        double water = 15;
        container.setWater(container.getWater() + water);
        flower.setContainer(container);
    }

    /**
     * exit form programm
     */
    public static void exit(){
        run = false;
    }

    /**
     * Shows info about flower and container
     * @param
     */
    public static String checkstats( Flower flower) {
        return
        flower.getName() + "'s stats:\n"+
        "Health: " + flower.getCurrenthp()+"\n"+
        "Stage of development (1- seed, 2-sprout,\n 3-youngster, 4-full age plant) is "+flower.getStage()+"\n"+
        "Right now it is "+ Utils.round(flower.getHigh())+" cm. high\n"+
        "it is "+flower.getTime()+" hours old\n"+
        "Container stats:\n"+
        "Water: "+ Utils.round(flower.getContainer().getWater())+"\n"+
        "Got "+ Utils.round(flower.getContainer().getSunlight())+ " of sunlight\n"+
        "Have "+ Utils.round(flower.getContainer().getLove())+ " of CO2 (love)\n"+
        "Nutrition of soil: " + Utils.round(flower.getContainer().getFood());

    }

    /**
     * Welcome info
     */
    public static String satrtinfo(){
        return "Hello there, plant lover, lil gamer or whatever you are!\n" +
                "This game here, is simulation of growing  plants.\n"+
                "You just need to plant a little seed and watch it grow.\n"+
                "Do not forget to check it time to time and water, talk or feed it.\n";
    }

    public static String tips() {
            return "1. Changes happen every hour.\n" +
                    "2. Your flower's needs depend on a part of a day.\n"+
                    "3. More - does not mean better. Do not overfeed your flower.\n"+
                    "4. Bigger grows flower - bigger grows his consumption.\n";
}
}
