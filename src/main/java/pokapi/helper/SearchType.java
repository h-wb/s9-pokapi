package pokapi.helper;

import org.apache.commons.lang3.StringUtils;
import pokapi.entity.TypeEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchType {
    private SearchType() {
    }

    /**
     * Méthode permettant d'effectuer une recherche scorée sur une liste de types de Pokémons
     *
     * @param allTypes liste des types sur laquelle la recherche a lieu
     * @param exp      expression recherchée
     * @return liste des types de Pokémons correspondants à la recherche, classée dans l'ordre descendant du score obtenu
     */
    public static List<TypeEntity> searchScore(List<TypeEntity> allTypes, String exp) {
        if (StringUtils.isBlank(exp) || allTypes == null || allTypes.isEmpty()) {
            return new ArrayList<>();
        }

        List<Map.Entry<TypeEntity, Integer>> typesScored = new ArrayList<>();
        allTypes.forEach(typeEntity -> {
            Search.searchScoreEntity(typesScored, typeEntity, typeEntity.getName(), exp);
        });

        typesScored.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        return typesScored.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
