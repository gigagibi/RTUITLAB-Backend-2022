package rtuitlab.supplies.unit.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import rtuitlab.supplies.controllers.AbstractController;
import rtuitlab.supplies.exceptions.EntityNotFoundException;
import rtuitlab.supplies.models.documents.AbstractDocument;
import rtuitlab.supplies.services.CommonService;
import rtuitlab.supplies.dto.*;

import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public abstract class AbstractControllerTest<E extends AbstractDocument, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO, S extends CommonService<E, Get, Post, Put, Posted, Updated>, C extends AbstractController<E, Get, Post, Put, Posted, Updated, S>> {
    protected C controller;
    protected S mockService;
    protected Supplier<E> eSupplier;
    protected Supplier<Get> getSupplier;
    protected Supplier<Post> postSupplier;
    protected Supplier<Put> putSupplier;
    protected Supplier<Posted> postedSupplier;
    protected Supplier<Updated> updatedSupplier;
    protected Supplier<ArgumentCaptor<Post>> postArgumentCaptorSupplier;
    protected Supplier<ArgumentCaptor<Put>> putArgumentCaptorSupplier;

    @Test
    void shouldGetAll() {
        // arrange (no given arrange)

        // act
        controller.getAll();
        //
        verify(mockService).getAll();
    }

    @Test
    void shouldGetById() throws EntityNotFoundException {
        // arrange (no given arrange)
        Get get = getSupplier.get();
        String id = get.getId();
        given(mockService.getById(id)).willReturn(get);

        // act
        controller.getById(id);

        // assert
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockService).getById(argumentCaptor.capture());
        String captured = argumentCaptor.getValue();
        assertThat(captured).isEqualTo(id);
    }

    @Test
    void shouldCreate() {
        // arrange
        Post post = postSupplier.get();

        // act
        controller.create(post);

        // assert
        ArgumentCaptor<Post> argumentCaptor = postArgumentCaptorSupplier.get();
        verify(mockService).create(argumentCaptor.capture());
        Post captured = argumentCaptor.getValue();
        assertThat(captured).isEqualTo(post);
    }

    @Test
    void shouldUpdateById() throws EntityNotFoundException {
        // arrange
        Put put = putSupplier.get();
        String id = "1";

        // act
        controller.update(id, put);

        // assert
        ArgumentCaptor<Put> putArgumentCaptor = putArgumentCaptorSupplier.get();
        ArgumentCaptor<String> idArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockService).update(idArgumentCaptor.capture(), putArgumentCaptor.capture());
        Put putCaptured = putArgumentCaptor.getValue();
        String idCaptured = idArgumentCaptor.getValue();
        assertThat(putCaptured).isEqualTo(put);
        assertThat(idCaptured).isEqualTo(id);
    }

    @Test
    void shouldDeleteAll() {
        // arrange (no given arrange)

        // act
        controller.deleteAll();

        // assert
        verify(mockService).deleteAll();
    }
}
