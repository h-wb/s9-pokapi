package pokapi.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pokapi.dto.EstTypeDTO;
import pokapi.entity.EstTypeEntity;
import pokapi.exception.ResourceNotFoundException;
import pokapi.repository.EstTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class EstTypeControllerTest {

    @InjectMocks
    private EstTypeController estTypeController;

    @Mock
    private EstTypeRepository estTypeRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllLiensEstType() {
        List<EstTypeEntity> estTypeEntities = new ArrayList<>();

        when(estTypeRepository.findAll()).thenReturn(estTypeEntities);

        List<EstTypeEntity> returned = estTypeController.getAllLiensEstType();

        verify(estTypeRepository, times(1)).findAll();

        verifyNoMoreInteractions(estTypeRepository);

        assertEquals(estTypeEntities, returned);
    }

    @Test
    public void getAllLiensEstTypeByPokemon() {
        List<EstTypeEntity> estTypeEntities = new ArrayList<>();

        when(estTypeRepository.findAll()).thenReturn(estTypeEntities);

        List<EstTypeEntity> returned = estTypeController.getAllLiensEstTypeByPokemon(1L);

        verify(estTypeRepository, times(1)).findAll();

        verifyNoMoreInteractions(estTypeRepository);

        assertEquals(estTypeEntities, returned);
    }

    @Test
    public void getAllLiensEstTypeByType() {
        List<EstTypeEntity> estTypeEntities = new ArrayList<>();

        when(estTypeRepository.findAll()).thenReturn(estTypeEntities);

        List<EstTypeEntity> returned = estTypeController.getAllLiensEstTypeByPokemon(1L);

        verify(estTypeRepository, times(1)).findAll();

        verifyNoMoreInteractions(estTypeRepository);

        assertEquals(estTypeEntities, returned);
    }

    @Test
    public void createLienEstType() {
        EstTypeDTO created = new EstTypeDTO();
        EstTypeEntity persisted = new EstTypeEntity();
        persisted.setId(1L);

        when(estTypeRepository.save(any(EstTypeEntity.class))).thenReturn(persisted);

        EstTypeEntity returned = estTypeController.createLienEstType(created);

        ArgumentCaptor<EstTypeEntity> estTypeEntityArgumentCaptor = ArgumentCaptor.forClass(EstTypeEntity.class);
        verify(estTypeRepository, times(1)).save(estTypeEntityArgumentCaptor.capture());
        verifyNoMoreInteractions(estTypeRepository);

        assertEquals(created.getId(), estTypeEntityArgumentCaptor.getValue().getId());
        assertEquals(created.getIdPokemon(), estTypeEntityArgumentCaptor.getValue().getIdPokemon());
        assertEquals(created.getIdType(), estTypeEntityArgumentCaptor.getValue().getIdType());
        assertEquals(persisted, returned);
    }

    @Test
    public void getLienEstTypeById() throws ResourceNotFoundException {
        EstTypeEntity estTypeEntity = new EstTypeEntity();
        estTypeEntity.setId(1L);

        when(estTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(estTypeEntity));

        ResponseEntity<EstTypeEntity> returned = estTypeController.getLienEstTypeById(1L);

        verify(estTypeRepository, times(1)).findById(1L);

        verifyNoMoreInteractions(estTypeRepository);

        assertEquals(estTypeEntity, returned.getBody());
    }

    @Test
    public void deleteLienEstType() throws ResourceNotFoundException {
        EstTypeEntity estTypeEntity = new EstTypeEntity();
        estTypeEntity.setId(1L);

        when(estTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(estTypeEntity));

        Map<String, Boolean> returned = estTypeController.deleteLienEstType(1L);

        verify(estTypeRepository, times(1)).findById(1L);
        verify(estTypeRepository, times(1)).delete(estTypeEntity);

        verifyNoMoreInteractions(estTypeRepository);

        assertTrue(returned.get("deleted"));
    }

    @Test
    public void updateLienEstType() throws ResourceNotFoundException {
        EstTypeDTO updated = new EstTypeDTO();
        updated.setId(1L);
        EstTypeEntity estTypeEntity = new EstTypeEntity();
        estTypeEntity.setId(1L);

        when(estTypeRepository.findById(updated.getId())).thenReturn(java.util.Optional.of(estTypeEntity));
        when(estTypeRepository.save(any(EstTypeEntity.class))).thenReturn(estTypeEntity);

        ResponseEntity<EstTypeEntity> returned = estTypeController.updateLienEstType(1L, updated);

        ArgumentCaptor<EstTypeEntity> estTypeEntityArgumentCaptor = ArgumentCaptor.forClass(EstTypeEntity.class);
        verify(estTypeRepository, times(1)).findById(updated.getId());
        verify(estTypeRepository, times(1)).save(estTypeEntityArgumentCaptor.capture());
        verifyNoMoreInteractions(estTypeRepository);

        assertEquals(updated.getId(), Objects.requireNonNull(returned.getBody()).getId());
        assertEquals(updated.getIdPokemon(), Objects.requireNonNull(returned.getBody()).getIdPokemon());
        assertEquals(updated.getIdType(), Objects.requireNonNull(returned.getBody()).getIdType());

        assertEquals(updated.getId(), estTypeEntityArgumentCaptor.getValue().getId());
        assertEquals(updated.getIdPokemon(), estTypeEntityArgumentCaptor.getValue().getIdPokemon());
        assertEquals(updated.getIdType(), estTypeEntityArgumentCaptor.getValue().getIdType());

        assertEquals(estTypeEntity, returned.getBody());
    }
}