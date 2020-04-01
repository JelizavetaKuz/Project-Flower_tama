import com.sun.jdi.IntegerValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

        System.out.println(readReact("test.txt"));

    }

    public static void fileWrite(String saveFileName) throws IOException {
        File save = new File(saveFileName);
        Date currentDateUnformat = new Date();
        //System.out.println(currentDateUnformat);
        SimpleDateFormat format = new SimpleDateFormat("hh:dd:MM:yyyy");
        String currentDate = format.format(currentDateUnformat);
        //System.out.println(currentDate);

        if(!save.exists()) {
            save.createNewFile();
            PrintWriter pw = new PrintWriter(save);
            pw.println(currentDate);
            pw.close();
        }
        else {
            PrintWriter pw = new PrintWriter(save);
            pw.println(currentDate);
            pw.close();
        }

    }
    static double oldNumber = 1;
    public static double readReact(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        Date currentDateUnformate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:dd:MM:yyyy");
        String currentDate = format.format(currentDateUnformate);

        String saved = sc.nextLine();
        String current = currentDate;

        double newNumber =  oldNumber* timeDifference(saved, current);

        return newNumber;
    }

    public static double timeDifference(String saved, String current) {
        int hourDifference;
        String [] savedDivided = saved.split(":");
        String [] currentDivided = current.split(":");

        int savedYear = Integer.valueOf(savedDivided[3]);
        int currentYear = Integer.valueOf(currentDivided[3]);

        int savedMonth = Integer.valueOf(savedDivided[2]);
        int currentMonth = Integer.valueOf(currentDivided[2]);

        int savedDay = Integer.valueOf(savedDivided[1]);
        int currentDay = Integer.valueOf(currentDivided[1]);

        int savedHour = Integer.valueOf(savedDivided[0]);
        int currentHour = Integer.valueOf(currentDivided[0]);

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
