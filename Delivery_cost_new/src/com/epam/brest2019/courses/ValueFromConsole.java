package com.epam.brest2019.courses;

import com.epam.brest2019.courses.Menu.CorrectValue;
import com.epam.brest2019.courses.Menu.EnteredValue;
import com.epam.brest2019.courses.Menu.ExitValue;
import com.epam.brest2019.courses.Menu.IncorrectValue;

import java.math.BigDecimal;
import java.util.Scanner;

public class ValueFromConsole {
    private static final String QUIT_SYMBOL = "q";

    private Scanner scanner;

    public ValueFromConsole(){
        this.scanner = new Scanner(System.in);
    }

    public EnteredValue getValue(){
        EnteredValue enteredValue = receiveValueFromConsole();
        if(enteredValue.getType() != EnteredValue.Types.EXIT) {
            CorrectValue correctValue = (CorrectValue) enteredValue;
            return correctValue;
        }
        else return enteredValue;
    }

    public EnteredValue receiveValueFromConsole() {
        EnteredValue enteredValue = new IncorrectValue();
        while (enteredValue.getType() == EnteredValue.Types.INCORRECT) {
            enteredValue = parseInputValue(scanner.nextLine());
        }
        return enteredValue;
    }

    private EnteredValue parseInputValue(String inputValue) {
        EnteredValue result = new ExitValue();
        if (!inputValue.trim().toLowerCase().equals(QUIT_SYMBOL)) {
            try {
                BigDecimal value = new BigDecimal(inputValue);
                if (value.compareTo(BigDecimal.ZERO) > 0) {
                    result = new CorrectValue(new BigDecimal(inputValue));
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.format("Incorrect value: %s%n", inputValue);
                result = new IncorrectValue();
            }
        }
        return result;
    }
}
