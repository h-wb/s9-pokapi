package pokapi.helper;

import org.apache.commons.lang3.StringUtils;
import pokapi.entity.TypeEntity;

import java.text.Normalizer;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Search {

    static final Integer notFound = -10;

    private Search() {
    }

    static <T> void searchScoreEntity(List<Map.Entry<T, Integer>> entitiesScored, T entity, String name, String exp) {
        int score = getScore(name, exp);
        if (score != notFound) {
            entitiesScored.add(new AbstractMap.SimpleImmutableEntry<>(entity, score));
        }
    }

    static int getScore(String entityName, String exp) {
        int maxScore = notFound;

        if (StringUtils.isBlank(entityName) || StringUtils.isBlank(exp)) {
            return maxScore;
        }

        while (entityName.length() >= exp.length()) {
            maxScore = Math.max(getScoreString(entityName, exp, 0), maxScore);
            entityName = entityName.substring(1);
        }

        return maxScore;
    }

    private static int getScoreString(String entityName, String exp, int score) {
        if ((StringUtils.isBlank(entityName) || StringUtils.isBlank(exp)) && score == 0) {
            return notFound;
        }

        if (exp.length() == 0) {
            return score;
        } else {
            int result = getScoreLetter(entityName.charAt(0), exp.charAt(0));
            if (result == notFound) {
                return result;
            } else {
                return getScoreString(
                        entityName.substring(currentCharEqualsSpace(exp.charAt(0), result) ? 0 : 1),
                        exp.substring(currentCharEqualsSpace(entityName.charAt(0), result) ? 0 : 1),
                        score + result);
            }
        }
    }

    private static int getScoreLetter(char entityNameLetter, char expLetter) {
        if (Character.isWhitespace(expLetter) || Character.isWhitespace(entityNameLetter)) {
            return -1;
        } else if (expLetter == entityNameLetter) {
            return 4;
        } else if (Character.toLowerCase(expLetter) == Character.toLowerCase(entityNameLetter)) {
            return 3;
        } else {
            String deAccentExpLetter = deAccent(String.valueOf(expLetter));
            String deAccentEntityNameLetter = deAccent(String.valueOf(entityNameLetter));

            if (deAccentExpLetter.equals(deAccentEntityNameLetter)) {
                return 2;
            } else if (deAccentExpLetter.toLowerCase().equals(deAccentEntityNameLetter.toLowerCase())) {
                return 1;
            } else {
                return notFound;
            }
        }
    }

    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    private static Boolean currentCharEqualsSpace(char charToTest, int lastScoreLetter) {
        return lastScoreLetter == -1 && Character.isWhitespace(charToTest);
    }
}
