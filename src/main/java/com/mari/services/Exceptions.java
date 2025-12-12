package com.mari.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions {
    Scanner sc = new Scanner(System.in);

    public String answer (String question){
        String answer = "";

        while (answer.isEmpty()){
            System.out.println(question);
            answer = sc.nextLine().trim();
        }
        return answer;
    }

    public Integer number (String question){
        Integer number = null;

        while (number == null) {
            System.out.println(question);
            try {
                number = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid year. Please enter an integer.");
                sc.nextLine();
            }
        }
        return number;
    }
}
