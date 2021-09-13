import java.lang.invoke.StringConcatFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;

/*
Usama Hameed
Account Management System
 */

class Customer
{
    /*
        Important Note As The Description Says that the Customer Can Open Only Two Types of Account and Also a Customer Can Open Both Types of Account At Once So Inorder to handle this
        we will use the

                [0] -- Index Checking Account
                [1] -- Index Saving Account

           private Boolean Account_Types []; -->this array will store the status weather the customer object has both accounts or no account
           private Bank_Accounts Customer_Opened_Accounts[]; -->This Will hold the objects of the Saving account ad Checking Account
     */

    private String Name;
    private String Address;
    private String phoneNumber;
    private String accountNumber;

    private Bank_Accounts Customer_Opened_Accounts[];
    private Boolean Account_Types[];


    //----------------------------------------------------------------------------- Parameterized Constructor of Class Customer -----------------------------------------------------------------------------
    Customer(String Name,String Address, String phoneNumber, String accountNumber)
    {
        this.Name=Name;
        this.Address=Address;
        this.phoneNumber=phoneNumber;
        this.accountNumber=accountNumber;


        this.Account_Types =new Boolean [2];
        this.Account_Types[0]=false;
        this.Account_Types[1]=false;

        Customer_Opened_Accounts=new Bank_Accounts[2];  //Two Types of Account A Customer Can Open Only....
    }

    Customer(Customer customerDetails)
    {
        this.Name =customerDetails.Name;
        this.accountNumber= customerDetails.accountNumber;;
        this.phoneNumber= customerDetails.phoneNumber;
        this.Address=customerDetails.Address;

        this.Account_Types =new Boolean [2];
        this.Account_Types[0]=customerDetails.Account_Types[0];
        this.Account_Types[1]=customerDetails.Account_Types[1];

        this.Customer_Opened_Accounts=new Bank_Accounts[2];
        this.Customer_Opened_Accounts[0] =customerDetails.Customer_Opened_Accounts[0];
        this.Customer_Opened_Accounts[1] =customerDetails.Customer_Opened_Accounts[1];
    }

    Customer()
    {
        this.Account_Types=new Boolean[2];
        this.Customer_Opened_Accounts=new Bank_Accounts[2];
        this.Address="";
        this.phoneNumber="";
        this.accountNumber="";
    }
    //--------------------------------------------------------- Set The Checking Account if Needed ------------------------------------------------------------------------------------
    public void setCheckingAccount(long accountBalance, String accountDateTimeCreation)
    {
        if(this.Account_Types[0])
        {
            System.out.println("Checking Account Already Exists");
        }
        else {
            this.Account_Types[0] = true;
            this.Customer_Opened_Accounts[0]=new Checking_Account(this.accountNumber+"C",accountBalance,accountDateTimeCreation);
            this.Customer_Opened_Accounts[0].setCustomerDetails(this);
            System.out.println("Checking Account Setted ....");
        }
    }


    //--------------------------------------------------------- Set The Saving Account if Needed --------------------------------------------------------------------------------------
    public void setSavingAccount(long accountBalance, String accountDateTimeCreation)
    {
        if(this.Account_Types[1])
        {
            System.out.println("Saving Account Already Exists");
        }
        else {
            this.Account_Types[1] = true;
            this.Customer_Opened_Accounts[1]=new Saving_Account(this.accountNumber+"S",accountBalance,accountDateTimeCreation);
            this.Customer_Opened_Accounts[1].setCustomerDetails(this);
            System.out.println("Saving Account Setted ....");
        }
    }
    //--------------------------------------------------------- Functions To Print Customer Details -----------------------------------------------------------------------------------
    public void print_Customer_Details()
      {
      System.out.println("-->Customer Details");
      System.out.println("Name\t\t\tAddress\t\t\t\tPhone Number\t\tAccount Number\t\tStatus Checking Account"+"\t\t"+"Status Saving Account");
      System.out.println(this.Name+"\t"+this.Address+"\t\t\t\t"+this.phoneNumber+"\t\t\t\t"+this.accountNumber+"\t\t\t\t"+this.Account_Types[0]+"\t\t\t\t\t\t"+this.Account_Types[1]);

      if(this.Account_Types[0]==false && this.Account_Types[1]==true) // i-e Checking False and Saving True
      {
          System.out.println("\n----->Saving Account Details");
          System.out.println("Saving Account Number : "+this.Customer_Opened_Accounts[1].getAccountNumber());
          System.out.println("Balance : "+this.Customer_Opened_Accounts[1].getAccountBalance());
          System.out.println("Account Creation Date : "+this.Customer_Opened_Accounts[1].getAccountDateTimeCreation());

          System.out.println("\n----->Checking Account Details");
          System.out.println("\nChecking Account Status : False (Not Opened Yet)");
      }
      else  if(this.Account_Types[0]==true && this.Account_Types[1]==false) // i-e Checking True and Saving False
      {
          System.out.println("\n----->Checking Account Details");
          System.out.println("Checking Account Number : "+this.Customer_Opened_Accounts[0].getAccountNumber());
          System.out.println("Balance : "+this.Customer_Opened_Accounts[0].getAccountBalance());
          System.out.println("Account Creation Date : "+this.Customer_Opened_Accounts[0].getAccountDateTimeCreation());

          System.out.println();
          System.out.println("\n----->Saving Account Details");
          System.out.println("\nSaving Account Status : False (Not Opened Yet)");

      }
      else  if(this.Account_Types[0]==true && this.Account_Types[1]==true) // i-e Checking True and Saving True Customer has both accounts
      {
          System.out.println("\n----->Checking Account Details");
          System.out.println("Saving Account Number : "+this.Customer_Opened_Accounts[0].getAccountNumber());
          System.out.println("Balance : "+this.Customer_Opened_Accounts[0].getAccountBalance());
          System.out.println("Account Creation Date : "+this.Customer_Opened_Accounts[0].getAccountDateTimeCreation());

          System.out.println();

          System.out.println("\n----->Saving Account Details");
          System.out.println("Saving Account Number : "+this.Customer_Opened_Accounts[1].getAccountNumber());
          System.out.println("Balance : "+this.Customer_Opened_Accounts[1].getAccountBalance());
          System.out.println("Date of Creation : "+this.Customer_Opened_Accounts[1].getAccountDateTimeCreation());
      }
    }

    //----------------------------------------------------------------------- Get Saving Account Object -----------------------------------------------------------------------------------------
    public Bank_Accounts getSavingAccountObject()
    {
        // 1 represent saving account
        return this.Customer_Opened_Accounts[1];
    }

    //----------------------------------------------------------------------- Get Checking Account Object -----------------------------------------------------------------------------------------
    public Bank_Accounts getCheckingAccountObject()
    {
        // 0 index represent checking account
        return this.Customer_Opened_Accounts[0];
    }

    //------------------------------------------------------------------------ Get Customer Detail -----------------------------------------------------------------------------------------------
    public Customer getCustomerDetails()
    {
        return this;
    }

    //----------------------------------------------------------------------- Get Generic Account ID ---------------------------------------------------------------------------------------------
    public String get_GENERIC_Account_ID()
    {
        return this.accountNumber;
    }

    // get saving account status
    public Boolean getSavingAccountStatus()
    {
        //1 to saving account
        return Account_Types[1];
    }

    // get checking account status
    public Boolean getCheckingAccountStatus()
    {
        //0 to checking account
        return Account_Types[0];
    }

    //------------------------------------------------------------------------ Account Deletes ---------------------------------------------------------------------------------------------------
    // Delete Saving Account
    public void deleteSavingAccounts()
    {
        System.out.println("delete saving called *********************");
        //Index 1 because Index 1 is assigned to the saving accounts
        this.Account_Types[1]=false;
        this.Customer_Opened_Accounts[1]=null;
    }

    // Delete checking Account
    public void deleteCheckingAccout()
    {
        System.out.println("deleye current called ******************");
        //Index 0 because Index 1 is assigned to the saving accounts
        this.Account_Types[0]=false;
        this.Customer_Opened_Accounts[0]=null;
    }
}

class Transaction
{
    private String transactionTimeDate;
    private long transactionAmount; //the amount charges
    private long remainingBalance; //will have the current balance of that account


    //Parameterized Constructor
    Transaction(String transactionTimeDate, long transactionAmount, long remainingBalance)
    {
        this.transactionTimeDate=transactionTimeDate;
        this.transactionAmount=transactionAmount;
        this.remainingBalance=remainingBalance;
    }

    public void printTransaction()
    {
        System.out.println(this.transactionTimeDate+"\t\t"+this.transactionAmount+"\t\t\t\t\t"+this.remainingBalance);
    }
}

class Deductions
{
    private String accountType;
    private String typeofDeduction;
    private String dateOfDeduction;
    private long deductionAmount;
    private long remainingBalance;


    Deductions(String accountType, String typeofDeduction, String dateOfDeduction, long deductionAmount, long remainingBalance)
    {
        this.dateOfDeduction=dateOfDeduction;
        this.accountType=accountType;
        this.deductionAmount=deductionAmount;
        this.remainingBalance=remainingBalance;
        this.typeofDeduction=typeofDeduction;
    }

    public void printDeductionsDetails()
    {
        System.out.println(this.dateOfDeduction+"\t\t"+this.accountType+"\t\t"+this.typeofDeduction+"\t\t"+this.deductionAmount+"\t\t\t\t"+this.remainingBalance);
    }
}
class Bank_Accounts
{
    private String accountNumber;
    private long accountBalance;
    private String accountDateTimeCreation;
    private Vector<Transaction> Account_Transaction_List;
    private Customer customerDetails;

    //------------------------------------------------------------ Default Constructors ----------------------------------------------------------------------------------------------------
    Bank_Accounts()
    {
        this.accountNumber="";
        this.accountBalance=0;
        this.accountDateTimeCreation="";
        Account_Transaction_List=new Vector<Transaction>();
        customerDetails=new Customer();
    }


    //------------------------------------------------------------ Constructor For Bank Accounts Class ---------------------------------------------------------------------------------------
    Bank_Accounts(String accountNumber, long accountBalance, String accountDateTimeCreation)
    {
        this.accountBalance=accountBalance;
        this.accountNumber =accountNumber;
        this.accountDateTimeCreation=accountDateTimeCreation;
        Account_Transaction_List =new Vector<Transaction>();
    }

    //------------------------------------------------------------ Set Account Number ---------------------------------------------------------------------------------------------------------
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber=accountNumber;
    }

    //------------------------------------------------------------ Set Account Balance ---------------------------------------------------------------------------------------------------------
    public void setAccountBalance(long accountBalance)
    {
        this.accountBalance=accountBalance;
    }

    //------------------------------------------------------------ Set Account Creation Data Time ---------------------------------------------------------------------------------------------------------
    public void setAccountDateTimeCreation(String dateTimeCreation)
    {
        this.accountDateTimeCreation=dateTimeCreation;
    }

    //------------------------------------------------------------ Set Customer Details ---------------------------------------------------------------------------------------------------------------
    public void setCustomerDetails(Customer customerDetails)
    {
        this.customerDetails=new Customer(customerDetails);
    }
    //------------------------------------------------------------ Get Account Creation Data Time ---------------------------------------------------------------------------------------------------------
    public String getAccountNumber()
    {
        return this.accountNumber;
    }

    //------------------------------------------------------------ Get Account Balance ---------------------------------------------------------------------------------------------------------
    public long getAccountBalance()
    {
        return this.accountBalance;
    }


    //------------------------------------------------------------ Get Account Creation Data Time ---------------------------------------------------------------------------------------------------------
    public String getAccountDateTimeCreation()
    {
        return this.accountDateTimeCreation;
    }

    //--------------------------------------------------------------- Get Customer Account Holder Details --------------------------------------------------------------------------------------------
    public Customer getCustomerDetails()
    {
        return this.customerDetails;
    }

    //-------------------------------------------------------------- Functions of Bank Account Class --------------------------------------------------------------------------------------------------
   //1. Make Deposit()
    public void makeDeposit(long deposit_amount)
    {
        this.accountBalance +=deposit_amount;       //adding funds to the account


        //adding th transaction to the Account Transaction List
        Transaction temp_obj_transaction=new Transaction(Get_Current_Date(),deposit_amount,this.accountBalance);
        Account_Transaction_List.add(temp_obj_transaction);

        System.out.println("Deposit Amount Successful !!!! ");
        System.out.println("Deposit Amount : "+ deposit_amount);
        System.out.println("New Account Balance : "+this.accountBalance);
        System.out.println("Date of Transaction : "+Get_Current_Date());
    }

    //2. Make Withdraw()
    public void makeWithdrawal(long withdraw_amount)
    {
        if (withdraw_amount < this.accountBalance)
        {
            this.accountBalance -=withdraw_amount;

            long negative_transaction_Withdraw =-1*withdraw_amount;
            //adding th transaction to the Account Transaction List
            Transaction temp_obj_transaction=new Transaction(Get_Current_Date(),negative_transaction_Withdraw,this.accountBalance);
            Account_Transaction_List.add(temp_obj_transaction);

            System.out.println("Withdraw Amount Successful !!!! ");
            System.out.println("Withdraw Amount : "+ withdraw_amount);
            System.out.println("New Account Balance : "+this.accountBalance);
            System.out.println("Date of Transaction : "+Get_Current_Date());
        }
        else
        {
            System.out.println("In Sufficient Balance To Perform With Draw Transaction \nQuiting System ...........");
        }
    }

    //3. Check Balance
    public void checkBalance()
    {
        System.out.println("\n\n#################################################################################");
        System.out.println("------------------ Customer Details ------------------");
        this.customerDetails.print_Customer_Details();


        System.out.println("\n\n------------------ Customer Bank Balance Display ------------------");
        System.out.println("-->Balance Check ");
        System.out.println("Account Balance :"+this.getAccountBalance());
        System.out.println("#################################################################################\n\n");
    }

    //4. Print Statement
    public void printStatement()
    {
        System.out.println("\n--------------------------------- Customer Bank Statement -----------------------------------------------");
        this.customerDetails.print_Customer_Details();

        System.out.println("-->Transaction Details of Account :");

        System.out.println("Date\t\tTime\t\tTrasaction Amount\tRemaining Balance \t\t\t\t -ive sign : Show Withdrawal & +no sign : Show deposit");
        for (Transaction temp_obj_print:Account_Transaction_List)
        {
            temp_obj_print.printTransaction();
        }
        System.out.println();
    }

    // 5. Overloaded make Withdraw Only for Checking Accounts as the customer can
    // withdraw more than their own account but max up to 5000
    public void makeWithdraw(long withdraw_amount, boolean check_CheckingAccount, long maxWithdrawLimit_Check_ACC) {
        if (check_CheckingAccount == true) {
            if (withdraw_amount <= maxWithdrawLimit_Check_ACC) // check balance should be greater than withdraw amount
            {
                this.accountBalance -= withdraw_amount; // performing the with draw operation now

                /*
                 * Code to Get the Current System Date And Time This will help in getting the
                 * exact time at which the transaction was performed in the system
                 */
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String stringDateTimeNow = now.format(dtf); // getting the time and date at which we did the withdrawal
                // --------------------------------------------------------------------------------------------------------------

                long previous_balance = this.accountBalance;

                long negative_TransactionAmount = -1 * withdraw_amount; // -1 to show that we are taking money out of
                // the account
                /*
                 * Below object will temporarily hold the details so that we can push that in to
                 * vector DS
                 */
                Transaction temp_var_Transaction =new Transaction(getAccountDateTimeCreation(),negative_TransactionAmount,this.getAccountBalance());

                Account_Transaction_List.add(temp_var_Transaction); // adding object to the Vector DS

                System.out.println("Withdraw Amount Successful !!!! ");
                System.out.println("Withdraw Amount : "+ withdraw_amount);
                System.out.println("New Account Balance : "+this.accountBalance);
                System.out.println("Date of Transaction : "+Get_Current_Date());
            }
            else
            {
                System.out.println("Insufficient Balance To Perform the Transaction \n Quiting the System ......");
                return;
            }
        }
        else {
            System.out.println("Trying to Use a Feature That is Only Available to the Checking Account Holders ");
        }

    }

    //5.Transfer Amount
    public Vector<Customer> TransferAmount(long amountToTransfer, String Recipient_Account_Number,Vector<Customer> Account_Holder_List,int  Account_Type_Index)
    {
        return null;
    }
    //6.Calculate Zakat
    public void calculateZakat() {}
    //7.Display All Deductions
    public void displayDeductions() {}

    //8. Calculate Interest
    public void calculateInterest() {}

    //9.Set Interest()
    public void setInterestRate(double interestRate) {}
    //10. Make Withdraw Override version only for Checking Accounts
    //dummy function to achieve polymorphism
    public void makeWithdraw(long amount, boolean flag) {}

    //------------------------------------------------------------ Helper Function to Get the System Time and Date -----------------------------------------------------------------------------------
    private String Get_Current_Date()
    {
          /*  //GETTING CURRENT DATE AND TIME
            Code to Get the Current System Date And Time
            This will help in getting the exact time at which the transaction was performed in the system
            */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String stringDateTimeNow =now.format(dtf);  //getting the time and date at which we did the withdrawal

        return stringDateTimeNow;
    }

}

class Saving_Account extends Bank_Accounts
{
    //Data Members
    private double interestRate;
    private long interestAmount;
    private int interestPeriod;
    private long zakatAmount;
    private Vector<Deductions> Saving_Account_Deductions;   //---> Vector of Transactions that will grow dynamically as the number of transactions will increase by the user

    Saving_Account()
    {
        super();
        interestRate=2.5;
        interestAmount=0L;
        interestPeriod=365;
        Saving_Account_Deductions =new Vector<Deductions>();
    }

    //---------------------------------------------------------------------------- Parameterized Constructor --------------------------------------------------------------------
    Saving_Account(String accountNumber, long accountBalance, String accountDateTimeCreation)
    {
        super(accountNumber,accountBalance,accountDateTimeCreation);
        interestRate=2.5;
        interestAmount=0;
        interestPeriod=365;
        Saving_Account_Deductions =new Vector<Deductions>();
    }

    public void setInterestRate(double interestRate)
    {
        this.interestRate=interestRate;
    }

    //1. Make Deposit()
    @Override
    public void makeDeposit(long deposit_amount)
    {
            super.makeDeposit(deposit_amount);
    }


//    2.Make Withdraw
    @Override
    public void makeWithdrawal(long withdraw_amount)
    {
        if(super.getAccountBalance() > withdraw_amount)
        {
            super.makeWithdrawal(withdraw_amount);
        }
        else
        {
            System.out.println("Insufficient Balance To Perform the Transaction \n Quiting the System ......");
        }
    }

    //3. Calculate Interest in the Saving Account
    @Override
    public void calculateInterest()
    {
        this.interestAmount =(long) (super.getAccountBalance()*this.interestPeriod*this.interestRate);

        Deductions temp_obj_Deductions =new Deductions("Saving Account","Interest",Get_Current_Date(),this.interestAmount,super.getAccountBalance());
        Saving_Account_Deductions.add(temp_obj_Deductions);
    }

    @Override
    //4. Calculate Zakat
    public void calculateZakat()
    {
        if(super.getAccountBalance() >=20000)
        {
            this.zakatAmount =(long)(super.getAccountBalance() *2.5)/100L;
            super.makeWithdrawal(zakatAmount);

            Deductions temp_obj_Deductions =new Deductions("Saving Account","Zakat Type",Get_Current_Date(),zakatAmount,super.getAccountBalance());
            Saving_Account_Deductions.add(temp_obj_Deductions);
        }
        else
        {
            System.out.println("Insufficient Balance To Calculate Zakat .. \n Quiting System ....");
        }
    }


    //5. Display Deductions()
    public void displayDeductions()
    {
        System.out.println("-->Saving Account Deduction List");

        super.getCustomerDetails().print_Customer_Details();        //printing the Customer Information
        System.out.println("Date\t\t\t\t\tType\t\t\t\tDeduction Type\tDeduction Amount\tRemaining Balance");

        for (Deductions temp_obj_deduction :Saving_Account_Deductions)
        {
            temp_obj_deduction.printDeductionsDetails();
        }
    }

    //-- Transfer Amount
    //5.Transfer Amount
    public Vector<Customer> TransferAmount(long amountToTransfer, String Recipient_Account_Number,Vector<Customer> Account_Holder_List,int Account_Type_Index)
    {

        String Account_Type="";
        if(Account_Type_Index ==0)
        {
            Account_Type="Checking Account";
        }
        else if(Account_Type_Index==1)
        {
                Account_Type="Saving Account";
        }
        else
        {
            System.out.println("Invalid Type of Account Index Passed ..... Check The Last Parameter At Function Call 0 for CHECKING ACCOUNT & 1 for SAVING ACCOUNT");
        }

        int recipientAccountID_Index=-1;
        for(int i=0; i<Account_Holder_List.size();i++)
        {
            if(Recipient_Account_Number.equals(Account_Holder_List.get(i).get_GENERIC_Account_ID()))
            {
                recipientAccountID_Index=i;
                System.out.println("Account Found @ index"+i);

                //perform transfer
                Customer temp_obj=Account_Holder_List.get(recipientAccountID_Index);



                //for transfer to Saving Account
                if(Account_Type.equals("Saving Account"))
                {
                    if(temp_obj.getSavingAccountStatus())
                    {
                        if(this.getAccountBalance() >amountToTransfer)
                        {
                            Account_Holder_List.remove(recipientAccountID_Index);
                            temp_obj.getSavingAccountObject().makeDeposit(amountToTransfer);
                            this.makeWithdrawal(amountToTransfer);

                            System.out.println("\n=================================================================");
                            System.out.println("-->Transfer From Saving Account To Saving Account Successful ");
                            System.out.println("Your New Balance is : "+this.getAccountBalance());
                            System.out.println("=================================================================");

                            Account_Holder_List.add(recipientAccountID_Index,temp_obj);
                            return Account_Holder_List;
                        }
                        else
                        {
                            System.out.println("\n=================================================================");
                            System.out.println("-->Transfer From Saving Account To Saving Account UnSuccessful Due To Insufficient Funds");
                            System.out.println("Your Current Balance is : "+this.getAccountBalance());
                            System.out.println("Kindly Increase Your Balance To Make A Fund Transfer ThankYou !!!! ");
                            System.out.println("=================================================================");
                        }
                        return Account_Holder_List;
                    }
                    else
                    {
                        System.out.println("Sorry !!! The Transfer Account has No Saving Account Opened Yet");
                        System.out.println("Transfer Not Successful");
                    }
                }

                //for transfer to Checking Account
                if(Account_Type.equals("Checking Account"))
                {
                    if(temp_obj.getCheckingAccountStatus())
                    {
                        if(this.getAccountBalance() >amountToTransfer)
                        {
                            Account_Holder_List.remove(recipientAccountID_Index);
                            temp_obj.getCheckingAccountObject().makeDeposit(amountToTransfer);
                            this.makeWithdrawal(amountToTransfer);

                            System.out.println("\n=================================================================");
                            System.out.println("-->Transfer From Saving Account To Checking Account Successful ");
                            System.out.println("Your New Balance is : "+this.getAccountBalance());
                            System.out.println("=================================================================");

                            Account_Holder_List.add(recipientAccountID_Index,temp_obj);
                            return Account_Holder_List;
                        }
                        else
                        {
                            System.out.println("\n=================================================================");
                            System.out.println("-->Transfer From Saving Account To Saving Account UnSuccessful Due To Insufficient Funds");
                            System.out.println("Your Current Balance is : "+this.getAccountBalance());
                            System.out.println("Kindly Increase Your Balance To Make A Fund Transfer ThankYou !!!! ");
                            System.out.println("=================================================================");
                        }
                        return Account_Holder_List;

                    }
                    else
                    {
                        System.out.println("Sorry !!! The Transfer Account has No Saving Account Opened Yet");
                        System.out.println("Transfer Not Successful");
                    }
                }
            }
        }
        if(recipientAccountID_Index ==-1)
        {
            System.out.println("The Recipient Account ID Not Founded .. Check Account ID\n Quiting System.....");
        }

        //Returning as Null Becuase if there is issue the Null Exception will be called during the process so that we have idea that the changed vector is not return yet
        System.out.println("\n\nThe Issue With Transcation Vector not returned yet");
        return null;
    }


    //------------------------------------------------------------ Helper Function to Get the System Time and Date -----------------------------------------------------------------------------------
    private String Get_Current_Date()
    {
          /*  //GETTING CURRENT DATE AND TIME
            Code to Get the Current System Date And Time
            This will help in getting the exact time at which the transaction was performed in the system
            */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String stringDateTimeNow =now.format(dtf);  //getting the time and date at which we did the withdrawal

        return stringDateTimeNow;
    }

}

class Checking_Account extends Bank_Accounts
{
    //Data Members
    private long transactionFee;
    private int withdrawTransactionCount;
    private int depositTransactionCount;
    private long maxWithdrawalAmountCheckingAcc=5000L; //max checking account holder can with draw money in case the want to with draw more money than account balance which is 5000

    private Vector<Deductions> Tax_Deductions_List_Checking_Acc=new Vector<Deductions>();        //vector to store all the tax deductions of the checking account

    //Default Constructor
    Checking_Account()
    {
        super();
        transactionFee=10L;
        withdrawTransactionCount=2;
        depositTransactionCount=2;
        maxWithdrawalAmountCheckingAcc=500L;
    }

    //Parameterized Constructor
    Checking_Account(String accountNumber, long accountBalance, String accountDateTimeCreation)
    {
        super(accountNumber,accountBalance,accountDateTimeCreation);
        transactionFee=10L;
        withdrawTransactionCount=2;
        depositTransactionCount=2;
        maxWithdrawalAmountCheckingAcc=5000L;
    }

    // 1. Make Withdraw on Checking Account
    @Override
    public void makeWithdraw(long withdraw_amount, boolean flag) {
        if (withdraw_amount <= maxWithdrawalAmountCheckingAcc && withdrawTransactionCount > 0) {
            super.makeWithdraw(withdraw_amount,true,maxWithdrawalAmountCheckingAcc);

            --this.withdrawTransactionCount;

            long tax_deduction_amount = 0L; // as the customer falls under free transaction category

            // Adding the Deduction Made to The Vector :Tax_Deductions_List_Checking_Acc
            Deductions temp_obj_withdraw_deduction=new Deductions("Checking Account","Withdrawal Tax",Get_Current_Date(),tax_deduction_amount,this.getAccountBalance());

            Tax_Deductions_List_Checking_Acc.add(temp_obj_withdraw_deduction); // adding temp object in the deduction list
        }
        else if (withdraw_amount <= maxWithdrawalAmountCheckingAcc && withdrawTransactionCount == 0)
        {
            //                               withdraw amount +10 PKR of TAX
            super.makeWithdraw(withdraw_amount+transactionFee, true, this.maxWithdrawalAmountCheckingAcc);

            long tax_deduction_amount = transactionFee; // as the customer don't fall under free transaction category we will charge RS 10 PKR
            Deductions temp_obj_withdraw_deduction=new Deductions("Checking Account","Withdrawal Tax",Get_Current_Date(),tax_deduction_amount,this.getAccountBalance());


            Tax_Deductions_List_Checking_Acc.add(temp_obj_withdraw_deduction); // adding temp object in the deduction
            // list
        } else if (withdraw_amount > maxWithdrawalAmountCheckingAcc && withdrawTransactionCount == 0) {
            System.out.println("Max Withdraw Limit for Checking Account is 5000PKR .... \nYou are Trying To with draw more amount than Bank Defaults ... \n Quiting System ....");
            return;
        } else if (withdraw_amount > maxWithdrawalAmountCheckingAcc && withdrawTransactionCount > 0) {
            System.out.println("Max Withdraw Limit for Checking Account is 5000PKR .... \nYou are Trying To with draw more amount than Bank Defaults ... \n Quiting System ....");
            return;
        }
    }


    //2. Make Deposit Checking Account
    @Override
    public void makeDeposit(long deposit_amount)
    {
        if(depositTransactionCount >0)
        {
            super.makeDeposit(deposit_amount);
            --this.depositTransactionCount;

            long tax_deduction_amount=0L; //as the customer will fall under the free category

            Deductions temp_obj_deposit_deduction=new Deductions("Checking Account","Deposit Tax",Get_Current_Date(),tax_deduction_amount,this.getAccountBalance());
            Tax_Deductions_List_Checking_Acc.add(temp_obj_deposit_deduction);
        }
        else if(depositTransactionCount ==0)
        {
            //                             deposit amount +10 PKR of TAX
            super.makeDeposit(deposit_amount+transactionFee);
            long tax_deduction_amount = transactionFee; // as the customer don't fall under free transaction category We will charge 10PKR per transaction

            Deductions temp_obj_deposit_deduction =new Deductions("Checking Account","Deposit Tax",Get_Current_Date(),tax_deduction_amount,this.getAccountBalance());
            Tax_Deductions_List_Checking_Acc.add(temp_obj_deposit_deduction);
        }
        else
        {
            System.out.println("Error with Transaction .....\n Try Again \nQuiting System");
        }

    }

    //3. Display Deduction()
    @Override
    public void displayDeductions()
    {
        System.out.println("##################################################### CHECKING ACCOUNT ALL DEDUCTIONS LIST ###########################################################################");
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t-->Current Account Deduction List");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        super.getCustomerDetails().print_Customer_Details();        //printing the Customer Information

        System.out.println();
        System.out.println();
        System.out.println("\n---------------------------------------------------------------------------------------------------------------");
        System.out.println("Date\t\t\t\tTime\t\t\t\t\t\tDeduction Type\tDeduction Amount\tRemaining Balance");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");


        for (Deductions temp_obj_deduction :Tax_Deductions_List_Checking_Acc)
        {
            temp_obj_deduction.printDeductionsDetails();
        }

    }

    //5.Transfer Amount
    public Vector<Customer> TransferAmount(long amountToTransfer, String Recipient_Account_Number,Vector<Customer> Account_Holder_List,int Account_Type_Index)
    {

        String Account_Type="";
        if(Account_Type_Index ==0)
        {
            Account_Type="Checking Account";
        }
        else if(Account_Type_Index==1)
        {
            Account_Type="Saving Account";
        }
        else
        {
            System.out.println("Invalid Type of Account Index Passed ..... Check The Last Parameter At Function Call 0 for CHECKING ACCOUNT & 1 for SAVING ACCOUNT");
        }

        int recipientAccountID_Index=-1;
        for(int i=0; i<Account_Holder_List.size();i++)
        {
            if(Recipient_Account_Number.equals(Account_Holder_List.get(i).get_GENERIC_Account_ID()))
            {
                recipientAccountID_Index=i;
//                System.out.println("Account Found @ index"+i);

                //perform transfer
                Customer temp_obj=Account_Holder_List.get(recipientAccountID_Index);

                //for transfer to Saving Account
                if(Account_Type.equals("Saving Account"))
                {
                    if(temp_obj.getSavingAccountStatus())
                    {
                        if(this.getAccountBalance() >amountToTransfer)
                        {
                            Account_Holder_List.remove(recipientAccountID_Index);
                            temp_obj.getSavingAccountObject().makeDeposit(amountToTransfer);
                            this.makeWithdrawal(amountToTransfer);

                            System.out.println("\n=================================================================");
                            System.out.println("-->Transfer From Checking Account To Saving Account Successful ");
                            System.out.println("Your New Balance is : "+this.getAccountBalance());
                            System.out.println("=================================================================");

                            Account_Holder_List.add(recipientAccountID_Index,temp_obj);
                            return Account_Holder_List;
                        }
                        else
                        {
                            System.out.println("\n=================================================================");
                            System.out.println("-->Transfer From Checking Account To Saving Account UnSuccessful Due To Insufficient Funds");
                            System.out.println("Your Current Balance is : "+this.getAccountBalance());
                            System.out.println("Kindly Increase Your Balance To Make A Fund Transfer ThankYou !!!! ");
                            System.out.println("=================================================================");
                        }
                        return Account_Holder_List;
                    }
                    else
                    {
                        System.out.println("Sorry !!! The Transfer Account has No Saving Account Opened Yet");
                        System.out.println("Transfer Not Successful");
                    }
                }

                //for transfer to Checking Account
                if(Account_Type.equals("Checking Account"))
                {
                    if(temp_obj.getCheckingAccountStatus())
                    {
                        if(this.getAccountBalance() >amountToTransfer)
                        {
                            Account_Holder_List.remove(recipientAccountID_Index);
                            temp_obj.getCheckingAccountObject().makeDeposit(amountToTransfer);
                            this.makeWithdrawal(amountToTransfer);

                            System.out.println("\n=================================================================");
                            System.out.println("-->Transfer From Checking Account To Checking Account Successful ");
                            System.out.println("Your New Balance is : "+this.getAccountBalance());
                            System.out.println("=================================================================");

                            Account_Holder_List.add(recipientAccountID_Index,temp_obj);
                            return Account_Holder_List;
                        }
                        else
                        {
                            System.out.println("\n=================================================================");
                            System.out.println("-->Transfer From Checking Account To Saving Account UnSuccessful Due To Insufficient Funds");
                            System.out.println("Your Current Balance is : "+this.getAccountBalance());
                            System.out.println("Kindly Increase Your Balance To Make A Fund Transfer ThankYou !!!! ");
                            System.out.println("=================================================================");
                        }
                        return Account_Holder_List;

                    }
                    else
                    {
                        System.out.println("Sorry !!! The Transfer Account has No Saving Account Opened Yet");
                        System.out.println("Transfer Not Successful");
                    }
                }
            }
        }
        if(recipientAccountID_Index ==-1)
        {
            System.out.println("The Recipient Account ID Not Founded .. Check Account ID\n Quiting System.....");
        }

        //Returning as Null Becuase if there is issue the Null Exception will be called during the process so that we have idea that the changed vector is not return yet
        System.out.println("\n\nThe Issue With Transcation Vector not returned yet");
        return null;
    }

    //------------------------------------------------------------ Helper Function to Get the System Time and Date -----------------------------------------------------------------------------------
    private String Get_Current_Date()
    {
          /*  //GETTING CURRENT DATE AND TIME
            Code to Get the Current System Date And Time
            This will help in getting the exact time at which the transaction was performed in the system
            */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String stringDateTimeNow =now.format(dtf);  //getting the time and date at which we did the withdrawal

        return stringDateTimeNow;
    }

}

class Admin_Bank {
    private String userName;
    private String password;



    //Constructor
    Admin_Bank(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }


    public String getPassword() {
        return this.password;
    }

    public String getUserName()
    {
        return this.userName;
    }
}

public class Main {
    public static String Get_Current_Date()
    {
          /*  //GETTING CURRENT DATE AND TIME
            Code to Get the Current System Date And Time
            This will help in getting the exact time at which the transaction was performed in the system
            */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String stringDateTimeNow =now.format(dtf);  //getting the time and date at which we did the withdrawal

        return stringDateTimeNow;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////// MENU OPTIONS ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void ADMIN_PANEL(Vector<Admin_Bank> Admin_Bank_List, Vector<Customer>Account_Holder_List) {
        boolean checkQuit = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n================================================================================================");

        do {
            System.out.println("\t\t\t\tADMIN PANEL");

            System.out.println("1. Login To Existing Admin Accout");
            System.out.println("2. Create A New Admin Accout");
            System.out.println("3. To Quit");

            System.out.print("Enter Your Choice : ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1: {
                    sc.nextLine();
                    System.out.println("--->Login For Existing Admin");
                    System.out.print("Enter ADMIN User Name : ");
                    String adminUser = sc.nextLine();
                    System.out.print("Enter ADMIN Password : ");
                    String adminPass = sc.nextLine();

                    boolean check_flag = false;
                    int admin_index_number = -1;
                    for (int i = 0; i < Admin_Bank_List.size(); i++) {
                        if (adminUser.equals(Admin_Bank_List.get(i).getUserName())) {
                            admin_index_number = i;
                            check_flag = true;
                        }
                    }

                    if (check_flag == false) {
                        System.out.println("Error : Admin Not Found");
                        System.exit(0);
                    }

                    if (adminPass.equals(Admin_Bank_List.get(admin_index_number).getPassword())) {
                        System.out.println("\n........Admin Verification Success.....");
                        MAIN_MENU(Account_Holder_List);
                    }
                    else
                    {
                        System.out.println("\n\nAdmin Details not Founded \n Try Again.......\n\n");
                    }
                    break;
                }
                case 2: {
                    sc.nextLine();
                    System.out.println("\t\t\tMenu For Adding A New Admin");
                    System.out.print("Enter The Admin User Name : ");
                    String adminUser = sc.nextLine();
                    System.out.print("Enter The Admin Password : ");
                    String adminPass = sc.nextLine();

                    Admin_Bank obj = new Admin_Bank(adminUser, adminPass);
                    Admin_Bank_List.add(obj);
                    break;
                }
                case 3: {
                        checkQuit=false;
                    System.out.println("Thanks For Using The Program");
                }
                System.out.println("\n================================================================================================");
            }
        }while (checkQuit);
    }


    public static Vector<Customer>  Account_Creation_MENU(Vector<Customer> Account_Holder_List)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("===========================================================================================");
        System.out.println("\t\t\t\t ACCOUNT CREATION MENU ");

        System.out.println("Welcome To Account Creation Menu");
        System.out.println("Which Type of Account You Want To Create ?");

        int choice = -1;
        do {
            System.out.println("1.Saving Account");
            System.out.println("2.Checking Account ");
            System.out.println("3 New Checking Account Over Saving Account");
            System.out.println("4 New Saving Account Over Checking Account");

            System.out.print("\nEnter Your Choice: ");
            choice = sc.nextInt();
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 );


        // 1 to create saving account
        //2 to create checking account

        System.out.println();
        System.out.println();
        switch (choice)
        {
            case 1:
            {
                // we know that index 1 is for all saving accounts in the Account Lists
                System.out.println("\n*************************** Saving Account Creation Menu *****************************************");
                sc.nextLine();
                System.out.println("Saving Account Creation Menu\n");

                System.out.print("Please Enter Your Name: ");
                String Temp_Name = sc.nextLine();

                System.out.print("Please Enter Your Address: ");
                String Temp_Address = sc.nextLine();

                System.out.print("Please Enter Your Phone Number: ");
                String Temp_Phone_No = sc.nextLine();

                System.out.print("Please Enter Your Account Number: ");
                String Temp_Acc_Number = sc.nextLine();

                String Temp_Current_DateTime = Get_Current_Date();
                System.out.print("Enter The Account Balance: ");
                long temp_balance = sc.nextLong();

                Customer newAccountHolder=new Customer(Temp_Name,Temp_Address,Temp_Phone_No,Temp_Acc_Number);
                newAccountHolder.setSavingAccount(temp_balance,Get_Current_Date());

                //update saving account with the customer details
//                newAccountHolder.getSavingAccountObject().setCustomerDetails(newAccountHolder.getCustomerDetails());

                newAccountHolder.print_Customer_Details();
                Account_Holder_List.add(newAccountHolder);
                return Account_Holder_List;
            }
            case 2:
            {
                // we know  2 is for all checking accounts in the Account Lists
                System.out.println("\n*************************** Checking Account Creation Menu *****************************************");
                sc.nextLine();
                System.out.println("Checking Account Creation Menu\n");

                System.out.print("Please Enter Your Name: ");
                String Temp_Name = sc.nextLine();

                System.out.print("Please Enter Your Address: ");
                String Temp_Address = sc.nextLine();

                System.out.print("Please Enter Your Phone Number: ");
                String Temp_Phone_No = sc.nextLine();

                System.out.print("Please Enter Your Account Number: ");
                String Temp_Acc_Number = sc.nextLine();

                String Temp_Current_DateTime = Get_Current_Date();
                System.out.print("Enter The Account Balance: ");
                long temp_balance = sc.nextLong();

                Customer newAccountHolder=new Customer(Temp_Name,Temp_Address,Temp_Phone_No,Temp_Acc_Number);
                newAccountHolder.setCheckingAccount(temp_balance,Get_Current_Date());

                //update checking account with the customer details
//                newAccountHolder.getCheckingAccountObject().setCustomerDetails(newAccountHolder.getCustomerDetails());

                newAccountHolder.print_Customer_Details();

                System.out.println("Checking Account Created Successfully");
                Account_Holder_List.add(newAccountHolder);
                return Account_Holder_List;

            }
            case 3:
            {
                sc.nextLine();
                System.out.println("\n*************************** New Checking Account on Existing Saving Creation Menu *****************************************");
                System.out.println("Please Enter Your Current Account Number");
                String Temp_Acc_Number = sc.nextLine();

                boolean check_Account_Holder=true;
                int accountHolder_index=-1;

                for (int i=0;i<Account_Holder_List.size();i++)
                {
                    if(Temp_Acc_Number.equals(Account_Holder_List.get(i).get_GENERIC_Account_ID())) {
                        check_Account_Holder = false;

                        accountHolder_index = i;
                        Customer temp_Existing_Customer = Account_Holder_List.get(i);
                        if (temp_Existing_Customer.getCheckingAccountStatus() == false)
                        {

                            Account_Holder_List.remove(i);

                            System.out.print("Enter The Account Balance: ");
                            long temp_balance = sc.nextLong();
                            temp_Existing_Customer.setCheckingAccount(temp_balance, Get_Current_Date());

                            //updating customer details
                            temp_Existing_Customer.getCheckingAccountObject().setCustomerDetails(temp_Existing_Customer.getCustomerDetails());
                            temp_Existing_Customer.getSavingAccountObject().setCustomerDetails(temp_Existing_Customer.getCustomerDetails());

                            Account_Holder_List.add(accountHolder_index, temp_Existing_Customer);

                            System.out.println("Checking Account with Existing Saving Account Created Successfully");
                            return Account_Holder_List;
                       }
                        else
                        {
                            System.out.println("Checking Account on This Account Already Exist");
                            return null;
                        }
                    }
                }
            }
            case 4:
            {
                sc.nextLine();
                System.out.println("\n*************************** New Saving Account on Existing Checking Creation Menu *****************************************");
                System.out.println("Please Enter Your Current Account Number");
                String Temp_Acc_Number = sc.nextLine();

                boolean check_Account_Holder=true;
                int accountHolder_index=-1;

                for (int i=0;i<Account_Holder_List.size();i++)
                {
                    if(Temp_Acc_Number.equals(Account_Holder_List.get(i).get_GENERIC_Account_ID()))
                    {
                        check_Account_Holder=false;

                        accountHolder_index=i;
                        Customer temp_Existing_Customer= Account_Holder_List.get(i);

                        if(temp_Existing_Customer.getSavingAccountStatus()==false)
                        {
                            Account_Holder_List.remove(i);

                            System.out.print("Enter The Account Balance: ");
                            long temp_balance = sc.nextLong();
                            temp_Existing_Customer.setSavingAccount(temp_balance,Get_Current_Date());

                            //updating the customer details
                            temp_Existing_Customer.getSavingAccountObject().setCustomerDetails(temp_Existing_Customer.getCustomerDetails());
                            temp_Existing_Customer.getCheckingAccountObject().setCustomerDetails(temp_Existing_Customer.getCustomerDetails());


                            Account_Holder_List.add(accountHolder_index,temp_Existing_Customer);
                            System.out.println("Saving Account with Existing Checking Account Created Successfully");
                            return  Account_Holder_List;
                        }
                        else
                        {
                            System.out.println("Saving Account on This Account Already Exist");
                            return null;
                        }

                    }
                }
            }
        }


        return null;

    }

   public static Vector<Customer> Close_An_Account(Vector<Customer>Account_Holder_List)
   {
       Scanner sc=new Scanner(System.in);

       System.out.println("==>Account Delete Menu");
       System.out.print("Please Enter The Account Number : ");
       String Account_ID = sc.nextLine();

       int accountHolder_index=-1;
        for (int i=0;i<Account_Holder_List.size();i++)
        {
            if(Account_ID.equals(Account_Holder_List.get(i).get_GENERIC_Account_ID()))
            {
                accountHolder_index=i;
                Customer temp_Existing_Customer= Account_Holder_List.get(i);

                if(temp_Existing_Customer.getCheckingAccountStatus() ==true && temp_Existing_Customer.getSavingAccountStatus()==true)
                {
                    System.out.println("********** Checking And Saving Account Deletion");
                    System.out.println("The Account Has Both Checking and Saving Accounts");

                    System.out.println("Select Option According To Delete An Account");
                    System.out.println("1. Delete Checking AccountOnly");
                    System.out.println("2. Delete Saving Account Only");
                    System.out.println("3. Delete Both Checking Account and Saving Account");

                    System.out.print("Enter Your Choice : ");

                    int choice_opt= sc.nextInt();
                    switch (choice_opt)
                    {
                        case 1:
                        {
                            temp_Existing_Customer.deleteCheckingAccout();
                            Account_Holder_List.remove(accountHolder_index);
                            Account_Holder_List.add(accountHolder_index,temp_Existing_Customer);
                            System.out.println("Accout "+Account_Holder_List.get(accountHolder_index).get_GENERIC_Account_ID()+" Checking Accout Deleted Succesffuly");
                            return Account_Holder_List;
                        }
                        case 2:
                        {
                            temp_Existing_Customer.deleteSavingAccounts();
                            Account_Holder_List.remove(accountHolder_index);
                            Account_Holder_List.add(accountHolder_index,temp_Existing_Customer);
                            System.out.println("Accout "+Account_Holder_List.get(accountHolder_index).get_GENERIC_Account_ID()+" Saving Accout Deleted Successfully");
                            return Account_Holder_List;
                        }
                        case 3:
                        {
                            String temp=Account_Holder_List.get(accountHolder_index).get_GENERIC_Account_ID();
                            Account_Holder_List.remove(accountHolder_index);
                            System.out.println("Accout "+temp+" Both Checking and Saving Accounts are Deleted Successfully");
                            return Account_Holder_List;
                        }
                    }
                }
                else if(temp_Existing_Customer.getCheckingAccountStatus() ==true && temp_Existing_Customer.getSavingAccountStatus()==false)
                {
                    String temp=Account_Holder_List.get(accountHolder_index).get_GENERIC_Account_ID();
                     Account_Holder_List.remove(accountHolder_index);
                    System.out.println("Accout "+temp+"The Checking Accout has been delete");
                    return Account_Holder_List;
                }
                else if(temp_Existing_Customer.getCheckingAccountStatus() ==false && temp_Existing_Customer.getSavingAccountStatus()==true)
                {
                    String temp=Account_Holder_List.get(accountHolder_index).get_GENERIC_Account_ID();
                    Account_Holder_List.remove(accountHolder_index);
                    System.out.println("Accout "+temp+"The Saving Accout has been delete");
                    return Account_Holder_List;
                }

            }
        }


       return null;
   }


   public  static Vector<Customer>Specify_Interest_Rate(Vector<Customer>Account_Holder_List)
   {
       Scanner sc = new Scanner(System.in);
       System.out.println("=============================================================================================");
       System.out.println("\t\t\tSpecify The Interest Rate Menu");

       System.out.print("Enter The Interest Rate : ");
       double interestRate= sc.nextDouble();


       for (Customer Acc_Holders :Account_Holder_List)
       {
           Acc_Holders.getSavingAccountObject().setInterestRate(interestRate);
       }
       System.out.println("=============================================================================================");
        return Account_Holder_List;
   }

   public static Vector<Customer> Perform_Accout_Operations(Vector<Customer>Account_Holder_List)
   {
       Scanner sc = new Scanner(System.in);
       System.out.println("===========================================================================================");
       System.out.println("\t\t\t Accout Operations Menu");
       System.out.print("Pleas Enter The Accout Number : ");

       int accountHolderIndex=-1;
       String accountNumber= sc.nextLine();
       //finding account Index
       for(int i=0; i<Account_Holder_List.size();i++)
       {
           if(accountNumber.equals(Account_Holder_List.get(i).get_GENERIC_Account_ID()))
           {
               accountHolderIndex=i;
           }
       }
       if (accountHolderIndex==-1)
       {
           System.out.println("Error Founding the Accout");
           return null;
       }

       System.out.println("Inorder To Continue You Must Have to Decide on What Accout Type You Want to Do A Accout Operation");
       System.out.println("1. Checking Account");
       System.out.println("2. Saving Account");

       System.out.print("Enter Your Choice: ");
       int choice = sc.nextInt();

       switch (choice)
       {
           case 1:      //checking account
           {
               if(Account_Holder_List.get(accountHolderIndex).getCheckingAccountStatus()==true)
               {
                   System.out.println("1. Make Deposit");
                   System.out.println("2. Make Withdraw");
                   System.out.println("3. Check Balance");
                   System.out.println("4. Print Statement");
                   System.out.println("5. Transfer Amount");
                   System.out.println("6. Display All Deductions");

                   System.out.print("Enter Your Choice : ");
                   int choice_opt = sc.nextInt();

                   switch (choice_opt)
                   {
                       case 1:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Deposit Menu");
                           System.out.print("--> Please Enter The Deposit Amount : ");
                           long deposit_amount = sc.nextLong();
                           Account_Holder_List.get(accountHolderIndex).getCheckingAccountObject().makeDeposit(deposit_amount);
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;
                       }
                       case 2:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Withdraw Menu");
                           System.out.print("--> Please Enter The Withdraw Amount : ");
                           long withdraw_amount = sc.nextLong();
                           Account_Holder_List.get(accountHolderIndex).getCheckingAccountObject().makeWithdraw(withdraw_amount,true);
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       case 3:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Check Balance Menu");

                           Account_Holder_List.get(accountHolderIndex).getCheckingAccountObject().checkBalance();
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       case 4:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Print Bank Statement Menu");

                           Account_Holder_List.get(accountHolderIndex).getCheckingAccountObject().printStatement();
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       case 5:
                       {

                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Amount Transfer Menu");

                           System.out.println("Inorder To Continue You Must Have to Decide on What Accout Type You Want to Do A Accout Operation");
                           System.out.println("1. Checking Account");
                           System.out.println("2. Saving Account");

                           sc.nextLine();
                           int accountTransferChoice=-1;
                           do{

                           System.out.print("Enter Your Choice : ");
                           accountTransferChoice= sc.nextInt();
                           }while (accountTransferChoice !=1 && accountTransferChoice !=2);



                           sc.nextLine();
                           System.out.print("Enter Recipient Accout : ");
                           String  recipientAccoutNUmber= sc.nextLine();

                           System.out.print("--> Please Enter The Transfer Amount : ");
                           long transfer_Amount = sc.nextLong();

                           Account_Holder_List.get(accountHolderIndex).getCheckingAccountObject().TransferAmount(transfer_Amount,recipientAccoutNUmber,Account_Holder_List,accountTransferChoice-1);
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       case 6:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Deduction Menu");

                           Account_Holder_List.get(accountHolderIndex).getCheckingAccountObject().displayDeductions();
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       default:
                       {
                           System.out.println("Invalid Option Selected");
                           return null;
                       }
                   }
               }
               else
               {
                   System.out.println("Error : There is no Checking Account Opened With This Account ID : "+Account_Holder_List.get(accountHolderIndex).get_GENERIC_Account_ID());
                   System.out.println("Quiting the System ....");
               }
           }
           case 2:  //saving accout
           {
               if(Account_Holder_List.get(accountHolderIndex).getSavingAccountStatus()==true)
               {
                   System.out.println("1. Make Deposit");
                   System.out.println("2. Make Withdraw");
                   System.out.println("3. Check Balance");
                   System.out.println("4. Print Statement");
                   System.out.println("5. Transfer Amount");
                   System.out.println("6. Display All Deductions");
                   System.out.println("7. Calculate Zakat ");
                   System.out.println("8. Calculate Interest");
                   System.out.println("9. Quit Menu");


                   System.out.print("Enter Your Choice : ");
                   int choice_opt = sc.nextInt();

                   switch (choice_opt)
                   {
                       case 1:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Deposit Menu");
                           System.out.print("--> Please Enter The Deposit Amount : ");
                           long deposit_amount = sc.nextLong();
                           Account_Holder_List.get(accountHolderIndex).getSavingAccountObject().makeDeposit(deposit_amount);
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;
                       }
                       case 2:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Withdraw Menu");
                           System.out.print("--> Please Enter The Withdraw Amount : ");
                           long withdraw_amount = sc.nextLong();
                           Account_Holder_List.get(accountHolderIndex).getSavingAccountObject().makeWithdrawal(withdraw_amount);
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       case 3:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Check Balance Menu");

                           Account_Holder_List.get(accountHolderIndex).getSavingAccountObject().checkBalance();
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       case 4:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Print Bank Statement Menu");

                           Account_Holder_List.get(accountHolderIndex).getSavingAccountObject().printStatement();
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       case 5:
                       {
                            sc.nextLine();
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Amount Transfer Menu");

                           System.out.println("Inorder To Continue You Must Have to Decide on What Accout Type You Want to Do A Accout Operation");
                           System.out.println("1. Checking Account");
                           System.out.println("2. Saving Account");

                           System.out.print("Enter Your Choice : ");
                           int accountTransferChoice= sc.nextInt();

                           sc.nextLine();

                           System.out.print("Enter Recipient Accout : ");
                           String  recipientAccoutNUmber= sc.nextLine();

                           System.out.print("--> Please Enter The Transfer Amount : ");
                           long transfer_Amount = sc.nextLong();

                           Account_Holder_List.get(accountHolderIndex).getSavingAccountObject().TransferAmount(transfer_Amount,recipientAccoutNUmber,Account_Holder_List,accountTransferChoice-1);
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       case 6:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Deduction Menu");

                           Account_Holder_List.get(accountHolderIndex).getSavingAccountObject().displayDeductions();
                           System.out.println("===========================================================================================");
                           return Account_Holder_List;

                       }
                       case 7:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Deduction Menu");

                           Account_Holder_List.get(accountHolderIndex).getSavingAccountObject().calculateZakat();

                           System.out.println("===========================================================================================");
                           return Account_Holder_List;
                       }
                       case 8:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("\t\t\t Calculate Interest Menu");

                           Account_Holder_List.get(accountHolderIndex).getSavingAccountObject().calculateInterest();

                           System.out.println("===========================================================================================");
                           return Account_Holder_List;
                       }
                       case 9:
                       {
                           System.out.println("===========================================================================================");
                           System.out.println("Thanks For Using ... \nQuiting");
                           return Account_Holder_List;
                       }
                       default:
                       {
                           System.out.println("Invalid Option Selected");
                           return null;
                       }
                   }
               }
               else
               {
                   System.out.println("Error : There is no Saving Account Opened With This Account ID : "+Account_Holder_List.get(accountHolderIndex).get_GENERIC_Account_ID());
                   System.out.println("Quiting the System ....");
               }
           }
           default:
           {
               System.out.println("Invalid Option Selected");
               return null;
           }
       }
   }

   public static void Display_All_Accounts(Vector<Customer> Account_Holder_List)
   {
       System.out.println("===========================================================================================");
       System.out.println("\t\t\tBANK DETAILS");
       System.out.println("Bank Name: ABL LTD");
       System.out.println("Bank Owner Name: ABC ABC");
       System.out.println("===========================================================================================");


       System.out.println("===========================================================================================");
       System.out.println("\t\t\tAccount Details");
       for (Customer accountHolder : Account_Holder_List)
       {
           accountHolder.print_Customer_Details();

           System.out.println();
           System.out.println("******************************************************************************************");
       }
   }


   public static void Display_All_Deductions(Vector<Customer>Account_Holder_List)
   {
       System.out.println("=========================================== Display All The Deductions =================================");

       System.out.println("=====> Displaying All The Check Accounts");
       for (Customer accountHolder : Account_Holder_List)
       {
           if(accountHolder.getCheckingAccountStatus())
           {
               accountHolder.getCheckingAccountObject().displayDeductions();

               System.out.println();
               System.out.println("******************************************************************************************");
           }
       }

       System.out.println();

       System.out.println("=====> Displaying All The Saving Accounts");
       for (Customer accountHolder : Account_Holder_List)
       {
          if(accountHolder.getSavingAccountStatus())
          {
              accountHolder.getSavingAccountObject().displayDeductions();

              System.out.println();
              System.out.println("******************************************************************************************");
          }
       }



   }

    public static void MAIN_MENU(Vector<Customer> Account_Holder_List) {
        boolean quit_check = true;
        do {

            Scanner sc = new Scanner(System.in);
            System.out.println("===========================================================================================");
            System.out.println("\t\t\t\t MAIN MENU ");
            System.out.println("1. Open a New Account");
            System.out.println("2. Close a Account");
            System.out.println("3. Perform Account Operations");
            System.out.println("4. Set The Interest Rate to All Saving Accounts");
            System.out.println("5. Display All The Account Details Including Bank Owner Details");
            System.out.println("6. Display All Accounts Deductions along with Account Details");
            System.out.println("7. Quit The Program");


            System.out.print("\nEnter Your Choice : ");
            int choice = sc.nextInt();

            System.out.println("===========================================================================================\n");
            switch (choice) {
                case 1: {
                    Account_Holder_List = Account_Creation_MENU(Account_Holder_List);
                    if (Account_Holder_List == null) {
                        System.out.println("===========================================================================================\n");
                        System.out.println("Error in Adding A New Account Holder");
                        System.exit(0);
                        System.out.println("===========================================================================================\n");
                    } else {
                        System.out.println("===========================================================================================");
                        System.out.println("Account Added Successfully");
                        System.out.println("===========================================================================================\n");
                    }

                    break;
                }

                case 2: {
                    Account_Holder_List = Close_An_Account(Account_Holder_List);
                    if (Account_Holder_List == null) {
                        System.out.println("===========================================================================================\n");
                        System.out.println("Error in Closing A New Account Holder");
                        System.exit(0);
                        System.out.println("===========================================================================================\n");
                    } else {
                        System.out.println("===========================================================================================");
                        System.out.println("Account Closed Successfully");
                        System.out.println("===========================================================================================\n");
                    }
                    break;
                }
                case 3: {
                    Account_Holder_List = Perform_Accout_Operations(Account_Holder_List);
                    if (Account_Holder_List == null) {
                        System.out.println("===========================================================================================\n");
                        System.out.println("Error in Performing A Operation on Account ");
                        System.exit(0);
                        System.out.println("===========================================================================================\n");
                    } else {
                        System.out.println("===========================================================================================");
                        System.out.println("Operation Performed Successfully");
                        System.out.println("===========================================================================================\n");
                    }
                    break;
                }
                case 4: {
                    Account_Holder_List = Specify_Interest_Rate(Account_Holder_List);
                    if (Account_Holder_List == null) {
                        System.out.println("===========================================================================================\n");
                        System.out.println("Interest Rate Set:  Unsuccessful ");
                        System.exit(0);
                        System.out.println("===========================================================================================\n");
                    } else {
                        System.out.println("===========================================================================================");
                        System.out.println("Interest Rate Set: Successful");
                        System.out.println("===========================================================================================\n");
                    }
                    break;
                }
                case 5: {
                    Display_All_Accounts(Account_Holder_List);
                    break;
                }
                case 6: {
                    Display_All_Deductions(Account_Holder_List);
                    break;
                }
                case 7: {
                    System.out.println("Thank You For Using The Program 😀😀");
                    quit_check = false;
                    break;
                }
                default: {
                    System.out.println("Invalid Option Selected");
                }
            }
        }while (quit_check) ;
    }






    public static void RUN(Vector<Admin_Bank> Admin_List,Vector<Customer>Account_Holder_list)
    {
        System.out.println("====================================================================");
        System.out.println("Starting The Accout Management System");
        System.out.println("Default Admin User Name :admin");
        System.out.println("Default Admin Password  :admin");

        ADMIN_PANEL(Admin_List,Account_Holder_list);

    }


    public static void main(String[] args) {
	// write your code here

        //// BANK INFORMATION




        //Admin List
        Vector<Admin_Bank> Admin_List=new Vector<Admin_Bank>();
        Admin_Bank a1=new Admin_Bank("admin","admin");
        Admin_List.add(a1);

        Vector<Customer> Account_Holder_list = new Vector<Customer>();
        Customer c1=new Customer("Imran Khan","Rawalpindi","+9203351209","123");
        c1.setSavingAccount(1500L,Get_Current_Date());
        c1.setCheckingAccount(2000L,Get_Current_Date());
        c1.getCheckingAccountObject().setCustomerDetails(c1.getCustomerDetails());
        c1.getSavingAccountObject().setCustomerDetails(c1.getCustomerDetails());



        Customer c2=new Customer("Abid Hussain","Islamabad","+9205123456","1234");
        c2.setCheckingAccount(500L,Get_Current_Date());
        c2.setSavingAccount(1500L,Get_Current_Date());


        //update Customer Details To the Respective Bank Account Type
        c2.getCheckingAccountObject().setCustomerDetails(c2.getCustomerDetails());
        c2.getSavingAccountObject().setCustomerDetails(c2.getCustomerDetails());

        Account_Holder_list.add(c1);
        Account_Holder_list.add(c2);






        //the run function will start the Account Management System
        RUN(Admin_List,Account_Holder_list);




    }
}
