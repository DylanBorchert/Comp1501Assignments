import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;


class DiceCollectionTests {

    @Test
    @DisplayName("an empty collection has a total of -1")
    void empty_preset_dice_has_value_of_neg1() {
	ArrayList<Dice> dice = new ArrayList<>();

	assertThat(new DiceCollection(dice).total()).isEqualTo(-1);
    }


    @Test
    @DisplayName("a new random collection has a total of -1")
    void new_random_dice_collection_has_value_of_neg1() {
	ArrayList<Dice> dice = new ArrayList<>();
	dice.add(new Dice());
	dice.add(new Dice());

	assertThat(new DiceCollection(dice).total()).isEqualTo(-1);
    }


    @Test
    @DisplayName("a new preset collection has a total of -1")
    void new_preset_dice_collection_has_value_of_neg1() {
	ArrayList<Dice> dice = new ArrayList<>();
	dice.add(new Dice("2"));
	dice.add(new Dice("4 5 6 2"));

	assertThat(new DiceCollection(dice).total()).isEqualTo(-1);
    }


    @Test
    @DisplayName("a preset collection rolls as expected")
    void preset_collection_rolls_as_expected() {
	ArrayList<Dice> dice = new ArrayList<>();
	dice.add(new Dice("1 2"));
	dice.add(new Dice("3 4"));
	dice.add(new Dice("5 6 1"));

	DiceCollection collection = new DiceCollection(dice);

	assertThat(collection.total()).isEqualTo(-1);

	collection.roll();
	assertThat(collection.total()).isEqualTo(9);

	collection.roll();
	assertThat(collection.total()).isEqualTo(12);

	collection.roll();
	assertThat(collection.total()).isEqualTo(-1);
    }


    @Test
    @DisplayName("a random collection rolls as expected")
    void random_collection_rolls_as_expected() {
	ArrayList<Dice> dice = new ArrayList<>();
	dice.add(new Dice());
	dice.add(new Dice());
	dice.add(new Dice());

	DiceCollection collection = new DiceCollection(dice);

	final int NUM_ROLLS = 10_000;
	int sum = 0;

	for (int i = 0; i < NUM_ROLLS; i++) {
	    collection.roll();
	    sum += collection.total();
	}

	double expectedAvg = 3.5 * 3;  // expected value * number of dice
	double actualAvg = (double) sum / NUM_ROLLS;

	assertThat(actualAvg).isCloseTo(expectedAvg, within(1d));

    }

    @Test
    @DisplayName("if even just one die in this collection has a -1, whole collection returns -1")
    void even_one_neg1_causes_total_to_be_neg1() {
	ArrayList<Dice> dice = new ArrayList<>();
	dice.add(new Dice("1"));
	dice.add(new Dice());
	dice.add(new Dice());

	DiceCollection collection = new DiceCollection(dice);

	collection.roll();
	assertThat(collection.total()).isNotEqualTo(-1);


	collection.roll();
	assertThat(collection.total()).isEqualTo(-1);

    }

    @Test
    @DisplayName("values returns the individual values of each dice in this collection")
    void values_returns_the_individual_values_of_each_dice() {
	ArrayList<Dice> dice = new ArrayList<>();
	dice.add(new Dice("1 2"));
	dice.add(new Dice("3 4 2"));
	dice.add(new Dice("5 6"));

	DiceCollection collection = new DiceCollection(dice);

	collection.roll();
	assertThat(collection.values()).containsExactly(1, 3, 5);


	collection.roll();
	assertThat(collection.values()).containsExactly(2, 4, 6);

	collection.roll();
	assertThat(collection.values()).containsExactly(-1, 2, -1);

    }


    @Test
    @DisplayName("contains returns false if collection is empty")
    void contains_returns_false_if_collection_is_empty() {
	ArrayList<Dice> dice = new ArrayList<>();

	DiceCollection collection = new DiceCollection(dice);

	assertThat(collection.contains(1)).isFalse();

    }


    @Test
    @DisplayName("contains returns false if collection doesn't have target value")
    void contains_returns_false_if_collection_doesnt_have_target() {
	ArrayList<Dice> dice = new ArrayList<>();
	dice.add(new Dice("1 2"));

	DiceCollection collection = new DiceCollection(dice);

	assertThat(collection.contains(3)).isFalse();

    }

    @Test
    @DisplayName("contains returns true if collection has target value")
    void contains_returns_false_if_collection_has_target() {
	ArrayList<Dice> dice = new ArrayList<>();
	dice.add(new Dice("1 2"));
	dice.add(new Dice("3 4"));

	DiceCollection collection = new DiceCollection(dice);

	collection.roll();
	assertThat(collection.contains(1)).isTrue();
	assertThat(collection.contains(3)).isTrue();

	collection.roll();
	assertThat(collection.contains(2)).isTrue();
	assertThat(collection.contains(4)).isTrue();

	collection.roll();
	assertThat(collection.contains(-1)).isTrue();
    }


}
