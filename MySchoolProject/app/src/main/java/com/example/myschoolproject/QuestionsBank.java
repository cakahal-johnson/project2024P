package com.example.myschoolproject;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    private static List<QuestionsList> physQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();

        // Create object of questionsList class and pass a questions along with options and answer 38:14
        final QuestionsList question1 = new QuestionsList("The inner diameter of a test tube can be measured accurately using a", "micrometer screw gauge", "pair of dividers", "metre rule", "pair of vernier calipers", "pair of vernier calipers", "");
        final QuestionsList question2 = new QuestionsList("Two bodies have masses in the ratio of 3:1. They experience forces that impart to them acceleration in the ratio 2:9 respectively. Find the ratio of forces the masses experienced.", "1 : 4", "2 : 1", "2 : 3", "2 : 5", "2 : 3", "");
        final QuestionsList question3 = new QuestionsList("Total internal reflection occurs when light moves from", "a dense medium to a less dense medium", "air to water", "water to glass", "a less dense to a dense medium", "water to glass", "");
        final QuestionsList question4 = new QuestionsList("A rope is being used to pull a mass of 10kg vertically upward. Determine the tension in the rope if, starting from rest, the mass acquires a velocity of 4ms-1 in 8s [g = 10ms-2]", "5N", "50N", "105N", "95N", "105N", "");
        final QuestionsList question5 = new QuestionsList("If the force and the velocity on a system are each reduced simultaneously by half, the power of the system is", "doubled", "reduced to a quarter", "reduced by half", "constant", "doubled", "");
        final QuestionsList question6 = new QuestionsList("A semiconductor diode is used in rectifying alternating current into direct current mainly because of it.", "is non-linear", "allows current to flow in either direction", "allows current to flow only in one direction", "offers a high input resistance", "allows current to flow in either direction", "");


        // add all questions to List <QuestionsList>
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;

    }

//    ============================ chem question =========================

    private static List<QuestionsList> chemQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();

        // Create object of questionsList class and pass a questions along with options and answer 38:14
        final QuestionsList question1 = new QuestionsList("Which of the following compound is aromatic?", "Benzene", "Cyclobutane", " Cyclopentane", "Hexane", "Hexane", "");
        final QuestionsList question2 = new QuestionsList("Which of the following does not affect the rate of a chemical reaction?", "Addition or presence of a catalyst", "Size of the reacting particles", "The enthalpy change for the reaction", "Concentration of the reactants", "The enthalpy change for the reaction", "");
        final QuestionsList question3 = new QuestionsList("Given that 32.0g sulphur contains 6.02 x 10 23 sulphur atoms how many atoms are there in 2.70g of aluminium? {Al = 27, S =32}", "6.02 x 10 23", "3.01 x 1022", "6.02 x 1022", "5. 08 x 1022", "6.02 x 1022", "");
        final QuestionsList question4 = new QuestionsList("The empirical formula of hydrocarbon containing 0. 12 mole of carbon and 0.36 mole of hydrogen is", "CH2", "CH3", " C2H2", "C2H4", "CH3", "");
        final QuestionsList question5 = new QuestionsList("A salt which loses mass when exposed to air is", "hygroscopic", "deliquescent", "efflorescent", "fluorescent", "efflorescent", "");
        final QuestionsList question6 = new QuestionsList("The following are uses of radioactive isotops except for", "determining equilibrum positions", "tracing reaction paths", "dating elements", "radiography", "determining equilibrum positions", "");


        // add all questions to List <QuestionsList>
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;

    }


    //    ============================ Maths question =========================

    private static List<QuestionsList> mathQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();

        // Create object of questionsList class and pass a questions along with options and answer 38:14
        final QuestionsList question1 = new QuestionsList("Choose the option that best conveys the meaning of the underlined portion in the following sentence;\n" +
                "His jail terms were to run concurrently", "simultaneously", "uniformly", "laboriously", "consecutively", "simultaneously", "");
        final QuestionsList question2 = new QuestionsList("Choose the option that best conveys the meaning of the underlined portion in the following sentence;\n" +
                "The bill has to wait as we are now insolvent", "overworked", "bankrupt", "unsettled", "insoluble", "bankrupt", "");
        final QuestionsList question3 = new QuestionsList("Choose the option that best conveys the meaning of the underlined portion in the following sentence;\n" +
                "\n" +
                "All his plans fell through", "failed", "were accomplished", "had to be reviewed", "were rejected", "failed", "");
        final QuestionsList question4 = new QuestionsList("Choose the option that best conveys the meaning of the underlined portion in the following sentence;\n" +
                "The balance sheet at the end of the business year shows that we broke even", "lost heavily", "made profit", "neither lost nor gained", "had no money to continue business", "neither lost nor gained", "");
        final QuestionsList question5 = new QuestionsList("Choose the option that best conveys the meaning of the underlined portion in the following sentence;\n" +
                "\n" +
                "He was appointed specifically to put the recruits through", "assign them to work", "train them", "discipline them", "assist them at work", "train them", "");
        final QuestionsList question6 = new QuestionsList("If my father had not arrived, I would have starved. This sentence means", "My father did arrive and I didn't starve", "I had to starve because my father didn't come", "I had starved before my father arrived", " I should have starved but I didn't", "My father did arrive and I didn't starve", "");


        // add all questions to List <QuestionsList>
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;

    }

    //    ============================ eng question =========================

    private static List<QuestionsList> engQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();

        // Create object of questionsList class and pass a questions along with options and answer 38:14
        final QuestionsList question1 = new QuestionsList("A frustrum of pyramid with square base has its upper and lower sections as squares of sizes 2m and 5m respectively and the distance between them 6m. Find the height of the pyramid from which the frustrum was obtained.", "8.0m", "8.4m", "10.0m", "9.0m", "10.0m", "");
        final QuestionsList question2 = new QuestionsList("P is a point on one side of the straight line UV and P moves in the same direction as UV. If the straight line ST is on the locus of P and angle VUS = 60°, find angle UST.", "310deg", "130deg", "80deg", "50deg", "130deg", "");
        final QuestionsList question3 = new QuestionsList("An equilateral triangle of side √3cm is inscribed in a circle. Find the radius of the circle. ", "1/3 cm", "1 cm", "2 cm", "3 cm", "1 cm", "");
        final QuestionsList question4 = new QuestionsList("if P and Q are fixed points and X is a point which moves so that XP = XQ, the locus of X is", "A straight line", "a circle", "the bisector of angle PXQ", "the bisector of angle PXQ", "the bisector of angle PXQ", "");
        final QuestionsList question5 = new QuestionsList("In a regular polygon, each interior angle doubles its corresponding exterior angle. Find the number of sides of the polygon.", "8", "6", "4", "3", "6", "");
        final QuestionsList question6 = new QuestionsList("X and Y are two events. The probability of X or Y is 0.7 and that of X is 0.4. If X and Y are independent, find the probability of Y.", "0.30", "0.50", "0.57", "1.80", "0.30", "");


        // add all questions to List <QuestionsList>
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;

    }

    public static List<QuestionsList> getQuestions(String selectedTopicName){
        switch (selectedTopicName){
            case "phys":
                return physQuestions();

            case "chem":
                return chemQuestions();

            case "eng":
                return engQuestions();

            default:
                return mathQuestions();
        }
    }
}
