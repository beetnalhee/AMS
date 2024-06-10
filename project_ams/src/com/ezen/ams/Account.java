package com.ezen.ams;

/**
 * 일상생활의 은행계좌(객체)를 표현한 클래스(설계도)
 * 객체 추상화
 */
public class Account {
    // 정적 필드(static 변수)
    public static String bankName = "EZEN Bank";//static변수는 선언과 동시에 초기화
    // 정적 초기화 블록
    // static { }
    // 상수(관례상 올대문자로 표기)
    public static final int MIN_BALANCE = 0;
    public static final int MAX_BALANCE = 1000000;

    // 필드(인스턴스 변수)
    // private 은 이 클래스 내부에서만 씀
    private String accountNum;   // 계좌번호
    private String accountOwner; // 예금주명
    private int passwd;          // 비밀번호
    private long balance;        // 잔액 롱타입 접미사L 붙이기
    private long loan;


    // 생성자(Constructor) 오버로딩(Overloading)


    public Account() {
        //super();
    }


    public Account(String accountNum, String accountOwner)
    //super();
    {
        this(accountNum, accountOwner, 0, 0l, 0l);
    }

    public Account(String accountNum, String accountOwner, int passwd, long balance)
    //super();

    {
        this.accountNum = accountNum;
        this.accountOwner = accountOwner;
        this.passwd = passwd;
        this.balance = balance;

    }  //<<생략 가능?>>

    public Account(String accountNum, String accountOwner, int password, long balance, long loan) {

        this.accountNum = accountNum;
        this.accountOwner = accountOwner;
        this.passwd = passwd;
        this.balance = balance;
        this.loan = loan;
    } //++minus


    // 계좌정보, 개인정보 같은 것들은
    // 숨겨진 필드의 값을 변경하거나, 또는 읽어 갈 수 있도록
    // setter 혹은 getter 메소드를 정하여야 한다.
    // 이것을'캡슐화' 라고 함

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public void setPasswd(int passwd) {
        this.passwd = passwd;
    }

    public void setBalance(long balance) {
        //입력 된 값 검증(데이터 유효성 검증)
    //0 <= balance <= 1000000

//        // balance >= 0 && balance <= 1000000 대신에 상수로 표기하는게 굿
//        if(balance >= MIN_BALANCE && balance <= MAX_BALANCE){
//            this.balance = balance;
//        }

        if (MIN_BALANCE >= balance) {
            return;
        } else if (MAX_BALANCE <= balance) {
            return;
        } else {
            this.balance = balance;
            System.out.println(balance);
        }

    }

    public String getAccountNum() {
        return accountNum;
    }

    public long getLoan() {
        return loan;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setLoan(long loan) {
        this.loan = loan;
    }

    public int getPasswd() {
        return passwd;
    }


    // 인스턴스 메소드(객체의 고유 기능)

    // 입금 기능 정의
    public long deposit(long money){
        // balance = balance + money;
        balance += money;
        return balance;
    }
    // 출금 기능 정의
    public long withdraw(long money){
            balance -= money;
            return balance;
    }
    // 잔액 조회 기능 정의
    public long getBalance(){
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNum='" + accountNum + '\'' +
                ", accountOwner='" + accountOwner + '\'' +
                ", passwd=" + passwd +
                ", balance=" + balance +
                ", loan=" + loan +
                '}';
    }

    // 비밀번호 확인 기능 정의 //if 문 쓰지않고 바로 리턴
    public boolean checkPasswd(int passwd){
        return this.passwd == passwd;
    }

    // 계좌가 가지고 있는 모든 속성(필드) 출력기능
    public void printAll(){
        System.out.println(accountNum + "\t" + accountOwner + "\t" + "****" + "\t" + balance + loan);
    }

    // 모든 계좌 객체의 공통 기능 (static method)
    public static void printBankName(){
        System.out.println("은행명 : " + bankName);
    }

}
