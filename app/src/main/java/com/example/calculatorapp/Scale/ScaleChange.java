package com.example.calculatorapp.Scale;

public class ScaleChange{

    private String equation,result;

    private String scale1,scale2;

    public ScaleChange(String equation,String scale1,String scale2){
        this.equation = equation;
        this.scale1 = scale1;
        this.scale2 = scale2;
    }

    String count(){
        int num = Integer.parseInt(equation);
        int middle = 0;
        String dec = "";
        switch (scale1){
            case "十进制":
                middle = num;
                break;
            case "八进制":
                middle = Integer.parseInt(equation,8);
                break;
        }

        switch (scale2){
            case "十进制":
                dec = Integer.toString(middle);
                break;
            case "八进制":
                dec = Integer.toOctalString(middle);
        }
        return dec;
    }
}
