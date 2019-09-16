package gp.psw.init;

import gp.psw.dao.PokemonDAO;
import gp.psw.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {

    private PokemonDAO pokemonDAO;

    @Autowired
    public DataInit(PokemonDAO pokemonDAO){
        this.pokemonDAO = pokemonDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = pokemonDAO.count();

        if (count == 0){
            Pokemon p1 = new Pokemon();
            p1.setName("Bulbizarre");
            p1.setType("Plante");

            Pokemon p2 = new Pokemon();
            p2.setName("Salamèche");
            p2.setType("Feu");

            Pokemon p3 = new Pokemon();
            p3.setName("Carapuce");
            p3.setType("Eau");

            pokemonDAO.save(p1);
            pokemonDAO.save(p2);
            pokemonDAO.save(p3);
        }
    }
}
