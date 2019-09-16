package gp.psw;

import gp.psw.dao.PokemonDAO;
import gp.psw.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private PokemonDAO pokemonDAO;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Iterable<Pokemon> all = pokemonDAO.findAll();

        StringBuilder sb = new StringBuilder();

        all.forEach(p -> sb.append(p.getName() + "<br>"));

        return sb.toString();
    }
}
