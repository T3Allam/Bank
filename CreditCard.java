import java.lang.Math;
import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;

public class CreditCard {
    private double interestRate;
    private double limit;
    private ArrayList<ArrayList<String>> timeStampedPurchase;
    private ArrayList<ArrayList<String>> updatedPurchases;
    private double balance;
    private int accountNumber;

    public CreditCard () {
		this.limit = 5000;
		this.timeStampedPurchase = new ArrayList<ArrayList<String>>();
        this.balance = 0;
        this.interestRate = 0.10;
        this.accountNumber = new AccountNumber().generateUniqueAccountNumber(AccountNumber.listofCreditCardNumbers, "3");
        this.updatedPurchases = new ArrayList<ArrayList<String>>();
    }

    public int getAccountNumber () {
        return this.accountNumber;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public ArrayList<ArrayList<String>> getListofPurchases() {
        return timeStampedPurchase;
    }

    public ArrayList<ArrayList<String>> getInterestAdjustedPurchases() {
        setUpdatedPurchases();
        // System.out.println("Size is " + updatedPurchases.size() + " and data is:" + updatedPurchases);
        if (updatedPurchases.size() > 0){        
            ArrayList<ArrayList<String>> interestPaymentAdjustedList = new ArrayList<ArrayList<String>>();
            for (int i =0; i<updatedPurchases.size(); i++) {
                ArrayList<String> entry = new ArrayList<>(2);                
                // System.out.println(updatedPurchases.get(i).get(0));
                // System.out.println(timeStampedPurchase.get(i).get(1));
                entry.add(0, updatedPurchases.get(i).get(0));
                entry.add(1, timeStampedPurchase.get(i).get(1));                
                interestPaymentAdjustedList.add(entry);                                                
            }            
            return interestPaymentAdjustedList;
        } 
        return this.updatedPurchases;        
    }

    public double getBalance() {
        setBalance(0);
        setUpdatedPurchases();
        for (ArrayList<String> purchase: updatedPurchases){
            balance += Double.parseDouble(purchase.get(0));
            }
        return this.balance;
    }    
    
    //updating purchases to reflect interest
    public void setUpdatedPurchases() {    
        double rn = 1+ interestRate/365;
        for (ArrayList<String> purchase: updatedPurchases) {
            if (!purchase.get(1).equals(LocalDate.now().toString())){
                double price = Double.parseDouble(purchase.get(0));
                LocalDate dateOfPurchase = LocalDate.parse(purchase.get(1));                   
                long period = ChronoUnit.DAYS.between(LocalDate.parse(purchase.get(1)), LocalDate.now());
                if (period > 21) {
                    dateOfPurchase = dateOfPurchase.plusDays(21);                
                    period = ChronoUnit.DAYS.between(dateOfPurchase, LocalDate.now());           
                    double pricePlusInterest = price * Math.pow(rn,period);                 
                    purchase.set(0, String.valueOf(pricePlusInterest));
                    purchase.set(1, LocalDate.now().toString());
                } 
            }             
        }
    }
    
    //making a payment
    public void makePayment (double amount) {    
        setUpdatedPurchases();        
    	if (getBalance()==0) {
            System.out.println("You do not owe the credit card. Use the extra money to earn interest on your savings account.");            
        } else if (amount> getBalance()) {
            System.out.println("You are overpaying. You only need to pay $"+ getBalance()+". Use the extra money to earn interest in your Savings Account. Please make a payment equivalent to $"+getBalance()+" or less.");
    	} else {  
            while (amount > 0) {
                for ( int i =0 ; i<updatedPurchases.size(); i++) {
                    if (amount< Double.parseDouble(updatedPurchases.get(i).get(0))) {
                        updatedPurchases.get(i).set(0, String.valueOf(Double.parseDouble(updatedPurchases.get(i).get(0))-amount));                                                    
                        amount=0;                   
                        break;
                    } else if (amount> Double.parseDouble(updatedPurchases.get(i).get(0))){                    
                        amount -= Double.parseDouble(updatedPurchases.get(i).get(0));                   
                        updatedPurchases.get(i).set(0, String.valueOf(0));                                       
                    } else if (Double.parseDouble(updatedPurchases.get(i).get(0))==amount) {
                        amount=0;
                        updatedPurchases.get(i).set(0, String.valueOf(0));
                    }
                }
            }  		
        }               
   	}
       
    //making a purchase
    public void makePurchase(double amount) {
        
        if ((amount + getBalance()) > limit) {
            System.out.println("This amount exceeds your credit limit.");
        } else {
            LocalDate today = LocalDate.now();
            // today = today.minusDays(386);
            ArrayList<String> originaltimePurchase = new ArrayList<>(2);
            ArrayList<String> updatetimePurchase = new ArrayList<>(2);
            originaltimePurchase.add(0, String.valueOf(amount));
            originaltimePurchase.add(1, today.toString());    
            updatetimePurchase.add(0, String.valueOf(amount));
            updatetimePurchase.add(1, today.toString());       
            timeStampedPurchase.add(originaltimePurchase);            
            updatedPurchases.add(updatetimePurchase);            
        }
    }
}