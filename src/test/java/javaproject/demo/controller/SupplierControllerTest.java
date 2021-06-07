package javaproject.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import javaproject.demo.dto.SupplierRequest;
import javaproject.demo.repository.SupplierRepository;
import javaproject.demo.stubs.SupplierStub;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SupplierControllerTest {
    @MockBean
    SupplierRepository supplierRepository;
    
    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void should_successfully_save() throws Exception {
        var request = SupplierStub.getSupplierRequest();
        var expectedType = SupplierStub.getRandomSupplier();
        when(supplierRepository.save(any())).thenReturn(expectedType);
        mockMvc.perform(postRequest("/suppliers", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getBusinessName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }


    @Test
    void should_successfully_get_by_id() throws Exception {
        var expectedType = SupplierStub.getRandomSupplier();
        when(supplierRepository.findById(any())).thenReturn(Optional.of(expectedType));
        mockMvc.perform(getRequest("/suppliers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getBusinessName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }


    @Test
    void should_successfully_get_all() throws Exception {
        var expectedType = SupplierStub.getRandomSupplier();
        when(supplierRepository.findAll()).thenReturn(Collections.singletonList(expectedType));
        mockMvc.perform(getRequest("/suppliers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getBusinessName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }



    @Test
    void should_return_empty_array_get_all() throws Exception {
        when(supplierRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(getRequest("/suppliers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    

    @Test
    void should_successfully_update_by_id() throws Exception {
        var request = SupplierStub.getSupplierRequest();
        request.setBusinessName("Name1");
        var expectedType = SupplierStub.getRandomSupplier();
        when(supplierRepository.findById(any())).thenReturn(Optional.of(expectedType));
        when(supplierRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        mockMvc.perform(putRequest("/suppliers/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(request.getBusinessName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }


    @Test
    void should_successfully_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/suppliers/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(supplierRepository, atLeast(1)).deleteById(1L);
    }

    private MockHttpServletRequestBuilder postRequest(String url, SupplierRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, SupplierRequest request) {
        return put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder getRequest(String url) {
        return get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    private MockHttpServletRequestBuilder deleteRequest(String url) {
        return delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

}