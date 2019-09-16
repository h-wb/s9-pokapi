package gp.psw.controller;

import gp.psw.dao.PokemonDAO;
import gp.psw.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PokemonController {

    @Autowired
    private PokemonDAO pokemonDAO;

    @RequestMapping(value = "/pokemon/{Id}", method = RequestMethod.GET)
    @ResponseBody
    Pokemon getPokemonById(@PathVariable final Long Id) {
        return pokemonDAO.getById(Id);
    }

    @RequestMapping(value = "/pokemon/type/{Type}", method = RequestMethod.GET)
    @ResponseBody
    List<Pokemon> getPokemonByType(@PathVariable final String Type) {
        return pokemonDAO.findByType(Type);
    }
}
