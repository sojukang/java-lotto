package lotto.controller;

import static lotto.view.InputView.*;
import static lotto.view.ResultView.*;

import lotto.model.AutoLottoFactory;
import lotto.model.LottoGame;
import lotto.model.LottoResult;
import lotto.model.Lottos;

public class LottoController {
    public void runGame() {
        long lottoMoney = insertMoney();
        int numberOfManualLottos = inputNumberOfManualLottos();
        LottoGame lottoGame = new LottoGame(lottoMoney, numberOfManualLottos, new AutoLottoFactory());

        Lottos manualLottos = lottoGame.buyManualLottos(inputManualLottos(numberOfManualLottos));
        printGeneratedLottos(manualLottos.getLottos(), lottoGame.getAutoLottos());

        LottoResult lottoResult = lottoGame.generateLottoResult(manualLottos, inputWinningNumbers(),
            inputBonusNumber());
        printResultStatistics(lottoResult);
        printYield(lottoGame.calculateYield(lottoResult));
    }
}
