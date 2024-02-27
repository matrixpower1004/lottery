package com.matrix.lotto.v1;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        // 1부터 45까지의 숫자를 생성하고, 이 중에서 무작위로 6개를 선택합니다.
        for (char i = 'A'; i < 'F'; i++) {
            System.out.println("Game: " + i);
            System.out.println(generateGoodLuck(random));
        }
    }

    private static List<Integer> generateGoodLuck(Random random) {
        return random.ints(1, 46) // 1부터 45까지의 난수 스트림 생성 (46은 포함되지 않음)
            .distinct() // 중복 값을 제거
            .limit(6) // 6개의 숫자만 선택
            .boxed() // IntStream을 Stream<Integer>로 박싱
            .sorted() // 결과 정렬
            .collect(Collectors.toList());// List<Integer>로 수집
    }
}
