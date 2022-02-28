package lotto.model;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Long> result;

    public LottoResult(Lottos lottos, Lotto winningNumbers, LottoNumber bonusNumber) {
        this.result = generateLottoResult(lottos, winningNumbers, bonusNumber);
    }

    private Map<Rank, Long> generateLottoResult(Lottos lottos, Lotto winningNumbers, LottoNumber bonusNumber) {
        return Collections.unmodifiableMap(lottos.getLottos().stream()
            .map(lotto -> Rank.match(lotto, winningNumbers, bonusNumber))
            .collect(groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), counting())));
    }

    public long getRankCount(Rank rank) {
        return result.getOrDefault(rank, 0L);
    }

    long getTotalWinningMoney() {
        return result.entrySet().stream()
            .map(entry -> entry.getKey().getMoney() * entry.getValue())
            .mapToLong(i -> i)
            .sum();
    }
}