import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ColorPoolViewTests {

    @Test
    @DisplayName("view returns number of red, green, and blue colors in pool with capital R, G, B and 2 digits with leading 0s")
    void view_returns_rgb_with_proper_labels_and_2_digits_leading_0s() {
	// the fake color pool always return 3 red, 7 green, and 81 blue
	ColorPool colorPool = new FakeColorPool();
	ColorPoolView colorPoolView = new ColorPoolView(colorPool);

	assertThat(colorPoolView.view()).contains("R03");
	assertThat(colorPoolView.view()).contains("G07");
	assertThat(colorPoolView.view()).contains("B81");
    }

}
