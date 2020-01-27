package pokapi.helper.search;

import org.junit.Test;
import pokapi.entity.TypeEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SearchTypeTest {

    @Test
    public void searchScore() {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setName("CÔmbat");
        TypeEntity typeEntity1 = new TypeEntity();
        typeEntity1.setName("co   mbat");
        TypeEntity typeEntity2 = new TypeEntity();
        typeEntity2.setName("Cômbat");
        TypeEntity typeEntity3 = new TypeEntity();
        typeEntity3.setName("Combat");
        TypeEntity typeEntity4 = new TypeEntity();
        typeEntity4.setName("combat");
        TypeEntity typeEntity5 = new TypeEntity();
        typeEntity5.setName("Electrique");

        List<TypeEntity> typeEntityList = new ArrayList<>();
        typeEntityList.add(typeEntity);
        typeEntityList.add(typeEntity1);
        typeEntityList.add(typeEntity2);
        typeEntityList.add(typeEntity3);
        typeEntityList.add(typeEntity4);
        typeEntityList.add(typeEntity5);

        List<TypeEntity> results = SearchType.searchScore(typeEntityList, "Comb");
        assertEquals(5, results.size());
        assertEquals(typeEntity3.getName(), results.get(0).getName());
        assertEquals(typeEntity4.getName(), results.get(1).getName());
        assertEquals(typeEntity2.getName(), results.get(2).getName());
        assertEquals(typeEntity.getName(), results.get(3).getName());
        assertEquals(typeEntity1.getName(), results.get(4).getName());

        assertTrue(SearchType.searchScore(typeEntityList, null).isEmpty());
        assertTrue(SearchType.searchScore(typeEntityList, "").isEmpty());
        assertTrue(SearchType.searchScore(typeEntityList, "  ").isEmpty());
        assertTrue(SearchType.searchScore(null, "Test").isEmpty());
        assertTrue(SearchType.searchScore(new ArrayList<>(), "Test").isEmpty());
    }
}