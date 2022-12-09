package com.company;

import java.util.ArrayList;
import java.util.List;

public class BracketsValidator {
    public ValidationResult validate(String input) {
        List<Integer> nestingLevels = convertToNestingLevels(input);

        while (!isValid(nestingLevels)) {
            if (!isValidClose(nestingLevels)) {
                nestingLevels = validateClose(nestingLevels);
            }

            if (isValidClose(nestingLevels) && !isValidOpen(nestingLevels)) {
                nestingLevels = validateOpen(nestingLevels);
            }
        }


        String validString = convertFromNestingLevels(nestingLevels);
        int validBracketsCount = validString.length();
        return new ValidationResult(validBracketsCount, validString);
    }

    private List<Integer> convertToNestingLevels(String input) {
        char[] charArray = input.toCharArray();
        ArrayList<Integer> nestingLevels = new ArrayList<>();
        Integer curNestingLevel = 0;
        for (char c : charArray) {
            String curChar = Character.toString(c);
            if (curChar.equals("(")) {
                curNestingLevel++;
                nestingLevels.add(curNestingLevel);

            } else if (curChar.equals(")")) {
                curNestingLevel--;
                nestingLevels.add(curNestingLevel);
            }
        }

        return nestingLevels;
    }

    private String convertFromNestingLevels(List<Integer> nestingLevels) {
        int prevNestingLevel = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer nestingLevel : nestingLevels) {
            if (nestingLevel > prevNestingLevel) {
                stringBuilder.append("(");
            } else {
                stringBuilder.append(")");
            }
            prevNestingLevel = nestingLevel;
        }

        return stringBuilder.toString();

    }

    private boolean isValid(List<Integer> nestingLevels) {
        if (nestingLevels.isEmpty()) {
            return true;
        }

        if (nestingLevels.get(nestingLevels.size() - 1) != 0) {
            return false;
        }

        for (Integer currentLevel : nestingLevels) {
            if (currentLevel < 0) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidClose(List<Integer> nestingLevels) {
        if (nestingLevels.isEmpty()) {
            return true;
        }

        for (Integer currentLevel : nestingLevels) {
            if (currentLevel < 0) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidOpen(List<Integer> nestingLevels) {
        if (nestingLevels.isEmpty()) {
            return true;
        }

        return nestingLevels.get(nestingLevels.size() - 1) == 0;
    }


    private List<Integer> validateClose(List<Integer> nestingLevels) {
        boolean negativeFound = false;
        int negativePosition = 0;
        for (int i = 0; i < nestingLevels.size(); i++) {

            if (negativeFound) {
                nestingLevels.set(i, nestingLevels.get(i) + 1);
            }

            if (nestingLevels.get(i) < 0 && !negativeFound) {
                negativeFound = true;
                negativePosition = i;
            }
        }

        nestingLevels.remove(negativePosition);

        return nestingLevels;
    }


    private List<Integer> validateOpen(List<Integer> nestingLevels) {
        int lastZeroPosition = -1;
        for (int i = 0; i < nestingLevels.size(); i++) {
            if (nestingLevels.get(i) == 0) {
                lastZeroPosition = i;
            }

        }

        nestingLevels.remove(lastZeroPosition + 1);

        for (int i = lastZeroPosition + 1; i < nestingLevels.size(); i++) {
            nestingLevels.set(i, nestingLevels.get(i) - 1);
        }

        return nestingLevels;
    }
}
