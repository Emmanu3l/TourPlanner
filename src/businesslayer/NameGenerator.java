package businesslayer;

import java.util.Random;

public class NameGenerator {

    public static String GenerateName(int nameLength) {
        // alternatively: just use japanese syllables and arrange them randomly
        // for using the original method: timestamp 1:47:43 at https://youtu.be/TDvtRbvp-mw

        // simple version of the japanese syllabary (more complex combinations, such as those using -tsu have been left out)
        // source http://www2.latech.edu/~sajones/Foreign%20Languages/Japanese%20Syllables.htm
        // TODO: possibly remove single and three letter syllables so you can guarantee a certain length
        String[] syllables = {"a", "i", "u", "e", "o",
                            "ka", "ki", "ku", "ke", "ko",
                            "ta", "chi", "tsu", "te", "to",
                            "sa", "shi", "su", "se", "so",
                            "na", "ni", "nu", "ne", "no", "n",
                            "ha", "hi", "fu", "he", "ho",
                            "ma", "mi", "mu", "me", "mo",
                            "ya", "yu", "yo",
                            "ra", "ri", "ru", "re", "ro",
                            "wa", "wo"};
        String name = "";

        // mindestens 2 silben
        name += getRandom(syllables);
        name += getRandom(syllables);

        while (name.length() < nameLength) {
            name += getRandom(syllables);
        }

        return name;
    }

    private static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

}
