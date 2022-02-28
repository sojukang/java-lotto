package lotto.model;

import java.util.Arrays;

public enum Rank {
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    FAIL(0, 0);

    private final int matchScore;
    private final long money;

    Rank(int matchScore, long money) {
        this.matchScore = matchScore;
        this.money = money;
    }

    static Rank match(Lotto lotto, Lotto winningNumbers, LottoNumber bonusNumber) {
        return Rank.find(lotto.getMatchScore(winningNumbers), lotto.isMatchNumber(bonusNumber));
    }

    private static Rank find(int matchWinningNumbers, boolean isMatchBonus) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.matchScore == matchWinningNumbers)
            .filter(rank -> rank != THIRD || !isMatchBonus)
            .findFirst()
            .orElse(FAIL);
    }

    public int getMatchScore() {
        return matchScore;
    }

    public long getMoney() {
        return money;
    }
}
