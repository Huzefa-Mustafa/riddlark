package com.group3;

import com.group3.models.Riddle;

import static com.group3.WelcomePage.checkIfDigit;
import static com.group3.WelcomePage.scanner;

public class DisplayRiddle {
    int choice = 1;
    public void displayRiddle(Riddle riddle) {

//        Riddle riddle = new Riddle();
        System.out.print("\nRiddle No: " + riddle.id);
        System.out.println(" , " + riddle.getRiddle()+"\n");
        for (String option : riddle.getOptions()) {
            System.out.print(choice+") " + option+"\t\t");
            choice++;
        }
        System.out.println("\n");
        System.out.println("Select the Choices form 1 to 5 ");
        do {
            String[] options = riddle.getOptions();
            String input = scanner.nextLine();
            if (checkIfDigit(input)) choice = Integer.parseInt(input);
            else choice = 10;
            switch (choice) {
                case 1 -> {
                    String option = options[0];
                    checkAns(riddle, option);
                }
                case 2 -> {
                    String option = options[1];
                    checkAns(riddle, option);
                }
                case 3 -> {
                    String option = options[2];
                    checkAns(riddle, option);
                }
                case 4 -> {
                    String option = options[3];
                    checkAns(riddle, option);
                }
                case 5 -> {
                    String option = options[4];
                    checkAns(riddle, option);
                }
                default -> System.out.println("\n\t************** please enter choice from 1 to 5 **************");
            }

        } while (choice != 6);

    }
    private void checkAns(Riddle riddle, String option) {
        if (option.equals(riddle.getAns())) {
            System.out.println("Correct Choices");
            choice = 6;
        }else{
            System.out.println("Wrong Choices");
            System.out.println("Correct Choice is: "+ riddle.getAns());
            choice = 6;
        }
//        riddle.setUserAns(option);
    }

    public static void main(String[] args) {
        Riddle riddle = new Riddle();
        riddle.setId("001");
        riddle.setRiddle("What can travel around the world while staying in a corner?");
        riddle.setAns("stamp");
        String[] options = {"address","barber","submarine","stamp","firefly"};
        riddle.setOptions(options);

        DisplayRiddle displayRiddle = new DisplayRiddle();
        displayRiddle.displayRiddle(riddle);

    }


}


