package pokapi.helper;

import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Search {

    private static final Integer NOT_FOUND = -10;

    private Search() {
    }

    /**
     * Méthode permettant de lancer le calcul du score de la recherche de l'expression sur le nom donné de l'entité
     *
     * @param entitiesScored la liste des entités ayant un score
     * @param entity         entité sur laquelle la recherche a lieu
     * @param name           nom de l'entité donnée
     * @param exp            expression recherchée
     * @param <T>            type de l'entité
     */
    static <T> void searchScoreEntity(List<Map.Entry<T, Integer>> entitiesScored, T entity, String name, String exp) {
        int score = getScore(name.trim(), exp.trim());
        if (score != NOT_FOUND) {
            entitiesScored.add(new AbstractMap.SimpleImmutableEntry<>(entity, score));
        }
    }

    /**
     * Méthode calculant le score total de la recherche de l'expression sur le nom de l'entité
     *
     * @param entityName nom de l'entité sur laquelle la recherche a lieu
     * @param exp        expression recherchée
     * @return score de la recherche
     */
    private static int getScore(String entityName, String exp) {
        int maxScore = NOT_FOUND;

        if (StringUtils.isBlank(entityName) || StringUtils.isBlank(exp)) {
            return maxScore;
        }

        while (entityName.length() >= exp.length()) {
            maxScore = Math.max(getScoreString(entityName, exp, 0), maxScore);
            entityName = entityName.substring(1);
        }

        return maxScore;
    }

    /**
     * Méthode récursive terminale calculant le score obtenu lors de la recherche d'une expression sur le nom de l'entité
     *
     * @param entityName nom de l'entité sur laquelle la recherche a lieu
     * @param exp        expression recherchée
     * @param score      score accumulé
     * @return score calculé ajouté au score accumulé
     */
    private static int getScoreString(String entityName, String exp, int score) {
        if ((StringUtils.isBlank(entityName) || StringUtils.isBlank(exp)) && score == 0) {
            return NOT_FOUND;
        }

        if (exp.length() == 0) {
            return score;
        } else {
            int result = getScoreLetter(entityName.charAt(0), exp.charAt(0));
            if (result == NOT_FOUND) {
                return result;
            } else {
                return getScoreString(
                        entityName.substring(currentCharEqualsSpace(exp.charAt(0), result) ? 0 : 1),
                        exp.substring(currentCharEqualsSpace(entityName.charAt(0), result) ? 0 : 1),
                        score + result);
            }
        }
    }

    /**
     * Méthode calculant le score obtenu lors de la comparaison de deux caractères
     *
     * @param entityNameLetter caractère du nom de l'entité sur laquelle la recherche a lieu
     * @param expLetter        caractère de l'expression recherchée
     * @return score calculé
     */
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
            } else if (deAccentExpLetter.equalsIgnoreCase(deAccentEntityNameLetter)) {
                return 1;
            } else {
                return NOT_FOUND;
            }
        }
    }

    /**
     * Méthode permettant de normaliser les caractères spéciaux ou accentués dans une chaîne de caractères
     *
     * @param str chaîne de caractère à normaliser
     * @return chaîne de caractère normalisée
     */
    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    /**
     * Méthode permettant de savoir si le caractère donné est un espace et si le dernier score correspond à une présence d'un espace
     *
     * @param charToTest      caractère à tester
     * @param lastScoreLetter dernier score
     * @return booléen représentant la présence d'un espace
     */
    private static Boolean currentCharEqualsSpace(char charToTest, int lastScoreLetter) {
        return lastScoreLetter == -1 && Character.isWhitespace(charToTest);
    }
}
