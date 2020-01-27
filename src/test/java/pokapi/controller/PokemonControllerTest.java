package pokapi.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import pokapi.dto.PokemonDTO;
import pokapi.entity.EstTypeEntity;
import pokapi.entity.PokemonEntity;
import pokapi.entity.TypeEntity;
import pokapi.exception.ResourceNotFoundException;
import pokapi.repository.EstTypeRepository;
import pokapi.repository.PokemonRepository;
import pokapi.repository.TypeRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PokemonControllerTest {

    @InjectMocks
    private PokemonController pokemonController;

    @Mock
    private PokemonRepository pokemonRepository;
    @Mock
    private TypeRepository typeRepository;
    @Mock
    private EstTypeRepository estTypeRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllPokemons() {
        List<PokemonEntity> pokemonEntities = new ArrayList<>();

        when(pokemonRepository.findAll()).thenReturn(pokemonEntities);

        List<PokemonEntity> returned = pokemonController.getAllPokemons();

        verify(pokemonRepository, times(1)).findAll();

        verifyNoMoreInteractions(pokemonRepository);

        assertEquals(pokemonEntities, returned);
    }

    @Test
    public void getAllPokemonsByPokedexId() {
        List<PokemonEntity> pokemonEntities = new ArrayList<>();

        when(pokemonRepository.findAll()).thenReturn(pokemonEntities);

        List<PokemonEntity> returned = pokemonController.getAllPokemonsByPokedexId(1L);

        verify(pokemonRepository, times(1)).findAll();

        verifyNoMoreInteractions(pokemonRepository);

        assertEquals(pokemonEntities, returned);
    }

    @Test
    public void createPokemon() {
        PokemonDTO created = new PokemonDTO();
        PokemonEntity persisted = new PokemonEntity();
        persisted.setId(1L);

        when(pokemonRepository.save(any(PokemonEntity.class))).thenReturn(persisted);

        PokemonEntity returned = pokemonController.createPokemon(created);

        ArgumentCaptor<PokemonEntity> pokemonEntityArgumentCaptor = ArgumentCaptor.forClass(PokemonEntity.class);
        verify(pokemonRepository, times(1)).save(pokemonEntityArgumentCaptor.capture());
        verifyNoMoreInteractions(pokemonRepository);

        assertEquals(created.getId(), pokemonEntityArgumentCaptor.getValue().getId());
        assertEquals(created.getName(), pokemonEntityArgumentCaptor.getValue().getName());
        assertEquals(created.getIdPokedex(), pokemonEntityArgumentCaptor.getValue().getIdPokedex());
        assertEquals(persisted, returned);
    }

    @Test
    public void getPokemonById() throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);

        when(pokemonRepository.findById(1L)).thenReturn(java.util.Optional.of(pokemonEntity));

        ResponseEntity<PokemonEntity> returned = pokemonController.getPokemonById(1L);

        verify(pokemonRepository, times(1)).findById(1L);

        verifyNoMoreInteractions(pokemonRepository);

        assertEquals(pokemonEntity, returned.getBody());
    }

    @Test
    public void deletePokemonById() throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);

        when(pokemonRepository.findById(1L)).thenReturn(java.util.Optional.of(pokemonEntity));

        Map<String, Boolean> returned = pokemonController.deletePokemonById(1L);

        try {
            pokemonController.deletePokemonById(2L);
        } catch (ResourceNotFoundException e) {
            assert (e.getMessage().contains("Pokemon not found"));
        }

        verify(pokemonRepository, times(1)).findById(1L);
        verify(pokemonRepository, times(1)).findById(2L);
        verify(pokemonRepository, times(1)).delete(pokemonEntity);

        verifyNoMoreInteractions(pokemonRepository);

        assertTrue(returned.get("deleted"));
    }

    @Test
    public void updatePokemon() throws ResourceNotFoundException {
        PokemonDTO updated = new PokemonDTO();
        updated.setId(1L);
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);

        when(pokemonRepository.findById(updated.getId())).thenReturn(java.util.Optional.of(pokemonEntity));
        when(pokemonRepository.save(any(PokemonEntity.class))).thenReturn(pokemonEntity);

        ResponseEntity<PokemonEntity> returned = pokemonController.updatePokemon(1L, updated);

        ArgumentCaptor<PokemonEntity> pokemonEntityArgumentCaptor = ArgumentCaptor.forClass(PokemonEntity.class);
        verify(pokemonRepository, times(1)).findById(updated.getId());
        verify(pokemonRepository, times(1)).save(pokemonEntityArgumentCaptor.capture());
        verifyNoMoreInteractions(pokemonRepository);

        assertEquals(updated.getId(), Objects.requireNonNull(returned.getBody()).getId());
        assertEquals(updated.getName(), Objects.requireNonNull(returned.getBody()).getName());
        assertEquals(updated.getIdPokedex(), Objects.requireNonNull(returned.getBody()).getIdPokedex());

        assertEquals(updated.getId(), pokemonEntityArgumentCaptor.getValue().getId());
        assertEquals(updated.getName(), pokemonEntityArgumentCaptor.getValue().getName());
        assertEquals(updated.getIdPokedex(), pokemonEntityArgumentCaptor.getValue().getIdPokedex());

        assertEquals(pokemonEntity, returned.getBody());
    }

    @Test
    public void searchPokemon() {
        List<PokemonEntity> expected = new ArrayList<>();

        when(pokemonRepository.findAll()).thenReturn(expected);

        List<PokemonEntity> returned = pokemonController.searchPokemon("test");

        verify(pokemonRepository, times(1)).findAll();
        verifyNoMoreInteractions(pokemonRepository);

        assertEquals(expected, returned);
    }

    @Test
    public void exportPokemons() throws IOException {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);
        pokemonEntity.setName("Bulbizarre");
        pokemonEntity.setIdPokedex(1L);

        List<PokemonEntity> pokemonEntities = new ArrayList<>();
        pokemonEntities.add(pokemonEntity);

        List<TypeEntity> typeEntities = new ArrayList<>();
        List<EstTypeEntity> estTypeEntities = new ArrayList<>();

        when(pokemonRepository.findAll()).thenReturn(pokemonEntities);
        when(typeRepository.findAll()).thenReturn(typeEntities);
        when(estTypeRepository.findAll()).thenReturn(estTypeEntities);

        ResponseEntity<InputStreamResource> returned = pokemonController.exportPokemons("xlsx", "full", "bulb");
        ResponseEntity<InputStreamResource> returnedCsv = pokemonController.exportPokemons("csv", "light", "bulb");
        ResponseEntity<InputStreamResource> returnedEmptyExp = pokemonController.exportPokemons("csv", "light", "");

        verify(pokemonRepository, times(3)).findAll();
        verify(typeRepository, times(3)).findAll();
        verify(estTypeRepository, times(3)).findAll();
        verifyNoMoreInteractions(pokemonRepository);
        verifyNoMoreInteractions(typeRepository);
        verifyNoMoreInteractions(estTypeRepository);

        assertThat(Objects.requireNonNull(returned.getBody()).contentLength(), greaterThan(0L));
        assertThat(Objects.requireNonNull(returnedCsv.getBody()).contentLength(), greaterThan(0L));
        assertThat(Objects.requireNonNull(returnedEmptyExp.getBody()).contentLength(), greaterThan(0L));
    }
}