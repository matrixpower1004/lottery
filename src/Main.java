import java.util.*;

/**
 * author         : Jisang Lee
 * date           : 2023-09-30
 * description    : Lottery v1.2
 */
public class Main {
    private static final int LAST_NUM = 45;

    public static void main(String[] args) {
        // 행운의 번호 추첨
        for (int i = 0; i < 5; i++) { // 5게임 동안 반복

            List<Integer> lottery = getLottery(); // 추첨기 안에 1 ~ 45까지 번호를 넣는다.
            List<Integer> goodGame = getGoodGame(lottery); // 추첨기에서 행운의 번호 6개를 뽑는다.
            printGoodGame(i, goodGame); // 행운의 번호를 출력한다.

            goodGame.clear(); // 출력 배열을 비운다.
            lottery.clear(); // 다음 게임을 위해 추첨기를 비운다.
        }
    }

    private static void printGoodGame(int i, List<Integer> goodNum) {
        char gameSequence = (char) ('A' + i);
        System.out.printf("Game %s:%n", gameSequence);
        goodNum.forEach(n -> System.out.printf("%d ", n));
        System.out.println();
        System.out.println("----------------------------");
    }

    private static List<Integer> getGoodGame(List<Integer> lottery) {
        List<Integer> goodNum = new ArrayList<>();
        int index = 0;
        while (goodNum.size() < 6) { // 한 게임의 번호 6개를 뽑을 때까지 반복
            int size = lottery.size();
            // 로또 번호는 1 ~ 45지만 추첨기 배열의 index는 0 ~ 44.
            index = (index + getLuckyNumber(size)) % size; // 환형구조의 index.
            goodNum.add(lottery.get(index)); // 뽑은 행운의 번호를 출력할 배열에 저장한다.
            lottery.remove(index); // 뽑은 번호는 추첨기 리스트에서 제거한다.
        }
        goodNum.sort(Comparator.naturalOrder()); // 행운의 번호를 오름차순 정렬한다.
        return goodNum;
    }

    private static List<Integer> getLottery() {
        Set<Integer> chooseNum = new HashSet<>();
        List<Integer> lottery = new ArrayList<>();
        while (lottery.size() < LAST_NUM) {
            lottery.add(getNumShuffle(chooseNum)); // Set의 중복을 허용하지 않은 특성을 이용.
        }
        return lottery;
    }

    private static int getNumShuffle(Set<Integer> chooseNum) {
        int beforeSize = chooseNum.size();
        int num = -1;
        while (beforeSize == chooseNum.size()) {
            num = new Random().nextInt(LAST_NUM) + 1; // 난수 1 ~ 45
            chooseNum.add(num);
        }
        return num;
    }

    private static int getLuckyNumber(int size) {
        return new Random().nextInt(size);
    }
}
