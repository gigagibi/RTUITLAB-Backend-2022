package rtuitlab.orders.unit.services;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import rtuitlab.orders.exceptions.EntityNotFoundException;
import rtuitlab.orders.mappers.CommonMapper;
import rtuitlab.orders.models.documents.AbstractDocument;
import rtuitlab.orders.repositories.CommonRepository;
import rtuitlab.orders.dto.*;
import rtuitlab.orders.services.CommonService;

import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public abstract class AbstractServiceTest<E extends AbstractDocument, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO, S extends CommonService<E, Get, Post, Put, Posted, Updated>, M extends CommonMapper<E, Get, Post, Put, Posted, Updated>, R extends CommonRepository<E>> {
    protected R mockRepository;
    protected M mockMapper;
    protected S service;
    protected Supplier<E> eSupplier;
    protected Supplier<Get> getSupplier;
    protected Supplier<Post> postSupplier;
    protected Supplier<Put> putSupplier;
    protected Supplier<Posted> postedSupplier;
    protected Supplier<Updated> updatedSupplier;
    protected Supplier<ArgumentCaptor<E>> eArgumentCaptorSupplier;

    @Test
    void shouldGetAll() {
        // arrange (no given arrange)

        // act
        service.getAll();

        // assert
        verify(mockRepository).findAll();
    }

    @Test
    void shouldGetById() throws EntityNotFoundException {
        // arrange
        E e = eSupplier.get();
        Get g = getSupplier.get();
        String id = e.getId();
        given(mockMapper.entityToGetDTO(e)).willReturn(g);
        given(mockRepository.findById(id)).willReturn(Optional.of(e));

        // act
        service.getById(id);

        // assert
        ArgumentCaptor<String> idArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockRepository).findById(idArgumentCaptor.capture());
        String capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void shouldCreate() {
        // arrange (no given arrange)
        E e = eSupplier.get();
        Post post = postSupplier.get();
        given(mockMapper.postDTOToEntity(post)).willReturn(e);

        // act
        service.create(post);

        // assert
        ArgumentCaptor<E> eArgumentCaptor = eArgumentCaptorSupplier.get();
        verify(mockRepository).save(eArgumentCaptor.capture());
        E captured = eArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(e);
    }

    @Test
    void shouldUpdate() throws EntityNotFoundException {
        // arrange
        E e = eSupplier.get();
        String id = e.getId();
        given(mockRepository.existsById(id)).willReturn(true);
        given(mockRepository.findById(id)).willReturn(Optional.of(e));
        Put put = putSupplier.get();
        given(mockMapper.putDTOToEntity(put)).willReturn(e);

        // act
        service.update(id, put);

        // assert
        ArgumentCaptor<E> eArgumentCaptor = eArgumentCaptorSupplier.get();
        verify(mockRepository).save(eArgumentCaptor.capture());
        E captured = eArgumentCaptor.getValue();
        assertThat(id).isEqualTo(captured.getId());
    }

    @Test
    void shouldDeleteById() throws EntityNotFoundException {
        // arrange
        String id = "1";
        given(mockRepository.existsById(id)).willReturn(true);

        // act
        service.deleteById(id);

        // assert
        ArgumentCaptor<String> idArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockRepository).deleteById(idArgumentCaptor.capture());
        String captured = idArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(id);
    }

    @Test
    void shouldDeleteAll() {
        // arrange (no given arrange)

        // act
        service.deleteAll();

        // assert
        verify(mockRepository).deleteAll();
    }

    @Test
    void shouldThrow_EntityNotFoundException_WhenGetsById() throws EntityNotFoundException {
        // arrange
        String id = "1";

        // act
        // assert
        assertThatThrownBy(() -> service.getById(id)).hasMessage("Entity with id = " + id + " is not found");
    }

    @Test
    void shouldThrow_EntityNotFoundException_WhenUpdatesById() throws EntityNotFoundException {
        // arrange
        String id = "1";

        // act
        // assert
        assertThatThrownBy(() -> service.update(id, any())).hasMessage("Entity with id = " + id + " is not found");
    }

    @Test
    void shouldThrow_EntityNotFoundException_WhenDeletesById() throws EntityNotFoundException {
        // arrange
        String id = "1";
        // act
        // assert
        assertThatThrownBy(() -> service.deleteById(id)).hasMessage("Entity with id = " + id + " is not found");
    }
}