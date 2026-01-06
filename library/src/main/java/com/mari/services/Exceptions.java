package com.mari.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions {
    static Scanner sc = new Scanner(System.in);

    public static String answer (String question){
        String answer = "";

        while (answer.isEmpty()){
            System.out.print(question);
            answer = sc.nextLine().trim();
        }
        return answer;
    }

    public static Integer number (String question){
        Integer number = null;

        while (number == null) {
            System.out.print(question);
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
