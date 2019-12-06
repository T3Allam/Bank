import java.util.Hashtable;
import java.util.Map;

public class BankingService {
    private Hashtable <Integer, Double> bankAccount; 
    private IAccount chequingAccount;
    private IAccount savingsAccount;
    private CreditCard creditCard;

    public BankingService () {
        this.bankAccount = new Hashtable<Integer, Double>();
    }
    
    public IAccount createNewAccount (String type) {
        // IAccount newAccount = null; 
        switch (type) {
            case "Chequing Account":
                this.chequingAccount = new ChequingAccount();
                this.bankAccount.put(this.chequingAccount.getAccountNumber(), this.chequingAccount.getBalance());     
                return this.chequingAccount;
                // System.out.println(bankAccount);           
                // break;
            case "Savings Account":
                this.savingsAccount = new SavingsAccount();
                this.bankAccount.put(this.savingsAccount.getAccountNumber(),  this.savingsAccount.getBalance());                
                return this.savingsAccount;
                // System.out.println(bankAccount);      
                // break;     
        }
        return null;
    }

    public CreditCard applyCredit () {
        this.creditCard = new CreditCard();       
        this.bankAccount.put(this.creditCard.getAccountNumber(), this.creditCard.getBalance());           
        return this.creditCard ;
    }

    public void deposit (IAccount account, double amount){
        account.deposit(amount);
    }

    public boolean withdraw (IAccount account, double amount) {
        account.withdraw(amount);
        return true;
    }
    
    public void transfer (IAccount fromAccount, IAccount toAccount, double amount){
        if (fromAccount.withdraw(amount)){
            toAccount.deposit(amount);
        }
    }    
    
    //display balance of all accounts
    public void displayBalances () {
        System.out.println(bankAccount);
        for (Map.Entry<Integer, Double> entry: bankAccount.entrySet()){
            if (String.valueOf(entry.getKey()).charAt(0) == '1'){
                entry.setValue(this.chequingAccount.getBalance());                
            } else if (String.valueOf(entry.getKey()).charAt(0) == '2') {
                entry.setValue(this.savingsAccount.getBalance()); 
            } else if (String.valueOf(entry.getKey()).charAt(0) == '3') {
                entry.setValue(this.creditCard.getBalance());
            }
        }
        System.out.println(bankAccount);
    }
}