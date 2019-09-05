package com.example.calculatorapp;

import java.util.Stack;

public class CounterByEquation {
    private String equation;

    private Stack<String> stack;  //存储符号
    private String []queue = new String[50];  //接受中缀表达式
    private int count=0;


    public CounterByEquation(String equation){
        this.equation = equation;
    }

    String solveEquation(){

        int length = equation.length();

        //求后缀表达式
        stack = new Stack<>();

        for(int i=0;i<length;i++){

            char curChar = equation.charAt(i);
            if(curChar=='+'||curChar=='-'||curChar=='*'||curChar=='/'||curChar=='%'){

                String top = "";
                if(!stack.empty())
                    top = stack.peek();
                if (curChar == '%') {
                    if(!stack.empty() && top.equals("%")){
                        queue[count++] = stack.pop();
                    }
                    stack.push(curChar+"");

                } else if ((curChar == '+' || curChar == '-')) {
                    while (true) {
                        if (stack.empty()) break;
                        queue[count++] = stack.pop();
                    }
                    stack.push(curChar+"");
                } else if (curChar == '*' || curChar == '/') {
                    if (!stack.empty()){
                        while (true) {
                            if (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%")) {
                                queue[count++] = stack.pop();
                            }else{
                                break;
                            }
                            if (stack.empty()) break;
                        }
                    }
                    stack.push(curChar+"");
                }
            } else{  //数字
                String numStr = curChar+"";
                for(i++;i<length;i++)
                {
                    char curBackChar = equation.charAt(i);
                    if(curBackChar=='+'||curBackChar=='-'||curBackChar=='*'||curBackChar=='/'||curBackChar=='%'){
                        i--;
                        break;
                    }
                    else
                        numStr+=curBackChar;
                }
                queue[count++] = numStr;

            }
        }

        while(true){
            if(stack.empty()) break;
            queue[count++] = stack.pop();
        }
        String logStr = "";
        for(int i=0;i<count;i++){
            logStr+=queue[i]+" ";
        }


        /*由中缀表达式求值*/
        for(int i=0;i<count;i++){
            String member = queue[i];
            int numLength = member.length();
            if(member.charAt(numLength-1)=='!'){
                member = member.substring(0,numLength-1);
                int num = Integer.parseInt(member);
                int sum = 1;
                while(num>0){
                    sum *= (num--);
                }
                queue[i] = sum+"";
                continue;
            }
            if(member.equals("π")) {
                queue[i] = "3.1415926";
                continue;
            }
            if(member.equals("e")) {
                queue[i] = "2.71828183";
                continue;
            }

            if(member.equals("+")||member.equals("-")||member.equals("*")||member.equals("/")||member.equals("%")||member.equals("^")){
                double first = Double.parseDouble(queue[i-2]);
                double second = 0;
                if(!queue[i-1].equals("-"))
                    second = Double.parseDouble(queue[i-1]);
                if (member.equals("+")) first += second;
                if (member.equals("-")) first -= second;
                if (member.equals("*")) first *= second;
                if (member.equals("/")) first /= second;
                if (member.equals("%")) first %= second;
                queue[i-2] = first+"";
                //前移
                for(int j = i-1;j<count-2;j++){
                    queue[j] = queue[j+2];
                }
                count-=2;
                i-=2;
            }
        }
        return queue[0];
    }
}
