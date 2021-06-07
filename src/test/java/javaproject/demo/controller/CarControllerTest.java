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
import javaproject.demo.dto.CarRequest;
import javaproject.demo.repository.CarRepository;
import javaproject.demo.repository.SupplierRepository;
import javaproject.demo.stubs.ProductStub;

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
class CarControllerTest {

    @MockBean
    CarRepository carRepository;

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
        var request = ProductStub.getProductRequest();
        var expectedProduct = ProductStub.getRandomProduct();
        when(carRepository.save(any())).thenReturn(expectedProduct);
        mockMvc.perform(postRequest("/products", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedProduct.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedProduct.getDescription())));
    }


    @Test
    void should_successfully_get_by_id() throws Exception {
        var expectedType = ProductStub.getRandomProduct();
        when(carRepository.findById(any())).thenReturn(Optional.of(expectedType));
        mockMvc.perform(getRequest("/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }


    @Test
    void should_successfully_get_all() throws Exception {
        var expectedType = ProductStub.getRandomProduct();
        when(carRepository.findAll()).thenReturn(Collections.singletonList(expectedType));
        mockMvc.perform(getRequest("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }



    @Test
    void should_return_empty_array_get_all() throws Exception {
        when(carRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(getRequest("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }



    @Test
    void should_successfully_update_by_id() throws Exception {
        var request = ProductStub.getProductRequest();
        request.setName("Name1");
        var expectedType = ProductStub.getRandomProduct();
        when(carRepository.findById(any())).thenReturn(Optional.of(expectedType));
        when(carRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        mockMvc.perform(putRequest("/products/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(request.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }


    @Test
    void should_successfully_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/products/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(carRepository, atLeast(1)).deleteById(1L);
    }


    private MockHttpServletRequestBuilder postRequest(String url, CarRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, CarRequest request) {
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