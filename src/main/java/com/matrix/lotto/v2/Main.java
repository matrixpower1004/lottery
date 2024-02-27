package com.matrix.lotto.v2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        for (char i = 'A'; i < 'F'; i++) {
//            List<Integer> lottoNumbers = generateLottoNumbers();
            System.out.println("Game: " + i);
            System.out.println(generateLottoNumbers());
        }
    }

    private static List<Integer> generateLottoNumbers() {
        while (true) {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                int number;
                do {
                    number = (int) (Math.random() * 45) + 1;
                } while (numbers.contains(number));
                numbers.add(number);
            }
            Collections.sort(numbers);

            if (!isConsecutive(numbers) && !isAllOddOrEven(numbers) && !isNumberRangeConcentrated(
                numbers)) {
                return numbers;
            }
        }
    }

    private static boolean isConsecutive(List<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) + 1 == numbers.get(i + 1)) {
                if (i + 5 < numbers.size() && numbers.get(i) + 5 == numbers.get(i + 5)) {
                    return true; // 6연번 조합 검사
                }
                if (i + 2 < numbers.size() && numbers.get(i + 2) - numbers.get(i) == 2) {
                    return true; // 연속행렬 검사
                }
            }
        }
        return false;
    }

    private static boolean isAllOddOrEven(List<Integer> numbers) {
        boolean isOdd = numbers.get(0) % 2 != 0;
        for (Integer number : numbers) {
            if ((number % 2 != 0) != isOdd) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumberRangeConcentrated(List<Integer> numbers) {
        int[] ranges = new int[5]; // 1-9, 10-19, 20-29, 30-39, 40-45
        for (Integer number : numbers) {
            ranges[(number - 1) / 10]++;
        }
        for (int range : ranges) {
            if (range > 3) { // 특정 번호대에 3개 이상의 숫자가 몰린 경우
                return true;
            }
        }
        return false;
    }
}