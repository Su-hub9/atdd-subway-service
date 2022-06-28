package nextstep.subway.fare.policy;

import nextstep.subway.fare.domain.Fare;

public class NoneDiscountPolicy implements DiscountPolicy {
    @Override
    public Fare discount(int fare) {
        return Fare.from(fare);
    }
}
