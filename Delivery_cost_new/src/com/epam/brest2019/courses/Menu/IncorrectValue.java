package com.epam.brest2019.courses.Menu;

public class IncorrectValue implements EnteredValue {
    @Override
    public Types getType() {
        return Types.INCORRECT;
    }
}
