package fr.fabien_jrt.mortgage_calculator;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTH_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int)readNumber("Principal (1k - 2B): ", 1000, 2_000_000_000);
        float annualInterest = (float)readNumber("Annual Interest Rate (in %): ", 0.0001, 1000);
        byte years = (byte)readNumber("Period (in years): ", 1, 100);

        printMortgage(principal, annualInterest, years);

        printPaymentSchedule(principal, annualInterest, years);
    }


    public static void printHeader(String label, char separator) {
        System.out.println();
        System.out.println(label.toUpperCase());
        for (byte character = 0; character < label.length(); character++) {
            System.out.print(separator);
        }
        System.out.println();
    }

    public static void printMortgage(int principal, float annualInterest, byte years) {
        double mortgage = calculateMortgage(principal, annualInterest, years);

        printHeader("mortgage", '-');
        System.out.println("Mortgage (Monthly payments): " + NumberFormat.getCurrencyInstance().format(mortgage));
    }

    public static void printPaymentSchedule(int principal, float annualInterest, byte years) {
        short numberOfPayments = (short)(years * MONTH_IN_YEAR);

        printHeader("payment schedule", '-');
        for (short numberOfPaymentsMade = 1; numberOfPaymentsMade <= numberOfPayments; numberOfPaymentsMade++) {
            System.out.println("Month " + numberOfPaymentsMade + ": "
                    + NumberFormat.getCurrencyInstance()
                    .format(calculateBalance(principal, annualInterest, years, numberOfPaymentsMade)));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Please enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateBalance(int principal, float annualInterest, byte years, short numberOfPaymentsMade) {
        float monthlyInterest = annualInterest / MONTH_IN_YEAR / PERCENT;
        short numberOfPayments = (short)(years * MONTH_IN_YEAR);

        double balance = principal * ((Math.pow((1 + monthlyInterest), numberOfPayments)
                - Math.pow((1 + monthlyInterest), numberOfPaymentsMade))
                / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1));

        return balance;
    }

    public static double calculateMortgage(int principal, float annualInterest, byte years) {
        float monthlyInterest = annualInterest / MONTH_IN_YEAR / PERCENT;
        short numberOfPayments = (short)(years * MONTH_IN_YEAR);

        double mortgage = principal
                * ((monthlyInterest * Math.pow((1 + monthlyInterest), numberOfPayments))
                / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1));

        return mortgage;
    }
}
