package nextstep.subway.fare.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {
    @DisplayName("일반(19세)인 경우 할인 정책이 없다.")
    @Test
    void basic_fare() {
        // given
        DiscountPolicy discountPolicy = new NoneDiscountPolicy();
        // when & then
        assertThat(discountPolicy.discount(1250)).isEqualTo(1250);
    }

    @DisplayName("청소년(13세)인 경우 공제 350원에 20% 할인 정책이 적용된다.")
    @Test
    void teenagers_fare() {
        // given
        DiscountPolicy discountPolicy = new TeenagersDiscountPolicy();
        // when & then
        assertThat(discountPolicy.discount(2000)).isEqualTo(1320);
    }

    @DisplayName("어린이(13세)인 경우 공제 350원에 50% 할인 정책이 적용된다.")
    @Test
    void kids_fare() {
        // given
        DiscountPolicy discountPolicy = new KidsDiscountPolicy();
        // when & then
        assertThat(discountPolicy.discount(2000)).isEqualTo(825);
    }
}