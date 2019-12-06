import java.util.Random; 
import java.util.Formatter;
import java.util.ArrayList;

public class AccountNumber {
    private int accountNumber;
    static ArrayList<Integer> listofChequingAccounts= new ArrayList<>();
    static ArrayList<Integer> listofSavingsAccounts= new ArrayList<>();
    static ArrayList<Integer> listofCreditCardNumbers= new ArrayList<>();

    public AccountNumber () {
        this.accountNumber = 0;
    }

    public int generateUniqueAccountNumber (ArrayList<Integer> listofAccounts, String leadDigit) {
        int a = new Random().nextInt(100000000); 
        while (listofAccounts.contains(a)) {
            a = new Random().nextInt(100000000);
        }
        if (String.valueOf(a).length() < 8 ) {
            String b= String.format("%08d" , a);                          
            accountNumber = Integer.parseInt(leadDigit + b);
        } else {
            accountNumber = Integer.parseInt(leadDigit + a);
        }
        listofAccounts.add(accountNumber);
        // System.out.println(accountNumber);
        return accountNumber;    
    }
}