ASSIGNMENT_NAME := Main

LIB_LOCATION := /users/library/csis/libs/comp1501-share

JUNIT_LOCATION := ${LIB_LOCATION}/jars/junit-platform-console-standalone-1.3.0-M1.jar

ASSERTJ_LOCATION := $(LIB_LOCATION)/jars/assertj-core-3.10.0.jar

CHECKSTYLE_LOCATION := $(LIB_LOCATION)/jars/checkstyle-8.11-all.jar
CHECKSTYLE_SETTINGS_LOCATION := $(LIB_LOCATION)/checkstyle-settings/1501_checks.xml

PMD_LOCATION := $(LIB_LOCATION)/pmd-bin-6.5.0/bin/
PMD_SETTINGS_LOCATION := $(LIB_LOCATION)/pmd-settings/1501_pmd_rules.xml

JSON_SIMPLE_LOCATION := $(LIB_LOCATION)/jars/json-simple-1.1.1.jar
RESTLET_LOCATION := $(LIB_LOCATION)/jars/org.restlet-2.3.12.jar

default: clean
	javac -d bin  -cp src src/*.java

tests: clean
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/*.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --scan-class-path --details tree

style:
	@echo CHECKSTYLE REPORT
	@java -jar $(CHECKSTYLE_LOCATION) -c $(CHECKSTYLE_SETTINGS_LOCATION) src/*.java; true
	@echo
	@echo PMD REPORT
	@$(PMD_LOCATION)/run.sh pmd -d src -f textcolor -R $(PMD_SETTINGS_LOCATION) -no-cache

all: tests style

beastcard: clean
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/BeastCardTests.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --select-class BeastCardTests --details tree

betweenvalidator:
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/BetweenValidatorTests.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --select-class BetweenValidatorTests --details tree

colorpool:
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/ColorPoolTests.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --select-class ColorPoolTests --details tree

colorpoolview:
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/ColorPoolViewTests.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --select-class ColorPoolViewTests --details tree

currentscoreview:
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/CurrentScoreViewTests.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --select-class CurrentScoreViewTests --details tree

finalscoreview:
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/FinalScoreViewTests.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --select-class FinalScoreViewTests --details tree

menuvalidator:
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/MenuValidatorTests.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --select-class MenuValidatorTests --details tree

rewarder:
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/RewarderTests.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --select-class RewarderTests --details tree

scorer:
	mkdir -p bin
	javac -d bin -cp src:tests:$(ASSERTJ_LOCATION):$(JUNIT_LOCATION) tests/ScorerTests.java src/$(ASSIGNMENT_NAME).java
	java -jar $(JUNIT_LOCATION) --class-path bin:tests:$(ASSERTJ_LOCATION) --select-class ScorerTests --details tree



run: default
	java -cp bin Main
#	java -cp bin $(ASSIGNMENT_NAME)

clean:
	$(RM) *~ *.class bin/* src/*~ src/*.class tests/*~ tests/*.class tests/data/*~ documentation/*~
