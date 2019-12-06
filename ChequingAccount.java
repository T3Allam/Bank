public class ChequingAccount implements IAccount {
    private double balance;
    private int accountNumber; 
 
    public ChequingAccount(){
        this.balance = 0;
        this.accountNumber = new AccountNumber().generateUniqueAccountNumber(AccountNumber.listofChequingAccounts, "1");
    }

    public int getAccountNumber() {
        return this.accountNumber;    
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit (double amount) {
        this.balance += amount;
        System.out.println("You have successfully deposited $"+amount+" into your Chequing Accounts.");
    }

    public boolean withdraw(double amount) {
        if (amount > getBalance()){
            System.out.println("You do not have enough money in your Chequings Account to make the withdrawal.");
            return false;
        } else {
            balance -= amount;
            System.out.println("You have withdrawn " + amount+ " from your Chequing Account. Your new balance is: " + balance+".");
            return true;
        }
    }
}