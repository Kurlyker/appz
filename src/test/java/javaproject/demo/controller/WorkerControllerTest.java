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
import javaproject.demo.entities.Worker;
import javaproject.demo.repository.WorkerRepository;
import javaproject.demo.stubs.WorkerStub;

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
class WorkerControllerTest {
    @MockBean
    WorkerRepository workerRepository;
    
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
        var worker = WorkerStub.getRandomWorker();
        when(workerRepository.save(any())).thenReturn(worker);
        mockMvc.perform(postRequest("/workers", worker).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(worker.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(worker.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(worker.getNumber())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(worker.getJob())));
    }


    
    @Test
    void should_successfully_get_by_id() throws Exception {
        var expectedWorker = WorkerStub.getRandomWorker();
        when(workerRepository.findById(any())).thenReturn(Optional.of(expectedWorker));
        mockMvc.perform(getRequest("/workers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedWorker.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedWorker.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedWorker.getNumber())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedWorker.getJob())));
    }

    
    @Test
    void should_successfully_get_all() throws Exception {
        var expectedType = WorkerStub.getRandomWorker();
        when(workerRepository.findAll()).thenReturn(Collections.singletonList(expectedType));
        mockMvc.perform(getRequest("/workers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getJob())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getNumber())));
    }


    @Test
    void should_return_empty_array_get_all() throws Exception {
        when(workerRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(getRequest("/workers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    

    @Test
    void should_successfully_update_by_id() throws Exception {
        var request = WorkerStub.getRandomWorker();
        request.setName("Name1");
        when(workerRepository.findById(any())).thenReturn(Optional.of(request));
        when(workerRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        mockMvc.perform(putRequest("/workers/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(request.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(request.getSurname())));
    }


    @Test
    void should_successfully_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/workers/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(workerRepository, atLeast(1)).deleteById(1L);
    }


    private MockHttpServletRequestBuilder postRequest(String url, Worker request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, Worker request) {
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