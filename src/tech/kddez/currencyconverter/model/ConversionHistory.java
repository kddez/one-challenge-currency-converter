package tech.kddez.currencyconverter.model;

import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {

    private List<Conversion> conversionList = new ArrayList<>();


    public ConversionHistory(){

    }
    public void addConversion(Conversion conversion){
       conversionList.add(conversion);
    }

    public List<Conversion> getConversionList() {
        return conversionList;
    }
}
