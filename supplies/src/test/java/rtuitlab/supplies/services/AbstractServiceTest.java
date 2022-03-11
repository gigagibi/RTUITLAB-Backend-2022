package rtuitlab.supplies.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import rtuitlab.supplies.dto.AbstractGetDTO;
import rtuitlab.supplies.dto.AbstractPostPutDTO;
import rtuitlab.supplies.mappers.CommonMapper;
import rtuitlab.supplies.models.AbstractDocument;
import rtuitlab.supplies.repositories.CommonRepository;

import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public abstract class AbstractServiceTest<E extends AbstractDocument, G extends AbstractGetDTO, P extends AbstractPostPutDTO, S extends CommonService<E, G, P>, M extends CommonMapper<E, G, P>, R extends CommonRepository<E>> {
    protected R mockRepository;
    protected M mockMapper;
    protected S service;
    protected Supplier<E> eSupplier;
    protected Supplier<ArgumentCaptor<E>> eArgumentCaptorSupplier;
    protected Supplier<P> pSupplier;
    protected Supplier<G> gSupplier;

    @Test
    void shouldGetAll() {
        // arrange (no given arrange)

        // act
        service.getAll();

        // assert
        verify(mockRepository).findAll();
    }

    @Test
    void shouldGetById() {
        // arrange
        E e = eSupplier.get();
        G g = gSupplier.get();
        String id = e.getId();
        given(mockMapper.entityToDTO(e)).willReturn(g);
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
        P p = pSupplier.get();
        given(mockMapper.postPutDTOToEntity(p)).willReturn(e);

        // act
        service.create(p);

        // assert
        ArgumentCaptor<E> eArgumentCaptor = eArgumentCaptorSupplier.get();
        verify(mockRepository).save(eArgumentCaptor.capture());
        E captured = eArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(e);
    }

    @Test
    void shouldUpdate() {
        // arrange
        E e = eSupplier.get();
        String id = e.getId();
        given(mockRepository.findById(id)).willReturn(Optional.of(e));
        P p = pSupplier.get();
        given(mockMapper.postPutDTOToEntity(p)).willReturn(e);

        // act
        service.update(id, p);

        // assert
        ArgumentCaptor<E> eArgumentCaptor = eArgumentCaptorSupplier.get();
        verify(mockRepository).save(eArgumentCaptor.capture());
        E captured = eArgumentCaptor.getValue();
        assertThat(id).isEqualTo(captured.getId());
    }

    @Test
    void shouldDeleteById() {
        // arrange
        String id = "1";
        given(mockRepository.findById(id)).willReturn(any());

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
}