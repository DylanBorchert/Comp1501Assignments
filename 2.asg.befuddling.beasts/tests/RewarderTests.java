import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RewarderTests {

    private static final int FIRST_REWARD = 5;
    private static final int SECOND_REWARD = 10;
    private static final int THIRD_REWARD = 15;

    private static final int FIRST_THRESHOLD = 1;
    private static final int SECOND_THRESHOLD = 4;
    private static final int THIRD_THRESHOLD = 8;

    private Rewarder rewarder;

    @BeforeEach
    public void setUp() {
	rewarder = new Rewarder();
	rewarder.setThresholdOne(FIRST_THRESHOLD, FIRST_REWARD);
	rewarder.setThresholdTwo(SECOND_THRESHOLD, SECOND_REWARD);
	rewarder.setThresholdThree(THIRD_THRESHOLD, THIRD_REWARD);
    }

    @Test
    @DisplayName("returns 0 if one under first threshold")
    void return_0_if_one_under_first_threshold() {
	assertThat(rewarder.reward(FIRST_THRESHOLD - 1)).isZero();
    }

    @Test
    @DisplayName("returns 0 if way under first threshold")
    void return_0_if_way_under_first_threshold() {
	assertThat(rewarder.reward(FIRST_THRESHOLD - 100)).isZero();
    }

    @Test
    @DisplayName("returns first reward if on first threshold")
    void return_first_reward_if_on_first_threshold() {
	assertThat(rewarder.reward(FIRST_THRESHOLD)).isEqualTo(FIRST_REWARD);
    }

    @Test
    @DisplayName("returns first reward if just under second threshold")
    void return_first_reward_if_just_under_second_threshold() {
	assertThat(rewarder.reward(SECOND_THRESHOLD - 1)).isEqualTo(FIRST_REWARD);
    }

    @Test
    @DisplayName("returns second reward if on second threshold")
    void return_second_reward_if_on_second_threshold() {
	assertThat(rewarder.reward(SECOND_THRESHOLD)).isEqualTo(SECOND_REWARD);
    }

    @Test
    @DisplayName("returns second reward if just under third threshold")
    void return_second_reward_if_just_under_third_threshold() {
	assertThat(rewarder.reward(THIRD_THRESHOLD - 1)).isEqualTo(SECOND_REWARD);
    }

    @Test
    @DisplayName("returns third reward if on third threshold")
    void return_third_reward_if_on_third_threshold() {
	assertThat(rewarder.reward(THIRD_THRESHOLD)).isEqualTo(THIRD_REWARD);
    }

    @Test
    @DisplayName("returns third reward if one over third threshold")
    void return_third_reward_if_one_over_third_threshold() {
	assertThat(rewarder.reward(THIRD_THRESHOLD + 1)).isEqualTo(THIRD_REWARD);
    }

    @Test
    @DisplayName("returns third reward if way over third threshold")
    void return_third_reward_if_way_over_third_threshold() {
	assertThat(rewarder.reward(THIRD_THRESHOLD + 100)).isEqualTo(THIRD_REWARD);
    }

}
