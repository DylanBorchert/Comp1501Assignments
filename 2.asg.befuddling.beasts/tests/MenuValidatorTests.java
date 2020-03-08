import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
class MenuValidatorTests {

    private static final String EOLN = String.format("%n");

    @Test
    @DisplayName("validSelection returns valid selection that's already in proper case with no trailing/leading white space")
    void return_valid_selection_already_in_proper_case() {
	Scanner input = new Scanner("A");
	MenuValidator validator = new MenuValidator(input, "ABC");

	assertThat(validator.validSelection("prompt")).isEqualTo("A");

	input = new Scanner("B");
	validator = new MenuValidator(input, "ABC");
	assertThat(validator.validSelection("prompt")).isEqualTo("B");

	input = new Scanner("C");
	validator = new MenuValidator(input, "ABC");
	assertThat(validator.validSelection("prompt")).isEqualTo("C");
    }

    @Test
    @DisplayName("validSelection returns valid selection that's not in proper case with no trailing/leading white space")
    void return_valid_selection_not_in_proper_case() {
	Scanner input = new Scanner("a");
	MenuValidator validator = new MenuValidator(input, "ABC");

	assertThat(validator.validSelection("prompt")).isEqualTo("A");

	input = new Scanner("b");
	validator = new MenuValidator(input, "ABC");
	assertThat(validator.validSelection("prompt")).isEqualTo("B");

	input = new Scanner("c");
	validator = new MenuValidator(input, "ABC");
	assertThat(validator.validSelection("prompt")).isEqualTo("C");
    }

    @Test
    @DisplayName("validSelection returns valid selection that's not in proper case and has trailing/leading white space")
    void return_valid_selection_not_in_proper_case_with_trailing_leading_whitespace() {
	Scanner input = new Scanner("  a   ");
	MenuValidator validator = new MenuValidator(input, "ABC");

	assertThat(validator.validSelection("prompt")).isEqualTo("A");

	input = new Scanner("  b");
	validator = new MenuValidator(input, "ABC");
	assertThat(validator.validSelection("prompt")).isEqualTo("B");

	input = new Scanner("c   ");
	validator = new MenuValidator(input, "ABC");
	assertThat(validator.validSelection("prompt")).isEqualTo("C");
    }


    @Test
    @DisplayName("validSelection returns valid selection when first entry is only whitespace")
    void return_valid_selection_when_first_entry_is_only_whitespace() {
	Scanner input = new Scanner("  " + EOLN + "A");
	MenuValidator validator = new MenuValidator(input, "ABC");

	assertThat(validator.validSelection("prompt")).isEqualTo("A");
    }

    @Test
    @DisplayName("validSelection returns valid selection when first entry is more than one letter")
    void return_valid_selection_when_first_entry_is_more_than_one_letter() {
	Scanner input = new Scanner("aa" + EOLN + "A");
	MenuValidator validator = new MenuValidator(input, "ABC");

	assertThat(validator.validSelection("prompt")).isEqualTo("A");
    }

    @Test
    @DisplayName("validSelection returns valid selection when first entry is not an acceptable letter")
    void return_valid_selection_when_first_entry_is_not_acceptable_letter() {
	Scanner input = new Scanner("D" + EOLN + "B");
	MenuValidator validator = new MenuValidator(input, "ABC");

	assertThat(validator.validSelection("prompt")).isEqualTo("B");
    }

    @Test
    @DisplayName("validSelection returns last valid entry if given multiple crappy inputs")
    void return_final_valid_number_if_rest_crappy() {
	String crappyInput = "";
	crappyInput += EOLN;
	crappyInput += "woo!" + EOLN;
	crappyInput += "-12.2" + EOLN;
	crappyInput += "-100" + EOLN;
	crappyInput += "12121" + EOLN;
	crappyInput += "aA" + EOLN;
	crappyInput += "D" + EOLN;
	crappyInput += "B";
	Scanner input = new Scanner(crappyInput);
	MenuValidator validator = new MenuValidator(input, "ABC");

	assertThat(validator.validSelection("prompt")).isEqualTo("B");
    }
}
