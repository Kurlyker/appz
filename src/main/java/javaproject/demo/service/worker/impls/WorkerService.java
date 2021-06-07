package javaproject.demo.service.worker.impls;


import javaproject.demo.entities.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javaproject.demo.repository.WorkerRepository;
import javaproject.demo.service.worker.interfaces.IWorkerService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkerService implements IWorkerService {
    private final  WorkerRepository workerRepository;

    @Override
    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    @Override
    public Worker getById(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
    }

    @Override
    public Worker create(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public Worker update(Long id, Worker worker) {
        var personnelToUpdate = workerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return workerRepository.save(personnelToUpdate);
    }

    @Override
    public void delete(Long id) {
        workerRepository.deleteById(id);
    }
}
