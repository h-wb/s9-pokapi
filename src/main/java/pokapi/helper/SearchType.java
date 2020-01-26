package pokapi.helper;

import org.apache.commons.lang3.StringUtils;
import pokapi.entity.TypeEntity;

import java.util.*;
import java.util.stream.Collectors;

public class SearchType {
    private SearchType() {
    }

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
