package com.epam.brest2019.courses;

import java.math.BigDecimal;
import java.util.Scanner;

class Logic {

    private static boolean isActive = true;

    private BigDecimal pricePerKg = new BigDecimal("7");


    void run(){
        Scanner scan = new Scanner(System.in);
        GetPrices getPrices = new GetPrices();

        while (isActive){
            try {
                System.out.println("Enter the distance in kilometres");
                BigDecimal distance = input(scan.nextLine());
                System.out.println("Enter the weight in kilograms");
                BigDecimal weight = input(scan.nextLine());
                BigDecimal pricePerKm = getPrices.getPriceFromProperties(distance);
                BigDecimal priceForDistance = getPrices.priceForDistance(distance, pricePerKm);
                BigDecimal priceForWeight = getPrices.priceForWeight(weight, pricePerKg);
                BigDecimal fullPrice = getPrices.price(priceForDistance, priceForWeight);
                System.out.println("Price = " + fullPrice);
            }catch(NumberFormatException e){
            }
        }
    }

    private boolean isNumber(String input) {
        return input.matches("[\\d]+");
    }

    private BigDecimal input(String input) throws NumberFormatException{
        if(isNumber(input)){
            return new BigDecimal(input);
        }else if(input.toLowerCase().equals("q")){
            System.out.println("Bye");
            isActive = false;
        } else {
            System.out.println("Oops, wrong value");
            isActive = false;
        }
        return new BigDecimal(input);
    }


}
