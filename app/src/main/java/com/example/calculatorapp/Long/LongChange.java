package com.example.calculatorapp.Long;

public class LongChange {
    private String equation,result;

    private String unit1,unit2;

    public LongChange(String equation,String unit1,String unit2){

        this.equation = equation;
        this.unit1 = unit1;
        this.unit2 = unit2;
    }

    String  count(){
        double num = Double.parseDouble(equation);
        double mi = 0;
        switch (unit1){
            case "千米":
                mi = num * 1000;
                break;
            case "米":
                mi = num;
                break;
            case "分米":
                mi = num / 10;
                break;
            case "厘米":
                mi = num / 100;
                break;
            case "毫米":
                mi = num / 1000;
                break;
            case "海里":
                mi = num * 1852;
                break;
            case "英里":
                mi = num * 1609.344;
                break;
            case "弗隆":
                mi = num * 201.168;
                break;
            case "英寻":
                mi = num * 1.8288;
                break;
            case "码":
                mi = num * 0.9144;
                break;
            case "英尺":
                mi = num * 0.3048;
                break;
            case "英寸":
                mi = num * 0.0254;
                break;
            case "公里":
                mi = num * 1000;
                break;
            case "里":
                mi = num * 500;
                break;
            case "丈":
                mi = num * 3.33;
                break;
            case "尺":
                mi = num * 0.33;
                break;
            case "寸":
                mi = num * 0.033;
                break;
        }

        switch (unit2){
            case "千米":
                mi = mi / 1000;
                break;
            case "米":
                mi = mi;
                break;
            case "分米":
                mi = mi * 10;
                break;
            case "厘米":
                mi = mi * 100;
                break;
            case "毫米":
                mi = mi * 1000;
                break;
            case "海里":
                mi = mi / 1852;
                break;
            case "英里":
                mi = mi / 1609.344;
                break;
            case "弗隆":
                mi = mi / 201.168;
                break;
            case "英寻":
                mi = mi / 1.8288;
                break;
            case "码":
                mi = mi / 0.9144;
                break;
            case "英尺":
                mi = mi / 0.3048;
                break;
            case "英寸":
                mi = mi / 0.0254;
                break;
            case "公里":
                mi = mi / 1000;
                break;
            case "里":
                mi = mi / 500;
                break;
            case "丈":
                mi = mi / 3.33;
                break;
            case "尺":
                mi = mi / 0.33;
                break;
            case "寸":
                mi = mi / 0.033;
                break;
        }
        result = String.valueOf(mi);
        return result;
    }

}
