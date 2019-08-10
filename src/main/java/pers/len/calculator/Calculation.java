package pers.len.calculator;

public class Calculation {
    int add ;
    int sub ;
    int mul ;
    int div ;
    public Calculation(){
    }

    public Calculation(String button){
        if(button.equals("+")){
            this.add = 1 + 2 ;
        }
    }

}
