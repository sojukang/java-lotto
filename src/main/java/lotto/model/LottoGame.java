package lotto.model;

import static lotto.ValidationUtils.*;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {
    private static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoGame(List<Integer> integers, int bonusNumber) {
        ArrayList<Integer> winningNumbers = new ArrayList<>(integers);
        validateEmptyCollection(winningNumbers);
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public static Lottos buyLottos(LottoMoney lottoMoney) {
        return new Lottos(new LottoNumberGenerator(), lottoMoney.getLottoSize());
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    public LottoResult generateLottoResult(Lottos lottos) {
        return new LottoResult(lottos, winningNumbers, bonusNumber);
    }

    public Yield calculateYield(LottoMoney lottoMoney, LottoResult lottoResult) {
        return new Yield(lottoMoney, lottoResult.getTotalWinningMoney());
    }
}
