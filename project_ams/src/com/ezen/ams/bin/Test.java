package com.ezen.ams.bin;

import java.text.DecimalFormat;

public class Test {
    public static void print(){
        String accountNum = "1111-2222";
        String accountOwner = "바나나";
        String password = "****";
        long balance = 10000000L;

        System.out.printf("%1$10s\t%2$4s\t%3$10s\t%4$10s", accountNum, accountOwner, password, balance);

    }

    public String comma(long balance){
        DecimalFormat comma = new DecimalFormat("###,###");
        String com = comma.format(balance);
        return com;
    }

    public static void main(String[] args) {


        System.out.println("------------------------------------------");
        System.out.println("계좌번호\t예금주명\t비밀번호\t잔액");
        System.out.println("------------------------------------------");
        print();



    }


}
