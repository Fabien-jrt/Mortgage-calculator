package fr.fabien_jrt.mortgage_calculator;

public class Main {
    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal (1k - 2B): ", 1000, 2_000_000_000);
        float annualInterest = (float) Console.readNumber("Annual Interest Rate (in %): ", 0.0001, 1000);
        byte years = (byte) Console.readNumber("Period (in years): ", 1, 100);

        var calculator = new MortgageCalculator(principal, annualInterest, years);
        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
    }

}
