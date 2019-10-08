package com.example.calculatorapp.Scale;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput_scale {
    private String equation_unit;
    private String scale;

    public void setEquationUnit(String equation_unit){
        this.equation_unit = equation_unit;
    }

    public String getEquationUnit(){
        return equation_unit;
    }
    public void setEquationScale(String scale){this.scale = scale;}

    boolean checkNumberUnit(){
        int length = equation_unit.length();
        if(length == 0)
            return true;
        char perChar = equation_unit.charAt(length-1);

        if(length == 1 && perChar == '0')
            equation_unit = "";
        return true;
    }



    boolean checkPointUnit(){
        int length = equation_unit.length();

        if(length == 0)
            equation_unit+='0';

        int pointNum = 0;
        for(int i = length-1;i>=0;i--){
            char checkChar = equation_unit.charAt(i);

            if(checkChar == '.')
                pointNum++;

        }
        if(pointNum>0)
            return false;
        return true;
    }

    void BackSpaceUnit(){
        int length = equation_unit.length();
        if(length == 0)
            return;
        equation_unit = equation_unit.substring(0,length-1);

    }
}
