package com.company;

public class ValidationResult {
    private int bracketsCount;
    private String validBrackets;

    public ValidationResult(int bracketsCount, String validBrackets) {
        this.bracketsCount = bracketsCount;
        this.validBrackets = validBrackets;
    }

    public int getBracketsCount() {
        return bracketsCount;
    }

    public String getValidBrackets() {
        return validBrackets;
    }
}
