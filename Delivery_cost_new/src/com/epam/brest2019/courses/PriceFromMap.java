package com.epam.brest2019.courses;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeSet;

public class PriceFromMap {
    public BigDecimal priceFromMap(Map<Integer, BigDecimal> inputMap, BigDecimal inputValue){
        TreeSet<Integer> setKeys = new TreeSet<>(inputMap.keySet());
        int value = setKeys.last();
        for(int key: setKeys){
            if(inputValue.compareTo(BigDecimal.valueOf(key))<=0) {
                value = key;
                break;
            }

        }
        BigDecimal outputValue = inputMap.get(value);
        return outputValue;
    }
}
