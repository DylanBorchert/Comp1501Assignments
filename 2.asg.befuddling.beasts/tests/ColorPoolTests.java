import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ColorPoolTests {

    private ColorPool pool;

    @BeforeEach
    public void setUp() {
	pool = new ColorPool();
    }

    @Test
    @DisplayName("a new pool has all 3 colors 0")
    void new_pool_all_3_colors_0() {
	assertThat(pool.numRed()).isZero();
	assertThat(pool.numGreen()).isZero();
	assertThat(pool.numBlue()).isZero();
    }

    @Test
    @DisplayName("gaining red gains no matter case of letter")
    void red_gains_no_matter_case() {
	pool.gain("r");
	assertThat(pool.numRed()).isEqualTo(1);
	assertThat(pool.numGreen()).isZero();
	assertThat(pool.numBlue()).isZero();

	pool.gain("R");
	assertThat(pool.numRed()).isEqualTo(2);
	assertThat(pool.numGreen()).isZero();
	assertThat(pool.numBlue()).isZero();
    }

    @Test
    @DisplayName("gaining green gains no matter case of letter")
    void green_gains_no_matter_case() {
	pool.gain("g");
	assertThat(pool.numGreen()).isEqualTo(1);
	assertThat(pool.numRed()).isZero();
	assertThat(pool.numBlue()).isZero();

	pool.gain("G");
	assertThat(pool.numGreen()).isEqualTo(2);
	assertThat(pool.numRed()).isZero();
	assertThat(pool.numBlue()).isZero();
    }

    @Test
    @DisplayName("gaining blue gains no matter case of letter")
    void blue_gains_no_matter_case() {
	pool.gain("b");
	assertThat(pool.numBlue()).isEqualTo(1);
	assertThat(pool.numGreen()).isZero();
	assertThat(pool.numRed()).isZero();

	pool.gain("B");
	assertThat(pool.numBlue()).isEqualTo(2);
	assertThat(pool.numGreen()).isZero();
	assertThat(pool.numRed()).isZero();
    }

    @Test
    @DisplayName("paying cost removes mana from pool and is case and order insensitve")
    void paying_cost_removes_mana() {
	pool.gain("r");
	pool.gain("g");
	pool.gain("b");

	pool.gain("r");
	pool.gain("g");
	pool.gain("b");

	pool.gain("r");
	pool.gain("g");
	pool.gain("b");

	pool.pay("BGR");
	pool.pay("bgr");
	pool.pay("RgB");

	assertThat(pool.numRed()).isZero();
	assertThat(pool.numGreen()).isZero();
	assertThat(pool.numBlue()).isZero();
    }

    @Test
    @DisplayName("paying cost doesn't go into negatives")
    void paying_cost_doesnt_go_negative() {
	pool.pay("r");
	pool.pay("g");
	pool.pay("b");

	assertThat(pool.numRed()).isZero();
	assertThat(pool.numGreen()).isZero();
	assertThat(pool.numBlue()).isZero();
    }


    @Test
    @DisplayName("canPayFor returns false if no colors")
    void canPayFor_false_ifNoColors() {
	BeastCard singleRed = new BeastCard("red", "R");
	BeastCard singleGreen = new BeastCard("green", "G");
	BeastCard singleBlue = new BeastCard("blue", "B");

	assertThat(pool.canPayFor(singleRed)).isFalse();
	assertThat(pool.canPayFor(singleGreen)).isFalse();
	assertThat(pool.canPayFor(singleBlue)).isFalse();
    }


    @Test
    @DisplayName("canPayFor returns false if short 1 color")
    void canPayFor_false_ifShortOneColor() {
	BeastCard doubleRed = new BeastCard("red", "RR");
	BeastCard doubleGreen = new BeastCard("green", "GG");
	BeastCard doubleBlue = new BeastCard("blue", "BB");
	BeastCard combo = new BeastCard("combo", "RGGB");

	pool.gain("r");
	pool.gain("g");
	pool.gain("b");

	assertThat(pool.canPayFor(doubleRed)).isFalse();
	assertThat(pool.canPayFor(doubleGreen)).isFalse();
	assertThat(pool.canPayFor(doubleBlue)).isFalse();
	assertThat(pool.canPayFor(combo)).isFalse();
    }


    @Test
    @DisplayName("canPayFor returns false if short >1 color")
    void canPayFor_false_ifShortMoreThanOneColor() {
	BeastCard doubleRed = new BeastCard("red", "RR");
	BeastCard doubleGreen = new BeastCard("green", "GG");
	BeastCard doubleBlue = new BeastCard("blue", "BB");
	BeastCard combo = new BeastCard("combo", "RGGB");


	assertThat(pool.canPayFor(doubleRed)).isFalse();
	assertThat(pool.canPayFor(doubleGreen)).isFalse();
	assertThat(pool.canPayFor(doubleBlue)).isFalse();
	assertThat(pool.canPayFor(combo)).isFalse();
    }

}
