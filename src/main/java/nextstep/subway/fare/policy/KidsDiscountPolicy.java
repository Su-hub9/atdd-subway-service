package nextstep.subway.fare.policy;

import nextstep.subway.fare.domain.Fare;

public class KidsDiscountPolicy implements DiscountPolicy {
    private static final int DEDUCTION_FARE = 350;

    @Override
    public Fare discount(int fare) {
        return Fare.from(calculateFare(fare));
    }

    private int calculateFare(int fare) {
        return (int) ((fare - DEDUCTION_FARE) * 0.5);
    }
}
