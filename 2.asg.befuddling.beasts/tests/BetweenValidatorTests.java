import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class BetweenValidatorTests {

    private static final String EOLN = String.format("%n");

    @Test
    @DisplayName("validInclusiveNumber returns number at lower bound")
    void return_lower_bound() {
	Scanner input = new Scanner("1");
	BetweenValidator validator = new BetweenValidator(input, 1, 5);

	assertThat(validator.validInclusiveNumber("prompt")).isEqualTo(1);
    }


    @Test
    @DisplayName("validInclusiveNumber returns number at upper bound")
    void return_upper_bound() {
	Scanner input = new Scanner("5");
	BetweenValidator validator = new BetweenValidator(input, 1, 5);

	assertThat(validator.validInclusiveNumber("prompt")).isEqualTo(5);
    }


    @Test
    @DisplayName("validInclusiveNumber returns number one higher than lower bound")
    void return_one_over_lower_bound() {
	Scanner input = new Scanner("2");
	BetweenValidator validator = new BetweenValidator(input, 1, 5);

	assertThat(validator.validInclusiveNumber("prompt")).isEqualTo(2);
    }

    @Test
    @DisplayName("validInclusiveNumber returns number one lower than upper bound")
    void return_one_under_upper_bound() {
	Scanner input = new Scanner("4");
	BetweenValidator validator = new BetweenValidator(input, 1, 5);

	assertThat(validator.validInclusiveNumber("prompt")).isEqualTo(4);
    }


    @Test
    @DisplayName("validInclusiveNumber returns the second number from the input if the first number is too low and second is valid")
    void return_second_valid_if_first_too_low() {
	Scanner input = new Scanner("0" + EOLN + "1");
	BetweenValidator validator = new BetweenValidator(input, 1, 5);

	assertThat(validator.validInclusiveNumber("prompt")).isEqualTo(1);
    }

    @Test
    @DisplayName("validInclusiveNumber returns the second number from the input if the first number is too high and second is valid")
    void return_second_valid_if_first_too_high() {
	Scanner input = new Scanner("6" + EOLN + "1");
	BetweenValidator validator = new BetweenValidator(input, 1, 5);

	assertThat(validator.validInclusiveNumber("prompt")).isEqualTo(1);
    }

    @Test
    @DisplayName("validInclusiveNumber returns the second number from the input if the first entry is not a number and second is valid")
    void return_second_valid_if_first_not_a_number() {
	Scanner input = new Scanner("foo" + EOLN + "1");
	BetweenValidator validator = new BetweenValidator(input, 1, 5);

	assertThat(validator.validInclusiveNumber("prompt")).isEqualTo(1);
    }

    @Test
    @DisplayName("validInclusiveNumber returns the second number from the input if the first entry is empty and second is valid")
    void return_second_valid_if_first_is_empty() {
	Scanner input = new Scanner("  " + EOLN + "1");
	BetweenValidator validator = new BetweenValidator(input, 1, 5);

	assertThat(validator.validInclusiveNumber("prompt")).isEqualTo(1);
    }

    @Test
    @DisplayName("validInclusiveNumber returns last valid entry if given multiple crappy inputs")
    void return_final_valid_number_if_rest_crappy() {
	String crappyInput = "";
	crappyInput += EOLN;
	crappyInput += "woo!" + EOLN;
	crappyInput += "-12.2" + EOLN;
	crappyInput += "-100" + EOLN;
	crappyInput += "12121" + EOLN;
	crappyInput += "5";
	Scanner input = new Scanner(crappyInput);
	BetweenValidator validator = new BetweenValidator(input, 1, 5);

	assertThat(validator.validInclusiveNumber("prompt")).isEqualTo(5);
    }

}
