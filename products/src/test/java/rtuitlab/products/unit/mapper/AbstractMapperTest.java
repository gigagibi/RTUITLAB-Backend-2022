package rtuitlab.products.unit.mapper;

import org.junit.jupiter.api.Test;
import rtuitlab.products.entities.AbstractEntity;
import rtuitlab.products.dto.*;
import rtuitlab.products.mapper.CommonMapper;

import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class AbstractMapperTest<E extends AbstractEntity, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO, M extends CommonMapper<E, Get, Post, Put, Posted, Updated>> {
    protected M mapper;
    protected Supplier<E> eSupplier;
    protected Supplier<Get> getSupplier;
    protected Supplier<Post> postSupplier;
    protected Supplier<Put> putSupplier;
    protected Supplier<Posted> postedSupplier;
    protected Supplier<Updated> updatedSupplier;

    @Test
    void shouldConvertPostDTOToEntity() {
        // arrange
        Post postDTO = postSupplier.get();
        E expectedE = eSupplier.get();
        expectedE.setId(null);

        // act
        E e = mapper.postDTOToEntity(postDTO);

        // assert
        assertThat(e.getId()).isEqualTo(expectedE.getId());
        assertThat(e).isNotNull();
    }

    @Test
    void shouldConvertPutDTOToEntity() {
        // arrange
        Put putDTO = putSupplier.get();
        E expectedE = eSupplier.get();
        expectedE.setId(null);

        // act
        E e = mapper.putDTOToEntity(putDTO);

        // assert
        assertThat(e.getId()).isEqualTo(expectedE.getId());
        assertThat(e).isNotNull();
    }

    @Test
    void shouldConvertEntityToGetDTO() {
        // arrange
        E e = eSupplier.get();
        Get expectedG = getSupplier.get();

        // act
        Get g = mapper.entityToGetDTO(e);

        // assert
        assertThat(g.getId()).isEqualTo(expectedG.getId());
    }

    @Test
    void shouldConvertEntityToPostedDTO() {
        // arrange
        E e = eSupplier.get();
        Posted expectedPosted = postedSupplier.get();

        // act
        Posted posted = mapper.entityToPostedDTO(e);

        // assert
        assertThat(posted.getId()).isEqualTo(expectedPosted.getId());
    }

    @Test
    void shouldConvertEntityToUpdatedDTO() {
        // arrange
        E e = eSupplier.get();
        Updated expectedUpdated = updatedSupplier.get();

        // act
        Updated updated = mapper.entityToUpdatedDTO(e);

        // assert
        assertThat(updated.getId()).isEqualTo(expectedUpdated.getId());
    }
}
