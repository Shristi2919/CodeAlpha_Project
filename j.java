package com.shristi.internship;

import java.util.Scanner;

public class j {
    public static void main(String[] args) {
        Scanner input1 = new Scanner(System.in);
        System.out.println("enter the total number of students");
        int number_of_student = input1.nextInt();


        //user input to ask for student score
        int[] scorearray = new int[number_of_student];


        //ask for student scores and start i from 0 because array out of index error will come as array index start from 0 ok!!
        System.out.println("Enter " + number_of_student + " scores: ");
        for(int i = 0; i<number_of_student ;i++){
            scorearray[i] = input1.nextInt();
        }


        int best = scorearray[0];
        for(int i =0;i< number_of_student;i++){
            if(best< scorearray[i]){
                best = scorearray[i];
            }
        }

        //assign grade based on the best score

        for(int i = 0;i< number_of_student;i++){
            char lettergrade;
            if(scorearray[i] >= (best - 10)){
                lettergrade = 'A';

            }
            else if (scorearray[i]>= (best-20)){
                lettergrade = 'B';
            }
            else if(scorearray[i]>= (best - 30)){
                lettergrade = 'C';
            }
            else if(scorearray[i]>= (best - 40)){
                lettergrade = 'D';
            }
            else{
                lettergrade = 'F';
            }

            // display the result
            System.out.println(("Student" + (i) + " Score is " + scorearray[i] + " and grade is " +lettergrade));
        }

        input1.close(); // close the scanner


    }
}
