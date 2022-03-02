package lotto;

import java.util.Collection;

public class ValidationUtils {
    public static void validateEmptyCollection(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("빈 컬렉션이 입력되었습니다.");
        }
    }
}