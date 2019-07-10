package com.epam.brest2019.courses.Calculate;

import java.math.BigDecimal;


public interface Calculator {

    BigDecimal calculate(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm);

}