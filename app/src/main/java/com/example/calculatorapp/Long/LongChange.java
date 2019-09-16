package com.example.calculatorapp.Long;

public class LongChange {
    private String equation,result;

    private String unit1,unit2;

    public LongChange(String equation){
        this.equation = equation;
    }

    public void getUnitOne(String unit1){
        this.unit1 = unit1;
    }

    public void getUnitTwo(String unit2){
        this.unit2 = unit2;
    }

    String  count(){
        int num = Integer.parseInt(equation);
        float mi = 0;
        switch (unit1){
            case "千米":
                mi = num * 1000;
                break;
        }

        switch (unit2){
            case "米":
                mi = mi;
                break;
        }

        result = String.valueOf(mi);
        return result;
    }

}
