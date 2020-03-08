import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DiceTests {

    @Test
    @DisplayName("a new preset dice has a value of -1")
    void new_preset_dice_has_value_of_neg1() {
	assertThat(new Dice("1").value()).isEqualTo(-1);
    }


    @Test
    @DisplayName("a new random dice has a value of -1")
    void new_random_dice_has_value_of_neg1() {
	assertThat(new Dice().value()).isEqualTo(-1);
    }


    @Test
    @DisplayName("rolling a preset dice rolls just those numbers and then -1 afterward")
    void rolling_a_preconstructed_dice_rolls_those_numbers() {
	Dice dice = new Dice("1 3 2");

	dice.roll();
	assertThat(dice.value()).isEqualTo(1);

	dice.roll();
	assertThat(dice.value()).isEqualTo(3);

	dice.roll();
	assertThat(dice.value()).isEqualTo(2);

	dice.roll();
	assertThat(dice.value()).isEqualTo(-1);
    }


    @Test
    @DisplayName("a random dice behaves randomly")
    void a_random_dice_behaves_randomly() {
	final int NUM_THROWS = 10_000;
	Dice dice = new Dice();
	int sum = 0;

	for (int i = 0; i < NUM_THROWS; i++) {
	    dice.roll();
	    sum += dice.value();
	}

	double actualAvg = (double) sum / NUM_THROWS;
	double expectedAvg = 3.5;

	assertThat(actualAvg).isCloseTo(expectedAvg, within(1d));

    }

}
