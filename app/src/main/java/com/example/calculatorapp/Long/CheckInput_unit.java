package com.example.calculatorapp.Long;

public class CheckInput_unit {
    private String equation_unit;

    public void setEquationUnit(String equation_unit){
        this.equation_unit = equation_unit;
    }

    public String getEquationUnit(){
        return equation_unit;
    }

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
