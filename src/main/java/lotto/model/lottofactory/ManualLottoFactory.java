package lotto.model.lottofactory;

import static java.util.stream.Collectors.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import lotto.model.LottoNumber;

public class ManualLottoFactory implements LottoFactory {
    private final Iterator<Set<LottoNumber>> manualLottos;

    public ManualLottoFactory(List<List<Integer>> rawManualLottoNumbers) {
        List<List<Integer>> copyOfManualLottos = List.copyOf(rawManualLottoNumbers);
        this.manualLottos = copyOfManualLottos.stream()
            .map(this::toLottoNumbers)
            .collect(toUnmodifiableList()).iterator();
    }

    @Override
    public Lotto generate() {
        if (manualLottos.hasNext()) {
            return new Lotto(manualLottos.next());
        }
        throw new IllegalStateException("수동 구매 횟수 초과입니다.");
    }

    private Set<LottoNumber> toLottoNumbers(List<Integer> integers) {
        return integers.stream()
            .map(LottoNumber::new)
            .collect(toUnmodifiableSet());
    }
}
