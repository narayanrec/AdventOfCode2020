import com.sun.tools.javac.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Day19 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day19.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }

    static void part1(String[] input) {
        List<Rule> rules = new ArrayList<>();
        List<String> messages = new ArrayList<>();
        for (int i=0; i<input.length; i++) {
            while(!input[i].equals("")) {
                rules.add(new Rule(input[i]));
                i++;
            }
            i++;
            while(i<input.length) {
                messages.add(input[i]);
                i++;
            }
        }
        Rule[] orderedRules = new Rule[rules.size()];

        for (int i = 0; i < rules.size(); i++) {
            orderedRules[rules.get(i).index] = rules.get(i);
        }

        List<String> combinations = createRuleCombinations(orderedRules[0], orderedRules);
        int count = 0;
        for(String message: messages) {
            if(combinations.contains(message)) count++;
        }
        System.out.println("# of messages matching rule 0 is " + count);
    }

    static void part2(String[] input) {
        List<Rule> rules = new ArrayList<>();
        List<String> messages = new ArrayList<>();
        for (int i=0; i<input.length; i++) {
            while(!input[i].equals("")) {
                rules.add(new Rule(input[i]));
                i++;
            }
            i++;
            while(i<input.length) {
                messages.add(input[i]);
                i++;
            }
        }
        Rule[] orderedRules = new Rule[rules.size()];

        for (int i = 0; i < rules.size(); i++) {
            orderedRules[rules.get(i).index] = rules.get(i);
        }

        List<String> combinations42 = createRuleCombinations(orderedRules[42], orderedRules);
        List<String> combinations31 = createRuleCombinations(orderedRules[31], orderedRules);
        int count = 0;
        for (String message : messages) {
            String part1 = message.substring(0, 8);
            String part2 = message.substring(8, 16);
            String lastPart = message.substring(message.length()-8);
            if(!combinations42.contains(part1) || !combinations42.contains(part2)) continue;
            if(!combinations31.contains(lastPart)) continue;

            String middlePart = message.substring(16, message.length() -8);
            if(middlePart.length() %8 != 0) continue;
            int i= 0;
            int j= middlePart.length();
            int count31 = 0;
            int count42 = 0;
            while(i<j) {
                String part = middlePart.substring(i, i+8);
                if(combinations42.contains(part)) count42++;
                else break;
                i+=8;
            }
            while (i<j) {
                String part = middlePart.substring(i, i+8);
                if(combinations31.contains(part)) count31++;
                else break;
                i+=8;
            }
            if(i <j || count31 > count42) continue;

            count++;

        }
        System.out.println("# of messages matching rule 0 is " + count);
    }

    static List<String> createRuleCombinations(Rule rule, Rule[] orderedRules) {
        if(rule.dependentRules.size() == 0) return Arrays.asList(rule.val);
        List<String> ruleVals = new ArrayList<>();

        for (int i = 0; i < rule.dependentRules.size(); i++) {
            List<String> givenRuleVals = new ArrayList<>();
            givenRuleVals.add("");
            int[] dependentRule = rule.dependentRules.get(i);
            for(int index : dependentRule) {
                List<String> childCombs = createRuleCombinations(orderedRules[index], orderedRules);
                int size = givenRuleVals.size();
                for (int j = 0; j < size; j++) {
                    String val = givenRuleVals.remove(0);
                    for(String childComb: childCombs) {
                        givenRuleVals.add(val + childComb);
                    }
                }
            }
            ruleVals.addAll(givenRuleVals);
        }
        return ruleVals;
    }

    static class Rule {
        int index;
        List<int[]> dependentRules = new ArrayList<>();
        String val;
        public Rule(String text) {
            String[] parts = text.split(":");
            this.index = Integer.valueOf(parts[0]);
            String[] parts1 = new String[1];
            if(parts[1].indexOf('|') >= 0) {
                parts1 = parts[1].split("\\|");
            }else parts1[0] = parts[1];

            for(String part : parts1) {
                String[] indexes = part.trim().split("\\s");
                if(Character.isDigit(indexes[0].charAt(0))) {
                    int[] rulePart = new int[indexes.length];
                    for (int i = 0; i < indexes.length; i++) {
                        rulePart[i] = Integer.valueOf(indexes[i]);
                    }
                    this.dependentRules.add(rulePart);
                } else {
                    this.val = indexes[0].replaceAll("\"", "");
                }

            }

        }

    }

}
