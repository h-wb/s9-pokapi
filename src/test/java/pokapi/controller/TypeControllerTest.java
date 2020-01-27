package pokapi.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pokapi.dto.TypeDTO;
import pokapi.dto.TypeDTO;
import pokapi.entity.*;
import pokapi.entity.TypeEntity;
import pokapi.entity.TypeEntity;
import pokapi.entity.TypeEntity;
import pokapi.exception.ResourceNotFoundException;
import pokapi.repository.TypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class TypeControllerTest {

    @InjectMocks
    private TypeController typeController;

    @Mock
    private TypeRepository typeRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTypes() {
        List<TypeEntity> typeEntities = new ArrayList<>();

        when(typeRepository.findAll()).thenReturn(typeEntities);

        List<TypeEntity> returned = typeController.getAllTypes();

        verify(typeRepository, times(1)).findAll();

        verifyNoMoreInteractions(typeRepository);

        assertEquals(typeEntities, returned);
    }

    @Test
    public void createType() {
        TypeDTO created = new TypeDTO();
        TypeEntity persisted = new TypeEntity();
        persisted.setId(1L);

        when(typeRepository.save(any(TypeEntity.class))).thenReturn(persisted);

        TypeEntity returned = typeController.createType(created);

        ArgumentCaptor<TypeEntity> typeEntityArgumentCaptor = ArgumentCaptor.forClass(TypeEntity.class);
        verify(typeRepository, times(1)).save(typeEntityArgumentCaptor.capture());
        verifyNoMoreInteractions(typeRepository);

        assertEquals(created.getId(), typeEntityArgumentCaptor.getValue().getId());
        assertEquals(created.getName(), typeEntityArgumentCaptor.getValue().getName());
        assertEquals(persisted, returned);
    }

    @Test
    public void getTypeById() throws ResourceNotFoundException {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setId(1L);

        when(typeRepository.findById(1L)).thenReturn(java.util.Optional.of(typeEntity));

        ResponseEntity<TypeEntity> returned = typeController.getTypeById(1L);

        verify(typeRepository, times(1)).findById(1L);

        verifyNoMoreInteractions(typeRepository);

        assertEquals(typeEntity, returned.getBody());
    }

    @Test
    public void deleteTypeById() throws ResourceNotFoundException {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setId(1L);

        when(typeRepository.findById(1L)).thenReturn(java.util.Optional.of(typeEntity));

        Map<String, Boolean> returned = typeController.deleteTypeById(1L);

        verify(typeRepository, times(1)).findById(1L);
        verify(typeRepository, times(1)).delete(typeEntity);

        verifyNoMoreInteractions(typeRepository);

        assertTrue(returned.get("deleted"));
    }

    @Test
    public void updateType() throws ResourceNotFoundException {
        TypeDTO updated = new TypeDTO();
        updated.setId(1L);
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setId(1L);

        when(typeRepository.findById(updated.getId())).thenReturn(java.util.Optional.of(typeEntity));
        when(typeRepository.save(any(TypeEntity.class))).thenReturn(typeEntity);

        ResponseEntity<TypeEntity> returned = typeController.updateType(1L, updated);

        ArgumentCaptor<TypeEntity> typeEntityArgumentCaptor = ArgumentCaptor.forClass(TypeEntity.class);
        verify(typeRepository, times(1)).findById(updated.getId());
        verify(typeRepository, times(1)).save(typeEntityArgumentCaptor.capture());
        verifyNoMoreInteractions(typeRepository);

        assertEquals(updated.getId(), Objects.requireNonNull(returned.getBody()).getId());
        assertEquals(updated.getName(), Objects.requireNonNull(returned.getBody()).getName());

        assertEquals(updated.getId(), typeEntityArgumentCaptor.getValue().getId());
        assertEquals(updated.getName(), typeEntityArgumentCaptor.getValue().getName());

        assertEquals(typeEntity, returned.getBody());
    }

    @Test
    public void searchType() {
        List<TypeEntity> expected = new ArrayList<>();

        when(typeRepository.findAll()).thenReturn(expected);

        List<TypeEntity> returned = typeController.searchType("test");

        verify(typeRepository, times(1)).findAll();
        verifyNoMoreInteractions(typeRepository);

        assertEquals(expected, returned);
    }
}