public class Customer {
    public static void main (String[] aStrings) {
       /************* Instantiating the facade class *****************/
        BankingService myBankingService = new BankingService();
    //    Creating New Bank Account - Chequing, Savings, Credit Card
        IAccount myCheqAccount = myBankingService.createNewAccount("Chequing Account");
        IAccount mySavAccount = myBankingService.createNewAccount("Savings Account");
        CreditCard myCredCard = myBankingService.applyCredit();
       
       /****************** Testing Chequing Account *******************/
        myCheqAccount.deposit(100);
        System.out.print("Your balance in the Chequing Account is: $" + myCheqAccount.getBalance());
        System.out.println("Your Chequing Account Number is: " + myCheqAccount.getAccountNumber());
        myCheqAccount.withdraw(80);
        myCheqAccount.withdraw(20);
        
    //    /****************** Testing Savings Account *******************/
        mySavAccount.deposit(100);
        mySavAccount.withdraw(80);
        mySavAccount.withdraw(20);
        mySavAccount.withdraw(20);
        System.out.print("Your balance in the Savings Account is: $"+ mySavAccount.getBalance());
        System.out.println("Your Savings Account Number is: " + mySavAccount.getAccountNumber());

        //     /****************** Testing Credit Card *******************/
        System.out.println("Your Credit Card Account Number is: " + myCredCard.getAccountNumber());
        System.out.println("Your balance on your Credit Card is: $"+ myCredCard.getBalance());

        myCredCard.makePurchase(100);
        myCredCard.makePayment(200);
        myCredCard.makePayment(100);
        myCredCard.makePurchase(100);
        myCredCard.makePayment(100);
        System.out.println("Your list of purchases: " + myCredCard.getListofPurchases());
        System.out.println("Your list of purchases adjusted for interest and previous payments made: " + myCredCard.getInterestAdjustedPurchases());
        System.out.println("Your balance on your Credit Card is: $"+ myCredCard.getBalance());

         /******************Testing Transfers between Chequing and Savings Account *******************/
        myCheqAccount.deposit(100);
        myBankingService.transfer(myCheqAccount, mySavAccount, 20);
        System.out.println(myCheqAccount.getBalance());
        System.out.println(mySavAccount.getBalance());        
        System.out.println("***********************");
        myBankingService.transfer(mySavAccount, myCheqAccount, 20);
        System.out.println(myCheqAccount.getBalance());
        System.out.println(mySavAccount.getBalance());        


        /******************Display All Balances {AccountNumber: amount} *******************/

        myBankingService.displayBalances();


    }
}