package fr.fabien_jrt.mortgage_calculator;

public class MortgageCalculator {
    private final static byte MONTH_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    private int principal;
    private float monthlyInterest;
    private short numberOfPayments;


    public MortgageCalculator(int principal, float annualInterest, byte years) {
        setPrincipal(principal);
        setMonthlyInterest(annualInterest);
        setNumberOfPayments(years);
    }


    private void setPrincipal(int principal) {
        if (principal < 0)
            throw new IllegalArgumentException("Principal cannot be less than 0.");
        else
            this.principal = principal;
    }

    private void setMonthlyInterest(float annualInterest) {
        if (annualInterest < 0)
            throw new IllegalArgumentException("Annual interest cannot be less than 0.");
        else
            this.monthlyInterest = annualInterest /MONTH_IN_YEAR / PERCENT;
    }

    private void setNumberOfPayments(byte years) {
        if(years < 0)
            throw new IllegalArgumentException("Years cannot be less than 0.");
        else
            this.numberOfPayments = (short)(years * MONTH_IN_YEAR);
    }

    private short getNumberOfPayments() {
        return numberOfPayments;
    }

    public double[] getRemainingBalances() {
        var balances = new double[getNumberOfPayments()];

        for (short numberOfPaymentsMade = 1; numberOfPaymentsMade <= balances.length; numberOfPaymentsMade++)
            balances[numberOfPaymentsMade - 1] = calculateBalance(numberOfPaymentsMade);
        return balances;
    }

    public double calculateMortgage() {
        return principal
                * ((monthlyInterest * Math.pow((1 + monthlyInterest), numberOfPayments))
                / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1));
    }

    public double calculateBalance(short numberOfPaymentsMade) {
        return principal * ((Math.pow((1 + monthlyInterest), numberOfPayments)
                - Math.pow((1 + monthlyInterest), numberOfPaymentsMade))
                / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1));
    }
}

