package com.github.zetaapps.either;

import android.support.annotation.Nullable;

public class Either<S, F> {

    public static <S, F> Either<S, F> fromSuccess(S successValue) {
        return new Either<>(successValue, null);
    }

    public static <S, F> Either<S, F> fromFailure(F failureValue) {
        return new Either<>(null, failureValue);
    }

    @Nullable
    public final S successValue;
    @Nullable
    public final F failureValue;

    // require using the static factory methods given
    private Either(@Nullable S successValue, @Nullable F failureValue) {
        this.successValue = successValue;
        this.failureValue = failureValue;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        // lifted from the AOSP "Pair" class
        if (!(o instanceof Either)) {
            return false;
        }
        Either<?, ?> p = (Either<?, ?>) o;
        return objectsEqual(p.successValue, successValue) && objectsEqual(p.failureValue, failureValue);
    }

    private static boolean objectsEqual(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    @Override
    public int hashCode() {
        // Inspired from the Android Open Source Pair class
        return (successValue == null ? 0 : successValue.hashCode())
                ^ (failureValue == null ? 0 : failureValue.hashCode());
    }

    @Override
    public String toString() {
        return "OneOf{" +
                "successValue=" + successValue +
                ", failureValue=" + failureValue +
                '}';
    }
}
