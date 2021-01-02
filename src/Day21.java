import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day21 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day21.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            //part2(input);
        }
    }

    static void part1(String[] input) {
        List<Food> foods = new ArrayList<>();
        //map of allergy -> list of ingredients
        Map<String, List<String>> map;
        Map<String, String> ingrAllergiesMap = new HashMap<>();
        Map<String, Integer> mapIngrCount = new HashMap<>();
        for(String in: input) {
            String[] ingredients = in.substring(0, in.indexOf('(')).trim().split("\\s");
            String[] allergens = in.substring(in.indexOf("(")+10, in.indexOf(")")).split(",");
            Food food = new Food(ingredients, allergens);
            foods.add(food);
            for(String ingr: ingredients) mapIngrCount.put(ingr, mapIngrCount.getOrDefault(ingr, 0)+1);
        }
        map = getAllPossibleMatchesMap(foods);
        while(!map.isEmpty()) {
            Iterator<Map.Entry<String, List<String>>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, List<String>> entry = iterator.next();

                if(entry.getValue().size() == 1) {
                    String ingr = entry.getValue().get(0);
                    ingrAllergiesMap.put(ingr, entry.getKey());
                    for(List<String> list : map.values()) {
                        list.remove(ingr);
                    }
                    iterator.remove();
                }
            }
        }

        int count = 0;
        for(String ingr: mapIngrCount.keySet()) {
            if(!ingrAllergiesMap.containsKey(ingr))
                count+=mapIngrCount.get(ingr);

        }

        System.out.println("count is "+count);
        List<Map.Entry<String, String>> list = new ArrayList<>(ingrAllergiesMap.entrySet());
        list.stream().sorted(Comparator.comparing(Map.Entry::getValue)).forEach(it -> System.out.print(it.getKey()+","));
    }

    private static Map<String, List<String>> getAllPossibleMatchesMap(List<Food> foods) {
        Map<String, List<String>> allergyPossibleMatches = new HashMap<>();
            for(Food food: foods) {
                for(String allergy: food.allergens) {
                    if(allergyPossibleMatches.containsKey(allergy)) {
                        allergyPossibleMatches.get(allergy).retainAll(food.ingredients);
                    } else {
                        allergyPossibleMatches.put(allergy, new ArrayList<>(food.ingredients));
                    }

                }
            }

        return allergyPossibleMatches;
    }

    static class Food{
        List<String> ingredients;
        List<String> allergens;

        public Food(String[] ingr, String[] allg) {
            ingredients = new ArrayList<>();
            allergens = new ArrayList<>();
            for(String in: ingr) ingredients.add(in.trim());
            for(String ag: allg) allergens.add(ag.trim());
        }
    }


}
