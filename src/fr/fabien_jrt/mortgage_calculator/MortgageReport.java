package fr.fabien_jrt.mortgage_calculator;

import java.text.NumberFormat;

public class MortgageReport {
    private final NumberFormat currency;
    private MortgageCalculator calculator;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
        currency = NumberFormat.getCurrencyInstance();
    }

    private void printHeader(String label) {
        System.out.println();
        System.out.println(label.toUpperCase());
        for (byte character = 0; character < label.length(); character++) {
            System.out.print('-');
        }
        System.out.println();
    }

    public void printPaymentSchedule() {
        printHeader("payment schedule");

        for (double balance : calculator.getRemainingBalances())
            System.out.println(currency.format(balance));

    }

    public void printMortgage() {
        double mortgage = calculator.calculateMortgage();

        printHeader("mortgage");
        System.out.println("Mortgage (Monthly payments): "
                + currency
                .format(mortgage));
    }
}
