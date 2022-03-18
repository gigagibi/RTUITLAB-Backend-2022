package rtuitlab.supplies.integration.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rtuitlab.supplies.controllers.AbstractController;
import rtuitlab.supplies.dto.*;
import rtuitlab.supplies.exceptions.EntityNotFoundException;
import rtuitlab.supplies.models.documents.AbstractDocument;
import rtuitlab.supplies.services.CommonService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractControllerTest<E extends AbstractDocument, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO, S extends CommonService<E, Get, Post, Put, Posted, Updated>, C extends AbstractController<E, Get, Post, Put, Posted, Updated, S>> {
    @Autowired
    protected MockMvc mockMvc;
    protected String apiUrl;
    @MockBean
    protected S service;
    protected Supplier<Get> getSupplier;
    protected Supplier<Post> postSupplier;
    protected Supplier<Put> putSupplier;
    protected Supplier<Posted> postedSupplier;
    protected Supplier<Updated> updatedSupplier;
    protected ObjectMapper objectMapper;

    @Test
    void shouldReturnAllGetDTO() throws Exception {
        // arrange
        Get get1 = getSupplier.get();
        get1.setId("1");
        Get get2 = getSupplier.get();
        get2.setId("2");
        List<Get> listGet = List.of(get1, get2);
        given(service.getAll()).willReturn(listGet);

        // act
        String responseJson = mockMvc.perform(get(apiUrl+"/"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();

        // assert
        String expectedResponseJson = objectMapper.writeValueAsString(listGet);
        assertThat(responseJson).isEqualTo(expectedResponseJson);
    }

    @Test
    void shouldReturnGetDTOById() throws Exception {
        // arrange
        Get get = getSupplier.get();
        String id = get.getId();
        given(service.getById(id)).willReturn(get);

        // act
        String responseJson = mockMvc.perform(get(apiUrl+"/"+id))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();

        // assert
        String expectedResponseJson =  objectMapper.writeValueAsString(get);
        assertThat(responseJson).isEqualTo(expectedResponseJson);
    }

    @Test
    void shouldCreateAndReturnListOfPostedDTO() throws Exception {
        // arrange
        Post post = postSupplier.get();
        Posted posted1 = postedSupplier.get();
        posted1.setId("1");
        Posted posted2 = postedSupplier.get();
        posted2.setId("2");
        List<Posted> listPosted = List.of(posted1, posted2);
        given(service.create(any())).willReturn(listPosted);
        String request =  objectMapper.writeValueAsString(post);

        // act
        String responseJson = mockMvc.perform(post(apiUrl+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();

        // assert
        String expectedResponseJson =  objectMapper.writeValueAsString(listPosted);
        assertThat(responseJson).isEqualTo(expectedResponseJson);
    }

    @Test
    void shouldUpdateAndReturnUpdatedDTO() throws Exception {
        // arrange
        String id = "1";
        Put put = putSupplier.get();
        Updated updated = updatedSupplier.get();
        given(service.update(any(), any())).willReturn(updated);
        String request =  objectMapper.writeValueAsString(put);

        // act
        String responseJson = mockMvc.perform(put(apiUrl+"/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();

        // assert
        String expectedResponseJson =  objectMapper.writeValueAsString(updated);
        assertThat(responseJson).isEqualTo(expectedResponseJson);
    }

    @Test
    void shouldDeleteByIdAndReturnListOfGetDTO() throws Exception {
        // arrange
        String id = "1";
        Get get = getSupplier.get();
        List<Get> listGet = List.of(get);
        given(service.deleteById(id)).willReturn(listGet);

        // act
        String responseJson = mockMvc.perform(delete(apiUrl+"/"+id))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();

        // assert
        String expectedResponseJson =  objectMapper.writeValueAsString(listGet);
        assertThat(responseJson).isEqualTo(expectedResponseJson);
    }

    @Test
    void shouldDeleteAllAndReturnEmptyList() throws Exception {
        // arrange
        given(service.deleteAll()).willReturn(new ArrayList<>());

        // act
        String responseJson = mockMvc.perform(delete(apiUrl+"/"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();

        // assert
        String expectedResponseJson = "[]";
        assertThat(responseJson).isEqualTo(expectedResponseJson);
    }
}
