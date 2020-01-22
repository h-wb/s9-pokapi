package gp.psw.controller;

import gp.psw.repository.PokemonRepository;
import gp.psw.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private PokemonRepository pokemonRepository;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Iterable<Pokemon> all = pokemonRepository.findAll();

        StringBuilder sb = new StringBuilder();

        sb.append("<table border=1>");
        all.forEach(p -> sb.append("<tr>" + p.getName() + "</tr>"));
        sb.append("</table>");

        return sb.toString();
    }
}
