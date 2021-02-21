package lottogame.view;

import lottogame.domain.Rank;
import lottogame.domain.dto.LottoResults;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void showLottos(List<List<Integer>> lottos) {
        for (List<Integer> lotto : lottos) {
            System.out.println(formatLottosOutput(lotto));
        }
    }

    private static String formatLottosOutput(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> String.valueOf(number))
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static void printResult(LottoResults results) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        printSummary(results);
        System.out.printf("총 수익률은 %.2f입니다.\n", results.getProfit());
    }

    private static void printSummary(LottoResults results) {
        for (Map.Entry<Rank, Integer> statistic : results.values().entrySet()) {
            Rank rank = statistic.getKey();
            int price = statistic.getValue();
            printRank(rank, price);
        }
    }

    private static void printRank(Rank rank, int price) {
        if (rank.isSecond()) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", rank.getCount(), rank.getMoney(), price);
            return;
        }
        System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getCount(), rank.getMoney(), price);
    }
}