package lotto.model;

public class Yield {
    private static final int GAIN_THRESHOLD = 1;

    private final float yield;

    Yield(LottoMoney lottoMoney, long totalWinningMoney) {
        this.yield = calculateYield(lottoMoney, totalWinningMoney);
    }

    private float calculateYield(LottoMoney lottoMoney, long totalWinningMoney) {
        return lottoMoney.divide(totalWinningMoney);
    }

    public boolean isGain() {
        return yield >= GAIN_THRESHOLD;
    }

    public float getYield() {
        return yield;
    }
}
