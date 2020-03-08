import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Files;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.contentOf;

class HighScoresPersistenatorTests {

    private static final String DEFAULT_SAVE_FILE_NAME = "highscores.txt";
    private static final int DEFAULT_NUM_HIGH_SCORES = 3;


    public void deleteBeforeTest() throws IOException {
	Path filePath = Paths.get(DEFAULT_SAVE_FILE_NAME);
	java.nio.file.Files.deleteIfExists(filePath);
    }

    @AfterEach
    public void deleteAfterTest() throws IOException {
	Path filePath = Paths.get(DEFAULT_SAVE_FILE_NAME);
	java.nio.file.Files.deleteIfExists(filePath);
    }


    @Test
    @DisplayName("loading an empty save file gives you an empty HighScores")
    void loading_an_empty_save_file_gives_you_an_empty_HighScores() throws IOException {
	try {
	    java.nio.file.Files.createFile(Paths.get(DEFAULT_SAVE_FILE_NAME));
	    HighScoresPersistenator persistenator = new HighScoresPersistenator(DEFAULT_NUM_HIGH_SCORES, DEFAULT_SAVE_FILE_NAME);
	    HighScores scores = persistenator.load();
	    assertThat(scores.numScores()).isZero();
	} catch (FileNotFoundException ex) {
	    fail("Creating a valid saver should not cause an exception.");
	}
    }


    @Test
    @DisplayName("loading a file with one high score in it gives you the expected HighScores")
    void loading_a_file_with_one_high_score_gives_you_expected_HighScores() throws IOException {
	try {
	    Path saveFilePath = Paths.get(DEFAULT_SAVE_FILE_NAME);
	    ArrayList<String> fileContents = new ArrayList<>();
	    fileContents.add("JDP 2");
	    java.nio.file.Files.write(saveFilePath, fileContents);

	    HighScoresPersistenator persistenator = new HighScoresPersistenator(DEFAULT_NUM_HIGH_SCORES, DEFAULT_SAVE_FILE_NAME);
	    HighScores scores = persistenator.load();
	    assertThat(scores.ranking(1).initials()).isEqualTo("JDP");
	} catch (FileNotFoundException ex) {
	    fail("Creating a valid saver should not cause an exception.");
	}
    }

    @Test
    @DisplayName("loading a file with two high scores in it gives you the expected HighScores")
    void loading_a_file_with_two_high_scores_gives_you_expected_HighScores() throws IOException {
	try {
	    Path saveFilePath = Paths.get(DEFAULT_SAVE_FILE_NAME);
	    ArrayList<String> fileContents = new ArrayList<>();

	    fileContents.add("AAA 2");
	    fileContents.add("BBB 1");
	    java.nio.file.Files.write(saveFilePath, fileContents);

	    HighScoresPersistenator persistenator = new HighScoresPersistenator(DEFAULT_NUM_HIGH_SCORES, DEFAULT_SAVE_FILE_NAME);
	    HighScores scores = persistenator.load();
	    assertThat(scores.ranking(1).initials()).isEqualTo("AAA");
	    assertThat(scores.ranking(2).initials()).isEqualTo("BBB");
	} catch (FileNotFoundException ex) {
	    fail("Creating a valid saver should not cause an exception.");
	}
    }

    @Test
    @DisplayName("saving an empty high scores results in empty file")
    void saving_an_empty_HighScores_gives_an_empty_file() throws IOException {
	try {
	    HighScoresPersistenator persistenator = new HighScoresPersistenator(DEFAULT_NUM_HIGH_SCORES, DEFAULT_SAVE_FILE_NAME);
	    Path saveFilePath = Paths.get(DEFAULT_SAVE_FILE_NAME);
	    HighScores highScores = new HighScores(1);

	    persistenator.save(highScores);

	    assertThat(java.nio.file.Files.readAllLines(saveFilePath)).isEmpty();
	} catch (FileNotFoundException ex) {
	    fail("Creating a valid saver should not cause an exception.");
	}
    }

    @Test
    @DisplayName("saving one high scores results in file with that score")
    void saving_a_single_high_score_results_in_file_with_that_score() throws IOException {
	try {
	    HighScoresPersistenator persistenator = new HighScoresPersistenator(DEFAULT_NUM_HIGH_SCORES, DEFAULT_SAVE_FILE_NAME);
	    Path saveFilePath = Paths.get(DEFAULT_SAVE_FILE_NAME);
	    HighScores highScores = new HighScores(1);
	    highScores.add(new PlayerScore("AAA", 2));

	    persistenator.save(highScores);
	    File saveFile = new File(DEFAULT_SAVE_FILE_NAME);
	    assertThat(contentOf(saveFile)).containsSubsequence("AAA", "2");
	} catch (FileNotFoundException ex) {
	    fail("Creating a valid saver should not cause an exception.");
	}
    }


    @Test
    @DisplayName("saving three high scores results in file with those scores")
    void saving_three_high_scores_results_in_file_with_those_score() throws IOException {
	try {
	    HighScoresPersistenator persistenator = new HighScoresPersistenator(DEFAULT_NUM_HIGH_SCORES, DEFAULT_SAVE_FILE_NAME);
	    Path saveFilePath = Paths.get(DEFAULT_SAVE_FILE_NAME);
	    HighScores highScores = new HighScores(3);
	    highScores.add(new PlayerScore("AAA", 7));
	    highScores.add(new PlayerScore("BBB", 6));
	    highScores.add(new PlayerScore("CCC", 1));

	    persistenator.save(highScores);
	    File saveFile = new File(DEFAULT_SAVE_FILE_NAME);
	    assertThat(contentOf(saveFile))
		.containsSubsequence("AAA", "7")
		.containsSubsequence("BBB", "6")
		.containsSubsequence("CCC", "1");
	} catch (FileNotFoundException ex) {
	    fail("Creating a valid saver should not cause an exception.");
	}
    }

}
