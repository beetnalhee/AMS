package com.ezen.ams;

public class MinusAccount extends Account{

    public MinusAccount(){
    }

    public MinusAccount(String accountNum, String accountOwner, int passwd, long balance, long loan) {
        super(accountNum, accountOwner, passwd, balance, loan);
    }



    @Override
    public String toString() {
        return "MinusAccount{" +
                "loan =" + getLoan() +
                "} " + super.toString(); // ?괄호
    }

    // 재정의가 필요한 메소드
    @Override
    public long getBalance() {
        return super.getBalance() - getLoan();
    }

    @Override
    public void setBalance(long balance) {
    }

    @Override
    public void printAll() {
        super.printAll();
        String allInfo = getAccountNum() + "\t" + getAccountOwner() + "\t" +"****"+ "\t" +getBalance()+ "\t" +getLoan();
        System.out.println(allInfo);
    }
}
