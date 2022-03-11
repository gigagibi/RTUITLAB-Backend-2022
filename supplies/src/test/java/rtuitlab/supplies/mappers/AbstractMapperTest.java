package rtuitlab.supplies.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import rtuitlab.supplies.dto.AbstractGetDTO;
import rtuitlab.supplies.dto.AbstractPostPutDTO;
import rtuitlab.supplies.models.AbstractDocument;

import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class AbstractMapperTest<E extends AbstractDocument, G extends AbstractGetDTO, P extends AbstractPostPutDTO, M extends CommonMapper<E, G, P>> {
    protected M mapper;
    protected Supplier<E> eSupplier;
    protected Supplier<G> gSupplier;
    protected Supplier<P> pSupplier;

    @Test
    void shouldConvertPostPutDTOToGetDTO() {
        // arrange
        P p = pSupplier.get();
        E expectedE = eSupplier.get();
        expectedE.setId(null);
        G g = gSupplier.get();

        // act
        E e = mapper.postPutDTOToEntity(p);

        // assert
        assertThat(e.getId()).isEqualTo(expectedE.getId());
        assertThat(e).isNotNull();
    }

    @Test
    void shouldConvertEntityToGetDTO() {
        // arrange
        E e = eSupplier.get();
        G expectedG = gSupplier.get();

        // act
        G g = mapper.entityToDTO(e);

        // assert
        assertThat(g.getId()).isEqualTo(expectedG.getId());
    }
}
