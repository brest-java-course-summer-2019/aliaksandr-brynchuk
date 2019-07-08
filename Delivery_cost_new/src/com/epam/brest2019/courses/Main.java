package com.epam.brest2019.courses;

import com.epam.brest2019.courses.Calculate.*;
import com.epam.brest2019.courses.Files.*;
import com.epam.brest2019.courses.Menu.CorrectValue;
import com.epam.brest2019.courses.Menu.EnteredValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String QUIT_SYMBOL = "q";

    private static final String PRICES_PER_KG = "price_per_kg.csv";
    private static final String PRICES_PER_KM = "price_per_km.csv";

    public static void main(String[] args)  throws IOException {
        Scanner scanner = new Scanner(System.in);

        Calculator calculator = new CalculatorImpl();
        FileReader fileReader = new CSVReader();
        ValueFromConsole fromConsole = new ValueFromConsole();
        PriceFromMap pfm = new PriceFromMap();

        BigDecimal weightPriceFromFile;
        BigDecimal distancePriceFromFile;

        BigDecimal weightFromConsole;
        BigDecimal distanceFromConsole;

        EnteredValue enteredValue;


        Map<Integer, BigDecimal> weightPrices = fileReader.readData(PRICES_PER_KG);
        if (weightPrices == null || weightPrices.isEmpty()) {
            throw new FileNotFoundException("File not found.");
        }

        Map<Integer, BigDecimal> distancePrices = fileReader.readData(PRICES_PER_KM);
        if (distancePrices == null || distancePrices.isEmpty()) {
            throw new FileNotFoundException("File not found.");
        }

        /*do{
            System.out.println("Enter weight in kg or 'q' for quit");
            enteredValue = fromConsole.getValue();

        }while(enteredValue.getType()!=EnteredValue.Types.EXIT);*/

        while(true){
            System.out.println("Enter weight in kg or 'q' for quit");
            enteredValue = fromConsole.getValue();
            if(enteredValue.getType()==EnteredValue.Types.EXIT)
                break;
            else weightFromConsole = ((CorrectValue)enteredValue).getValue();
            System.out.println("Enter distance in kg or 'q' for quit");
            enteredValue = fromConsole.getValue();
            if(enteredValue.getType()==EnteredValue.Types.EXIT)
                break;
            else distanceFromConsole = ((CorrectValue)enteredValue).getValue();

            weightPriceFromFile = pfm.priceFromMap(weightPrices, weightFromConsole);
            distancePriceFromFile = pfm.priceFromMap(distancePrices,distanceFromConsole);

           BigDecimal price =  calculator.calculate(weightFromConsole,distanceFromConsole, weightPriceFromFile, distancePriceFromFile);
            System.out.println(price);

        }




    }
}
