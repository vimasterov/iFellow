package com.company;


public class Main {

    public static void main(String[] args) {
        String input1 = "(()";
        String input2 = ")()())";
        String input3 = ")(()())";
        String input4 = ")(";

        String input5 = ")())((())";
        String input6 = "()((())(()";
        String input7 = "(()))(())((())";

        var bracketsValidator = new BracketsValidator();
        var result = bracketsValidator.validate(input1);
        System.out.println(result.getBracketsCount()+" - "+result.getValidBrackets());
    }
}
