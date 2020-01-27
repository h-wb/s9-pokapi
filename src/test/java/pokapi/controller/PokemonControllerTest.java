package pokapi.controller;

import org.apache.commons.io.FileUtils;
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
import pokapi.helper.export.ExportXLSX;
import pokapi.helper.export.version.PokemonsExportVersion;
import pokapi.helper.export.version.PokemonsExportVersionFull;
import pokapi.repository.EstTypeRepository;
import pokapi.repository.PokemonRepository;
import pokapi.repository.TypeRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

        verify(pokemonRepository, times(1)).findById(1L);
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
        String extension = "xlsx";

        when(pokemonRepository.findAll()).thenReturn(pokemonEntities);
        when(typeRepository.findAll()).thenReturn(typeEntities);
        when(estTypeRepository.findAll()).thenReturn(estTypeEntities);

        ResponseEntity<InputStreamResource> returned = pokemonController.exportPokemons(extension, "full", "bulb");
        ResponseEntity<InputStreamResource> returnedBad = pokemonController.exportPokemons(extension, "full", "test");

        PokemonsExportVersion exportVersion = new PokemonsExportVersionFull(extension, pokemonEntities, typeEntities, estTypeEntities);
        InputStreamResource expected1 = new InputStreamResource(ExportXLSX.export(exportVersion));
        InputStreamResource expected2 = new InputStreamResource(ExportXLSX.export(exportVersion));

        verify(pokemonRepository, times(2)).findAll();
        verify(typeRepository, times(2)).findAll();
        verify(estTypeRepository, times(2)).findAll();
        verifyNoMoreInteractions(pokemonRepository);
        verifyNoMoreInteractions(typeRepository);
        verifyNoMoreInteractions(estTypeRepository);

        assertEquals(expected1.contentLength(), Objects.requireNonNull(returned.getBody()).contentLength());
        assertNotEquals(expected2.contentLength(), Objects.requireNonNull(returnedBad.getBody()).contentLength());
    }
}