package javaproject.demo.service.worker.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javaproject.demo.entities.Worker;
import javaproject.demo.repository.WorkerRepository;
import javaproject.demo.stubs.OrderStub;
import javaproject.demo.stubs.WorkerStub;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {
    private WorkerService service;
    @Mock
    private WorkerRepository workerRepository;

    @BeforeEach
    void setup() {
        service = new WorkerService(workerRepository);
    }

    @Test
    void getSuccessful() {
        List<Worker> list = new ArrayList<Worker>();
        var worker = WorkerStub.getRandomWorker();
        list.add(worker);
        list.add(worker);
        list.add(worker);

        when(workerRepository.findAll()).thenReturn(list);
        var getAll = service.getAll();
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulById() {
        var worker = WorkerStub.getRandomWorker();
        when(workerRepository.findById(Mockito.any())).thenReturn(Optional.of(worker));
        var byId = service.getById(WorkerStub.ID);

        assertAll(() -> assertEquals(byId.getId(), worker.getId()),
                () -> assertEquals(byId.getName(), worker.getName()),
                () -> assertEquals(byId.getSurname(), worker.getSurname()),
                () -> assertEquals(byId.getPatronic(), worker.getPatronic()),
                () -> assertEquals(byId.getBirthday(), worker.getBirthday()),
                () -> assertEquals(byId.getNumber(), worker.getNumber()),
                () -> assertEquals(byId.getJob(), worker.getJob()),
                () -> assertEquals(byId.getStartedWork(), worker.getStartedWork()));
    }


    @Test
    void createSuccessful() {
        var captor = ArgumentCaptor.forClass(Worker.class);
        var worker = WorkerStub.getRandomWorker();
        when(workerRepository.save(Mockito.any())).thenReturn(WorkerStub.getRandomWorker());
        var result = service.create(WorkerStub.getRandomWorker());
        Mockito.verify(workerRepository, atLeast(1)).save(captor.capture());
        assertEquals(worker, captor.getValue());
        assertEquals(worker.getName(), result.getName());
    }

    @Test
    void updateSuccessful() {
        var captor = ArgumentCaptor.forClass(Worker.class);
        var worker = WorkerStub.getRandomWorker();
        when(workerRepository.findById(Mockito.any())).thenReturn(Optional.of(WorkerStub.getRandomWorker()));
        when(workerRepository.save(Mockito.any())).thenReturn(WorkerStub.getRandomWorker());
        var result = service.update(WorkerStub.ID, WorkerStub.getRandomWorker());
        Mockito.verify(workerRepository, atLeast(1)).save(captor.capture());
        assertEquals(worker, captor.getValue());
        assertEquals(worker.getName(), result.getName());
    }

    @Test
    void deleteSuccessful() {
        service.delete(WorkerStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(workerRepository, atLeast(1)).deleteById(captor.capture());
        assertEquals(WorkerStub.ID, captor.getValue());
    }
}