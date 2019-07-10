package com.epam.brest2019.courses.Calculate;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {

    public BigDecimal calculate(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm) {
        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }

}