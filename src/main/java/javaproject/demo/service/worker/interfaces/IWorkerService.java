package javaproject.demo.service.worker.interfaces;

import javaproject.demo.entities.Worker;

import java.util.List;

public interface IWorkerService {
    List<Worker> getAll();
    Worker getById(Long id);
    Worker create(Worker worker);
    Worker update(Long id, Worker worker);
    void delete(Long id);
}
