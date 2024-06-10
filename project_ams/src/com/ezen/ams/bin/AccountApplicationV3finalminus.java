package com.ezen.ams.bin;

import com.ezen.ams.Account;
import com.ezen.ams.AccountService;
import com.ezen.ams.MinusAccount;

import java.util.Scanner;

public class AccountApplicationV3finalminus {

    private static Scanner scanner = new Scanner(System.in);
    private static AccountService accountService = new AccountService();


    // 메뉴 출력
    public static void printMenu() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println(" 1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.검색 | 6. 삭제 | 7. 종료");
        System.out.println("----------------------------------------------------------------------");
    }


    // 입출금계좌 or 마이너스계좌 등록
    public static void createAccount() {
            String accountNum = null;
            String accountOwner = null;
            int password = 0;
            long balance = 0L;
            long loan = 0L;

            System.out.println("개설하고자 하는 계좌 유형을 선택해주세요");
            System.out.println("----------------------------");
            System.out.println("1.입출금계좌 2.마이너스계좌");
            System.out.println("----------------------------");
            System.out.print("선택 >");
            int whichAccount = scanner.nextInt();
          //  whichAccount = 0;

            switch (whichAccount) {
                // 신규입출금
                case 1:
                    System.out.println("입출금계좌 개설을 위한 정보를 입력해주세요");


                    System.out.print("계좌번호 : ");
                    accountNum = scanner.next();
                    System.out.print("예금주명 : ");
                    accountOwner = scanner.next();
                    System.out.print("비밀번호 : ");
                    password = scanner.nextInt();
                    System.out.print("초기입금액 : ");
                    balance = scanner.nextLong();
                    // 신규계좌 생성
                    Account newAccount = new Account(accountNum, accountOwner, password, balance, loan);
                    accountService.addAccount(newAccount);
                    System.out.println("※ 신규 입출금 계좌(" + newAccount.getAccountNum() + ")를 등록 하였습니다..");

                    return;

                case 2:
                    System.out.println("마이너스계좌 개설을 위한 정보를 입력해주세요");
                    System.out.print("계좌번호 : ");
                    accountNum =scanner.next();
                    System.out.print("예금주명 : ");
                    accountOwner =scanner.next();
                    System.out.print("비밀번호 : ");
                    password =scanner.nextInt();
                    System.out.print("초기입금액 : ");
                    balance =scanner.nextLong();
                    System.out.print("대출금 : ");
                    loan =scanner.nextLong();

                    // 마이너스 신규계좌 생성
                    MinusAccount newMinusAccount = new MinusAccount (accountNum, accountOwner, password, balance, loan);
                    accountService.addAccount(newMinusAccount);
                    System.out.println("※ 신규 마이너스계좌("+newMinusAccount.getAccountNum()+")를 등록 하였습니다..");



            } return;
        }




        // 계좌 목록 출력
        public static void printAccounts () {
            Account[] accounts = accountService.getAccounts(); //어카운트서비스한테 계좌 받기
            printHeader();
            for (int i = 0; i < accountService.getCount(); i++) {
                printRow(accounts[i]);
                System.out.println();
            }
        }
        // 계좌 헤더 출력
        private static void printHeader () {
            System.out.println("------------------------------------------------------------------");
            System.out.println(" 계좌종류 \t 계좌번호 \t 예금주명\t비밀번호\t 잔액\t 대출금");
            System.out.println("------------------------------------------------------------------");
        }

        // 계좌 데이터 출력
        private static void printRow (Account account){
            String kindAccount;
            long loan = 0L;
            if(!(account instanceof MinusAccount)){ //T
                kindAccount = "입출금";
            } else { //F
                kindAccount = "마이너스";
            }
            System.out.printf("%1$-5s\t%2$-5s\t%3$5s\t%4$5s\t%5$10s\t%6$5s",
                    kindAccount,
                    account.getAccountNum(),
                    account.getAccountOwner(),
                    "****",
                    account.getBalance(),
                    account.getLoan());
        }

        // 입금
        public static void depositAccount () {
            System.out.print("계좌번호 : ");
            String inputAccountNum = scanner.next();
            System.out.print("예금액 : ");
            long inputMoney = scanner.nextLong();
//            long restMoney = accountService.depositAccount(inputAccountNum, inputMoney);
            accountService.depositAccount(inputAccountNum, inputMoney);
            long balance = accountService.findAccount(inputAccountNum).getBalance();

            if (balance != -1) {
                System.out.println("※ 정상 입금 처리되었습니다.");
                System.out.println("- 입금 후 잔액 : " + balance);
            } else {
                System.out.println("※ 입금하고자 하는 계좌가 존재하지 않습니다..");
            }
        }

        // 출금
        public static void withdrawAccount () {
            System.out.print("계좌번호 : ");
            String outputAccountNum = scanner.next();
            System.out.print("출금액 : ");
            long outputMoney = scanner.nextLong();
//            long restMoney2 = accountService.withdrawAccount(outputAccountNum, outputMoney);
            accountService.withdrawAccount(outputAccountNum, outputMoney);
            long balance = accountService.findAccount(outputAccountNum).getBalance();
            if (balance != -1) {
                System.out.println("※ 정상 출금 처리되었습니다.");
                System.out.println("※ 출금 후 잔액 : " + balance);
            } else {
                System.out.println("※ 출금하고자 하는 계좌가 존재하지 않습니다..");
            }
        }

        // 검색 계좌번호로
        public static void findByAccountNum () {
            System.out.print("찾으시는 계좌번호 : ");
            String accountNum = scanner.next();
            Account findAccount = accountService.findAccount(accountNum);
            if (findAccount != null) {
                printHeader();
                printRow(findAccount);

            } else {
                System.out.println("존재하지 않는 계좌번호입니다..");
            }

        }

        // 삭제
        public static void removeByAccountNum () {
            System.out.print("찾으시는 계좌번호 : ");
            String accountNum = scanner.next();
            boolean isDelete = accountService.removeAccount(accountNum);

            if (isDelete) {
                System.out.println("▷ 삭제 되었습니다..");

            } else {
                System.out.println("존재하지 않는 계좌번호입니다..");
            }

        }

        public static void main (String[]args){
            System.out.println("****************************************");
            System.out.println("*** " + Account.bankName + "은행 계좌 관리 프로그램 ***");
            System.out.println("****************************************");


            boolean running = true;

            // 테스트를 위한 샘플(더미) 데이터 입력
//            accountService.addAccount(new Account("1111-2222", "바나나", 1111, 10000));
//            accountService.addAccount(new Account("1111-3333", "오렌지", 1111, 10000));
//            accountService.addAccount(new Account("1111-4444", "한라봉", 1111, 10000));
//            accountService.addAccount(new MinusAccount("9999-1111", "마라탕", 1111, 10000,200000));

            while (running) {
                printMenu();
                System.out.print("선택 > ");
                int menuNum = scanner.nextInt();
                switch (menuNum) {

                    case 1: // 계좌생성
                        createAccount();
                        break;
                    case 2: // 전체계좌 목록 조회
                        printAccounts();
                        break;
                    case 3: // 예금
                        depositAccount();
                        break;
                    case 4: // 출금
                        withdrawAccount();
                        break;
                    case 5: // 계좌번호로 검색
                        findByAccountNum();
                        break;
                    case 6: // 삭제
                        removeByAccountNum();
                        break;
                    case 7: // 종료
                        System.out.println("프로그램을 종료합니다..");
                        //return;
                        running = false;
                        break;
                }
            }


        }
    }

