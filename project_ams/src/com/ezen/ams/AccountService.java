package com.ezen.ams;
/**
 * 계좌 목록 관리
 * class 를 만든다 (main 에서 재사용하기 위해)
 */

public class AccountService {

    private Account[] accounts; // 배열도 데이터 타입이다. 리턴도 이걸로 받음
    private int count;

    // alt + enter + 생성자,  setter, getter 설정 더 쉽게 가능
    public AccountService() {
        accounts = new Account[100]; // null 보다 이렇게 해두는게 좋음
    }

    public AccountService(int capacity) {
        accounts = new Account[capacity];
    }

    public Account[] getAccounts(){
        return accounts;
    }
    //
//    public void setAccounts(Account[] accounts){
//        this.accounts = accounts;
//    }
// setter 변수는 논리적으로 보고 없애기도 -  실행파일에서, setter 에서 null 값으로 바꿔버리면..
    // 대참사가 일어나기 때문이다. 못 바꾸게 하기위해 삭제하는 것

    public int getCount(){
        return count; //length 말고
    }

    // 계좌 생성
    public void addAccount(Account account){
        accounts[count++] = account;
    }
    // 계좌 목록 출력
    public void printAccounts(){
        System.out.println("------------------------------------------");
        System.out.println("계좌번호\t예금주명\t비밀번호\t잔액");
        System.out.println("------------------------------------------");
        for (int i = 0; i <count; i++) {
            String header = accounts[i].getAccountNum()+ "\t" +
                            accounts[i].getAccountOwner()+ "\t" +
                            "****"+ "\t" +
                            accounts[i].getBalance();
            System.out.println(header);

        }
    }
    // 입금
    public long depositAccount(String accountNum, long money){
        long balance = 0;
        boolean find = false;
        Account findAccount = findAccount (accountNum);
        if(findAccount != null){
           balance = findAccount.deposit(money);
           find = true;

        }
        if(!find){
            balance = -1;
        }
        return balance;
    }

    // 출금
    public long withdrawAccount(String accountNum, long money){
        long balance = 0;
        boolean find = false;
        Account findAccount = findAccount (accountNum);
        if(findAccount != null){
            balance = findAccount.withdraw(money);
            find = true;

        }
        if(!find){
            balance = -1;
        }
        return balance;
    }

    // 계좌 검색 (메소드)
    public Account findAccount(String accountNum){
        for (int i = 0; i < count; i++) {
            Account account = accounts[i];
            if(account.getAccountNum().equals(accountNum)) {
                return account;
            }
        }
        return null;

    }

    // 계좌 삭제 메소드
    public boolean removeAccount(String accountNum){
        for (int i = 0; i < count; i++) {
            // 삭제하고자 하는 객체일 경우
            if(accounts[i].getAccountNum().equals(accountNum)){
                for (int j = i; j < count-1; j++) {
                    accounts[j] = accounts[j+1];
                }
                count--;
                return true;
            }
        }
        return false;
    }

}
