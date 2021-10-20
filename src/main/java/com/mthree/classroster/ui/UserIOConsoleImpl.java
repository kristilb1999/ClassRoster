package com.mthree.classroster.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{

    private Scanner in = new Scanner(System.in);

    public void print(String message) {

        System.out.println(message);

    }

    public String readString(String prompt) {

        System.out.println(prompt);

        return in.nextLine();

    }

    public int readInt(String prompt) {

        System.out.println(prompt);

        String userInput = in.nextLine();

        int toReturn = Integer.parseInt(userInput);

        return toReturn;

    }

    public int readInt(String prompt, int min, int max) {

        String userInput;
        int toReturn;

        do {

            System.out.println(prompt);
            userInput = in.nextLine();
            toReturn = Integer.parseInt(userInput);

        } while(toReturn > max || toReturn < min);

        return toReturn;

    }

    public double readDouble(String prompt) {

        System.out.println(prompt);

        String userInput = in.nextLine();

        double toReturn = Double.parseDouble(userInput);

        return toReturn;

    }

    public double readDouble(String prompt, double min, double max) {

        String userInput;
        double toReturn;

        do {

            System.out.println(prompt);
            userInput = in.nextLine();
            toReturn = Double.parseDouble(userInput);

        } while(toReturn > max || toReturn < min);

        return toReturn;

    }

    public float readFloat(String prompt) {

        System.out.println(prompt);

        String userInput = in.nextLine();

        float toReturn = Float.parseFloat(userInput);

        return toReturn;

    }

    public float readFloat(String prompt, float min, float max) {

        String userInput;
        float toReturn;

        do {

            System.out.println(prompt);
            userInput = in.nextLine();
            toReturn = Float.parseFloat(userInput);

        } while(toReturn > max || toReturn < min);

        return toReturn;

    }

    public long readLong(String prompt) {

        System.out.println(prompt);

        String userInput = in.nextLine();

        long toReturn = Long.parseLong(userInput);

        return toReturn;

    }

    public long readLong(String prompt, long min, long max) {

        String userInput;
        long toReturn;

        do {

            System.out.println(prompt);
            userInput = in.nextLine();
            toReturn = Long.parseLong(userInput);

        } while(toReturn > max || toReturn < min);

        return toReturn;

    }

}
