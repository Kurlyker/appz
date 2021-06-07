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
import javaproject.demo.entities.Order;
import javaproject.demo.repository.ClientRepository;
import javaproject.demo.repository.OrderRepository;
import javaproject.demo.repository.CarRepository;
import javaproject.demo.stubs.OrderStub;

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
class OrderControllerTest {

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    CarRepository carRepository;

    @MockBean
    ClientRepository clientRepository;

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
        var expectedOrder = OrderStub.getRandomOrder();
        when(orderRepository.save(any())).thenReturn(expectedOrder);
        mockMvc.perform(postRequest("/orders", expectedOrder).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedOrder.getDescription())));
    }

    @Test
    void should_successfully_get_by_id() throws Exception {
        var expectedOrder = OrderStub.getRandomOrder();
        when(orderRepository.findById(any())).thenReturn(Optional.of(expectedOrder));
        mockMvc.perform(getRequest("/orders/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedOrder.getDescription())));
    }

    @Test
    void should_successfully_get_all() throws Exception {
        var expectedOrder = OrderStub.getRandomOrder();
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(expectedOrder));
        mockMvc.perform(getRequest("/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedOrder.getDescription())));
    }


    @Test
    void should_return_empty_array_get_all() throws Exception {
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(getRequest("/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void should_successfully_get_by_product() throws Exception {
        var expectedOrder = OrderStub.getRandomOrder();
        when(orderRepository.findOrdersByProduct_Id(any())).thenReturn(Collections.singletonList(expectedOrder));
        mockMvc.perform(getRequest("/orders/product/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedOrder.getDescription())))
               ;
    }


    @Test
    void should_successfully_get_by_client() throws Exception {
        var expectedOrder = OrderStub.getRandomOrder();
        when(orderRepository.findOrdersByClient_Id(any())).thenReturn(Collections.singletonList(expectedOrder));
        mockMvc.perform(getRequest("/orders/client/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedOrder.getDescription())))
        ;
    }


    @Test
    void should_successfully_update_by_id() throws Exception {
        var request = OrderStub.getRandomOrder();
        request.setDescription("description 2");
        var expectedOrder = OrderStub.getRandomOrder();
        when(orderRepository.findById(any())).thenReturn(Optional.of(expectedOrder));
        when(orderRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        mockMvc.perform(putRequest("/orders/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedOrder.getDescription())));
    }


    @Test
    void should_successfully_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/orders/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(orderRepository, atLeast(1)).deleteById(1L);
    }

    private MockHttpServletRequestBuilder postRequest(String url, Order request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, Order request) {
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