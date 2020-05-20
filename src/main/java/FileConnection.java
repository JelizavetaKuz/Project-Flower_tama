package main.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class FileConnection {


    /*
    public static void fileCreate(String fileName) throws IOException {

        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
            Scanner scan = new Scanner(System.in);
            System.out.println("You even can give him a name!");
            System.out.println("So lets start!");
            System.out.println("How would you like to name your flower?: ");
            String name = scan.next();
            Flower flower = new Flower(name);

            fileWrite(fileName, flower);
        } else {
            try (Scanner sc = new Scanner(file)) {
                String isDead = sc.nextLine();
                if (isDead.equals("1")) {
                    file.createNewFile();
                    Scanner scan = new Scanner(System.in);
                    System.out.println("\nYou can try once again!");
                    System.out.println("How would you like to name your new flower?: ");
                    String name = scan.next();
                    Flower flower = new Flower(name);

                    fileWrite(fileName, flower);
                }
            }
        }

    }

     */

    public static int fileCheck(String fileName) throws IOException {

        File file = new File(fileName);
        if (!file.exists())
            return 2;

        else
            try (Scanner sc = new Scanner(file)) {
                String isDead = sc.nextLine();
                if (isDead.equals("1"))
                    return 1;
                else
                    return 0;
            }
    }
    /**
     * Create file and flower object at start
     * @param fileName String, file name (constant)
     * @param flowerName String, name of flower
     * @throws IOException
     */

    public static Flower fileCreate(String fileName, String flowerName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        Flower flower = new Flower(flowerName);
        fileWrite(fileName, flower);
        return flower;
    }

    /**
     * method, that gets flower name from file, if it is
     * @param fileName string, file name (constant)
     * @return String,
     * @throws FileNotFoundException
     */
    public static String getFlowerName(String fileName)throws FileNotFoundException {
        ArrayList<String> parameters = new ArrayList<>();
        File file = new File(fileName);
        try (Scanner sc = new Scanner(file)) {
            String str = "";

            for (int i = 0; i < 8; i++) {
                str = sc.nextLine();
            }
            return str;
        }
    }



    /**
     * File write on close.
     * @param saveFileName String
     * @param flower Flower
     * @throws IOException
     */
    public static void fileWrite(String saveFileName, Flower flower) throws IOException {
        File save = new File(saveFileName);
        Date currentDateUnformat = new Date();
        //System.out.println(currentDateUnformat);
        SimpleDateFormat format = new SimpleDateFormat("kk:dd:MM:yyyy");
        String currentDate = format.format(currentDateUnformat);
        //System.out.println(currentDate);

        try (PrintWriter pw = new PrintWriter(save)) {
            pw.println("0");
            pw.println(currentDate);
            pw.println(flower.getContainer().getFood());
            pw.println(flower.getContainer().getLove());
            pw.println(flower.getContainer().getPosition());
            pw.println(flower.getContainer().getSunlight());
            pw.println(flower.getContainer().getWater());
            pw.println(flower.getName());
            pw.println(flower.getHp());
            pw.println(flower.getCurrenthp());
            pw.println(flower.getWaterunit());
            pw.println(flower.getSunlightunit());
            pw.println(flower.getLoveunit());
            pw.println(flower.getFoodunit());
            pw.println(flower.getGrowSpeed());
            pw.println(flower.getHigh());
            pw.println(flower.getMaxheight());
            pw.println(flower.getTime());
            pw.println(flower.getStage());
            pw.println(flower.getPeriodTime());
        }
    }


    /**
     * Getting all states of flower at start
     * @param fileName String
     * @param flower Flower
     * @throws FileNotFoundException
     */
    public static void fileRead (String fileName, Flower flower) throws FileNotFoundException {
        ArrayList<String> parameters = new ArrayList<>();
        File file = new File(fileName);
        try(Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                parameters.add(str);
            }
        }

        Container thisConteiner = flower.getContainer();
        thisConteiner.setFood(Double.parseDouble(parameters.get(2)));
        thisConteiner.setLove(Double.parseDouble(parameters.get(3)));
        thisConteiner.setPosition(Integer.parseInt(parameters.get(4)));
        thisConteiner.setSunlight(Double.parseDouble(parameters.get(5)));
        thisConteiner.setWater(Double.parseDouble(parameters.get(6)));
        flower.setContainer(thisConteiner);
        flower.setName(parameters.get(7));
        flower.setHp(Integer.parseInt(parameters.get(8)));
        flower.setCurrenthp(Integer.parseInt(parameters.get(9)));
        flower.setWaterunit(Double.parseDouble(parameters.get(10)));
        flower.setSunlightunit(Double.parseDouble(parameters.get(11)));
        flower.setLoveunit(Double.parseDouble(parameters.get(12)));
        flower.setFoodunit(Double.parseDouble(parameters.get(13)));
        flower.setGrowSpeed(Double.parseDouble(parameters.get(14)));
        flower.setHigh(Double.parseDouble(parameters.get(15)));
        flower.setMaxheight(Double.parseDouble(parameters.get(16)));
        flower.setTime(Double.parseDouble(parameters.get(17)));
        flower.setStage(Integer.parseInt(parameters.get(18)));
        flower.setPeriodTime((Double.parseDouble(parameters.get(19))));




    }

    /**
     * Get period of time between last time log and now
     * @param fileName String
     * @return double, how much time has passed
     * @throws FileNotFoundException
     */
    public static double readTimePassed(String fileName) throws FileNotFoundException {
        ArrayList<String> parameters = new ArrayList<>();
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        Date currentDateUnformate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("kk:dd:MM:yyyy");
        String currentDate = format.format(currentDateUnformate);

        while (sc.hasNextLine()){
            String str = sc.nextLine();
            parameters.add(str);
        }

        String saved = parameters.get(1);
        String current = currentDate;

        double timePassed = timeDifference(saved, current);

        return timePassed;
    }

    /**
     * Read time from file
     * @param fileName String
     * @return int, time
     * @throws FileNotFoundException
     */
    public static int readHours(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        ArrayList<String> parameters = new ArrayList<>();

        while (sc.hasNextLine()){
            String str = sc.nextLine();
            parameters.add(str);
        }

        String saved = parameters.get(1);
        String [] savedDivided = saved.split(":");
        int savedHour = Integer.parseInt(savedDivided[0]);


        return savedHour;
    }


    /**
     * Method to calculate how much time has passed
     * @param saved String, from file
     * @param current String, current date
     * @return double, difference
     */
    public static double timeDifference(String saved, String current) {
        int hourDifference;
        String [] savedDivided = saved.split(":");
        String [] currentDivided = current.split(":");

        int savedYear = Integer.parseInt(savedDivided[3]);
        int currentYear = Integer.parseInt(currentDivided[3]);

        int savedMonth = Integer.parseInt(savedDivided[2]);
        int currentMonth = Integer.parseInt(currentDivided[2]);

        int savedDay = Integer.parseInt(savedDivided[1]);
        int currentDay = Integer.parseInt(currentDivided[1]);

        int savedHour = Integer.parseInt(savedDivided[0]);
        int currentHour = Integer.parseInt(currentDivided[0]);

        // Programmeerimisharjutused (LTAT.03.007) 2019/2020 1.loengu programm
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int dayDifference = 0;

        for (int i = savedYear + 1; i < currentYear; i = i + 1) {
            dayDifference = dayDifference + 365;
            if ((i % 4 == 0) && ((i % 100 != 0) || (i % 400 == 0)))
                dayDifference = dayDifference + 1;
        }

        for (int i = 1; i < currentMonth; i = i + 1) {
            dayDifference = dayDifference + months[i - 1];
        }
        dayDifference = dayDifference + currentDay;

        if ((currentMonth > 1) && (currentYear % 4 == 0) && ((currentYear % 100 != 0) || (currentYear % 400 == 0)))
            dayDifference = dayDifference + 1;


        if (savedYear < currentYear)
            dayDifference = dayDifference + 365;

        for (int i = 1; i < savedMonth; i = i + 1) {
            dayDifference = dayDifference - months[i - 1];
        }
        dayDifference = dayDifference - savedDay - 1;

        if ((savedYear < currentYear) && (savedMonth < 3) &&
                (savedYear % 4 == 0) && ((currentYear % 100 != 0) || (currentYear % 400 == 0)))
            dayDifference = dayDifference + 1;

        hourDifference = dayDifference*24;
        hourDifference += currentHour-savedHour;

        return hourDifference;
    }

}
