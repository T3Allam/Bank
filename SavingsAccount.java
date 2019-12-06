import java.lang.Math;
import java.time.*;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class SavingsAccount implements IAccount {
    final double interestRate;
    private double balance;
    private int accountNumber;
    private ArrayList<ArrayList<String>> timeStampedDeposit;

    public SavingsAccount(){
        this.balance = 0;
        this.timeStampedDeposit = new ArrayList<ArrayList<String>>();
        this.interestRate = 0.02;        
        this.accountNumber = new AccountNumber().generateUniqueAccountNumber(AccountNumber.listofSavingsAccounts, "2");
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setBalance(double amount) {
        balance = amount;
    }

    public double getBalance() {
        setBalance(0);
        double endInvestment = 0;
        double rn = 1+ interestRate/365;
        for (ArrayList<String> deposit: timeStampedDeposit){                       
            //Extracting data from ArrayList of TimeStampedDeposit
            double startDeposit = Double.parseDouble(deposit.get(0));            
            long period = ChronoUnit.DAYS.between(LocalDate.parse(deposit.get(1)), LocalDate.now());
            //Calculating End Investment..it's daily therefore leap year does not matter as n*t = period
            endInvestment= startDeposit * Math.pow(rn, period);
            balance += endInvestment;
        }
        return balance;
    }

    public void deposit (double amount) {    
        // LocalDate today = LocalDate.now();
        // today = today.minusDays(365);
        ArrayList<String> onetimeDeposit = new ArrayList<>(2);
        onetimeDeposit.add(0, String.valueOf(amount));
        onetimeDeposit.add(1, LocalDate.now().toString());       
        timeStampedDeposit.add(onetimeDeposit);
        System.out.println("You have deposited $"+amount+" into your Savings Accounts.");
    }

    public boolean withdraw(double amount) {
        if (getBalance() < amount) {
            System.out.println("You do not have enough funds in your savings account.");
            return false;
        } else {        
            balance = getBalance() - amount;
            // System.out.println(balance);
            timeStampedDeposit.clear();        
            ArrayList<String> remainingBalance = new ArrayList<>(2);
            remainingBalance.add(0, String.valueOf(balance));
            remainingBalance.add(1, LocalDate.now().toString());   
            timeStampedDeposit.add(remainingBalance);
            System.out.println("You have withdrawn " + amount + " from your Savings Account. Your new balance is: " + getBalance()+".");            
            return true;
        }
    }
}