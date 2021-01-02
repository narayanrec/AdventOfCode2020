import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day4 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day4.txt";

        try(Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[] :: new);
            //System.out.println("input is " + Arrays.toString(input));
            int count = 0;
            for(int i=0; i<input.length; i++) {
                List<String> pass = new ArrayList<>();
                while(i < input.length && !input[i].trim().equals("")) {
                    //System.out.println("read line is "+ input[i] + "--");
                    pass.add(input[i]);
                    i++;
                }

                Passport passport = new Passport(pass.toArray(new String[pass.size()]));
                if(passport.isValid()) count++;
            }

            System.out.println("valid passport count is " + count);
        }
    }

}

class Passport {
    static List<String> validEyeColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    String issueYear;
    String countryId;
    String birthYear;
    String expirationYear;
    String height;
    String hairColor;
    String eyeColor;
    String passportId;

    Passport(String[] vals) {
        for(String val: vals) {
            String[] attrs = val.split("\\s");
            for(String attr: attrs) {
                String[] keyVal = attr.split(":");
                if(keyVal[0].equals("iyr")) this.issueYear = keyVal[1];
                else if(keyVal[0].equals("cid")) this.countryId = keyVal[1];
                else if(keyVal[0].equals("byr")) this.birthYear = keyVal[1];
                else if(keyVal[0].equals("eyr")) this.expirationYear = keyVal[1];
                else if(keyVal[0].equals("hgt")) this.height = keyVal[1];
                else if(keyVal[0].equals("hcl")) this.hairColor = keyVal[1];
                else if(keyVal[0].equals("ecl")) this.eyeColor = keyVal[1];
                else if(keyVal[0].equals("pid")) this.passportId = keyVal[1];
            }
        }
    }

    boolean isValid() {
        return  isValidIssueYear() &&  isValidBirthYear() && isValidExpirationYear() && isValidHeight()
                && isValidHairColor() && isValidEyeColor() && isValidPassportId();
    }

    boolean isValidIssueYear() {
        return this.issueYear != null && this.issueYear.length() == 4
                && Integer.valueOf(issueYear) >=2010 && Integer.valueOf(issueYear) <= 2020;
    }

    boolean isValidBirthYear() {
        return this.birthYear != null && this.birthYear.length() == 4
                && Integer.valueOf(birthYear) >=1920 && Integer.valueOf(birthYear) <= 2002;
    }

    boolean isValidExpirationYear() {
        return this.expirationYear != null && this.expirationYear.length() == 4
                && Integer.valueOf(expirationYear) >=2020 && Integer.valueOf(expirationYear) <= 2030;
    }

    boolean isValidHeight() {
        if(this.height != null) {
            int ht = Integer.valueOf(this.height.substring(0, height.length()-2));
            //System.out.println("the height is " +ht);
            if(this.height.endsWith("cm")) return ht >= 150 && ht <= 193;
            else if(this.height.endsWith("in")) return ht >= 59 && ht <= 76;
            else return false;
        }
        return false;
    }

    boolean isValidHairColor() {
        if(this.hairColor != null) {
            return hairColor.length() == 7 && hairColor.charAt(0) == '#' && hairColor.substring(1).matches("[0-9a-f]+");
        }
        return false;
    }

    boolean isValidEyeColor() {
        return this.eyeColor != null && Passport.validEyeColors.contains(eyeColor);
    }

    boolean isValidPassportId() {
        return this.passportId != null && this.passportId.length() == 9;
    }


}
