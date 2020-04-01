import com.sun.jdi.IntegerValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TestFlower {
    public static void main(String[] args) throws IOException {
        /*
        Flower flower1 = new Flower("Lilia");
        System.out.println(flower1);
        flower1.switchPlace("Valguse alla");
        flower1.addSomething("vesi",40);
        flower1.addSomething("armastus",40);
        flower1.addSomething("toiduained",40);
        System.out.println(flower1);
        flower1.lose();
        System.out.println(flower1);
        */

        System.out.println(readTimePassed("test.txt"));

    }

    public static void create(String fileName) throws IOException {

        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        if(sc.nextLine()=="0"){
            Scanner scan = new Scanner(System.in);
            System.out.print("Mis nimi tahad oma lillele anda? ");
            String name = scan.next();
            Flower flower = new Flower(name);

            fileWrite(fileName, flower);
        }

    }

    public static void fileWrite(String saveFileName, Flower flower) throws IOException {
        File save = new File(saveFileName);
        Date currentDateUnformat = new Date();
        //System.out.println(currentDateUnformat);
        SimpleDateFormat format = new SimpleDateFormat("hh:dd:MM:yyyy");
        String currentDate = format.format(currentDateUnformat);
        //System.out.println(currentDate);

            PrintWriter pw = new PrintWriter(save);
            pw.println("1");
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
            pw.println(flower.getHeight());
            pw.println(flower.getMaxheight());
            pw.println(flower.getTime());
            pw.println(flower.getStage());
            pw.println(flower.getPeriodTime());
            pw.close();
        }


    public static void fileRead (String fileName, Flower flower) throws FileNotFoundException {
        ArrayList<String> parameters = new ArrayList<>();
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            String str = sc.nextLine();
            parameters.add(str);
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
        flower.setHeight(Double.parseDouble(parameters.get(15)));
        flower.setMaxheight(Double.parseDouble(parameters.get(16)));
        flower.setTime(Integer.parseInt(parameters.get(17)));
        flower.setStage(Integer.parseInt(parameters.get(18)));
        flower.setPeriodTime((Integer.parseInt(parameters.get(19))));




    }

    public static double readTimePassed(String fileName) throws FileNotFoundException {
        ArrayList<String> parameters = new ArrayList<>();
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        Date currentDateUnformate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:dd:MM:yyyy");
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

    public static double readHours(String fileName) throws FileNotFoundException {
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

    public static void sunlightDifference(String saved, String current){
        double hours = timeDifference(saved, current);
        double days = hours%24;



    }



}
