package com.epam.brest2019.courses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class GetPrices {
    public BigDecimal priceForDistance(BigDecimal distance, BigDecimal pricePerKm){
        return distance.multiply(pricePerKm);
    }

    public BigDecimal priceForWeight(BigDecimal weight, BigDecimal pricePerKg){
        return weight.multiply(pricePerKg);
    }

    public BigDecimal price(BigDecimal priceForDistance, BigDecimal priceForWeight){
        return priceForDistance.add(priceForWeight);
    }

    public BigDecimal getPriceFromProperties(BigDecimal distance) {
        Properties prop = new Properties();
        try (FileInputStream stream = new FileInputStream("resources/price_per_km.properties")) {
            prop.load(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(distance.doubleValue()<10){
            return new BigDecimal(prop.getProperty("less_10"));
        }else if(distance.doubleValue()>10 & distance.doubleValue()<100){
            return new BigDecimal(prop.getProperty("less_100"));
        }else return new BigDecimal(prop.getProperty("more_100"));
    }

}
