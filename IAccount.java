public interface IAccount {
    public void deposit(double amount);
    public boolean withdraw(double amount);
    public int getAccountNumber();
    public double getBalance();
}