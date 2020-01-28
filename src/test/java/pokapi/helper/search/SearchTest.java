package pokapi.helper.search;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchTest {

    @Test
    public void getScore() {
        assertEquals("Test trouvé", 12, Search.getScore("Bulbizarre", "Bul"));
        assertEquals("Test trouvé", 12, Search.getScore("Bulbizarre", "zar"));
        assertEquals("Test trouvé espace", 11, Search.getScore("Bulbizarre", "ar r"));
        assertEquals("Test trouvé accent", 14, Search.getScore("Bulbizarre", "arré"));
        assertEquals("Test trouvé sans majuscule", 11, Search.getScore("Bulbizarre", "bul"));
        assertEquals("Test trouvé accent majuscule", 8, Search.getScore("Bulbizarre", "bÛl"));
        assertEquals("Test trouvé espaces multiples", 3, Search.getScore("Bulbizarre", "b    u"));
        assertEquals("Test non trouvé différent", -10, Search.getScore("Bulbizarre", "Roucoul"));
        assertEquals("Test non trouvé null", -10, Search.getScore(null, null));
        assertEquals("Test non trouvé vide", -10, Search.getScore("", ""));
        assertEquals("Test non trouvé espace", -10, Search.getScore("  ", "  "));
    }

    @Test
    public void getScoreString() {
        assertEquals("Test trouvé", 12, Search.getScoreString("Bulbizarre", "Bul", 0));
        assertEquals("Test trouvé sans majuscule", 11, Search.getScoreString("Bulbizarre", "bul", 0));
        assertEquals("Test trouvé espace sans majuscule", 10, Search.getScoreString("Bulbizarre", " bul", 0));
        assertEquals("Test trouvé accent", 6, Search.getScoreString("Sulfura", "Sû", 0));
        assertEquals("Test trouvé accent majuscule", 5, Search.getScoreString("Sulfura", "SÛ", 0));
        assertEquals("Test non trouvé espace", -10, Search.getScoreString("Sulfura", " ", 0));
        assertEquals("Test non trouvé différent", -10, Search.getScoreString("Sulfura", "Bul", 0));
        assertEquals("Test non trouvé null", -10, Search.getScoreString(null, null, 0));
        assertEquals("Test non trouvé vide", -10, Search.getScoreString("", "", 0));
        assertEquals("Test non trouvé espace", -10, Search.getScoreString("  ", "  ", 0));
    }

    @Test
    public void getScoreLetter() {
        assertEquals("Test espace", -1, Search.getScoreLetter(' ', 'a'));
        assertEquals("Test espace inversé", -1, Search.getScoreLetter('a', ' '));

        assertEquals("Test identique", 4, Search.getScoreLetter('a', 'a'));

        assertEquals("Test identique majuscule", 3, Search.getScoreLetter('A', 'a'));
        assertEquals("Test identique majuscule inversé", 3, Search.getScoreLetter('a', 'A'));

        assertEquals("Test identique accent", 2, Search.getScoreLetter('é', 'e'));
        assertEquals("Test identique accent inversé", 2, Search.getScoreLetter('e', 'é'));

        assertEquals("Test identique accent majuscule", 1, Search.getScoreLetter('É', 'e'));
        assertEquals("Test identique accent majuscule inversé", 1, Search.getScoreLetter('e', 'É'));

        assertEquals("Test différent", -10, Search.getScoreLetter('a', 'e'));
    }

    @Test
    public void deAccent() {
        final String accents = "È,É,Ê,Ë,Û,Ù,Ï,Î,À,Â,Ô,è,é,ê,ë,û,ù,ï,î,à,â,ô,Ç,ç,Ã,ã,Õ,õ";
        final String expected = "E,E,E,E,U,U,I,I,A,A,O,e,e,e,e,u,u,i,i,a,a,o,C,c,A,a,O,o";

        assertEquals("Test suppression des accents", expected, Search.deAccent(accents));

        final String accents2 = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
        final String expected2 = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";

        assertEquals("Test large suppression des accents", expected2, Search.deAccent(accents2));


    }

    @Test
    public void currentCharEqualsSpace() {
        assertTrue("Test espace score avec espace", Search.currentCharEqualsSpace(' ', -1));
        assertFalse("Test espace score sans espace", Search.currentCharEqualsSpace(' ', 4));
        assertFalse("Test sans espace score avec espace", Search.currentCharEqualsSpace('a', -1));
        assertFalse("Test sans espace score sans espace", Search.currentCharEqualsSpace('a', 4));
    }
}