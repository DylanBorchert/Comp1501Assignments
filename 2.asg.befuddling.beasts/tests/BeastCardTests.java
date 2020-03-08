import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BeastCardTests {

    private BeastCard card;

    @BeforeEach
    public void setUp() {
	card = new BeastCard("flying squirrel", "bgr");
    }


    @Test
    @DisplayName("a BeastCard can tell you its name")
    void new_beast_cards_has_a_name() {
	assertThat(card.name()).isEqualTo("flying squirrel");
    }

    @Test
    @DisplayName("a BeastCard can tell you its cost, and its always in caps")
    void new_beast_cards_has_a_upper_case_cost() {
	assertThat(card.cost()).isEqualTo("BGR");
    }

}
