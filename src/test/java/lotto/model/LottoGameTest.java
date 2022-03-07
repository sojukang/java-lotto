package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.lottofactory.Lotto;
import lotto.model.lottofactory.LottoFactory;
import lotto.model.lottofactory.ManualLottoFactory;

class LottoGameTest {

    @Test
    @DisplayName("수동 구매 검증 테스트")
    void buyManualLottosTest() {
        LottoGame lottoGame = new LottoGame(1000, 1, new TestAutoLottoFactory());
        List<List<Integer>> rawManualLottos = List.of(List.of(1, 2, 3, 11, 12, 13));

        Lottos manualLottos = lottoGame.buyManualLottos(rawManualLottos);
        List<Lotto> expected = new Lottos(new TestAutoLottoFactory(), 1).getLottos();

        assertThat(manualLottos.getLottos().toString()).isEqualTo(expected.toString());
    }

    @Test
    @DisplayName("자동 구매 검증 테스트")
    void buyAutoLottosTest() {
        LottoGame lottoGame = new LottoGame(1000, 0, new TestAutoLottoFactory());

        List<Lotto> autoLottos = lottoGame.getAutoLottos();
        List<Lotto> expected = new Lottos(new TestAutoLottoFactory(), 1).getLottos();

        assertThat(autoLottos.toString()).isEqualTo(expected.toString());
    }

    @Test
    @DisplayName("보너스 볼 번호와 지난주 당첨 번호가 중복된 번호인 경우 예외 처리")
    void validateDuplicateBonusBallNumberTest() {
        LottoGame lottoGame = new LottoGame(1000, 0, new TestAutoLottoFactory());
        assertThatThrownBy(() ->
            lottoGame.generateLottoResult(new Lottos(new ManualLottoFactory(Collections.emptyList()), 0),
                List.of(1, 2, 3, 4, 5, 6), 6))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("보너스 볼 번호가 당첨 번호와 중복입니다.");
    }

    @Test
    @DisplayName("수익률 계산 확인")
    void calculateYieldTest() {
        LottoGame lottoGame = new LottoGame(1000, 0, new TestAutoLottoFactory());
        LottoResult lottoResult = lottoGame.generateLottoResult(
            new Lottos(new ManualLottoFactory(Collections.emptyList()), 0),
            List.of(1, 2, 3, 4, 5, 6),
            7);
        Yield yield = lottoGame.calculateYield(lottoResult);

        assertThat(yield.getYield()).isCloseTo(5.00f, Percentage.withPercentage(99));
    }

    static class TestAutoLottoFactory implements LottoFactory {
        private final LottoFactory factory;

        public TestAutoLottoFactory() {
            this.factory = new ManualLottoFactory(List.of(List.of(1, 2, 3, 11, 12, 13)));
        }

        @Override
        public Lotto generate() {
            return factory.generate();
        }
    }
}
