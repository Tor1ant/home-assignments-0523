package taskService;

import java.util.UUID;
import mapper.TaskMapper;
import model.Task;
import model.TaskDto;
import repository.TaskRepositoryImpl;

public class TaskServiceImpl implements TaskService {

    private final TaskRepositoryImpl taskRepository;
    private final TaskMapper taskMapper = new TaskMapper();

    public TaskServiceImpl(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getTask(UUID id) {
        return taskRepository.getTask(id);
    }

    @Override
    public Task postTask(TaskDto taskDto) {
        Task task = taskMapper.taskDtoToTask(taskDto);
        return taskRepository.saveTask(task);
    }

    @Override
    public Task deleteTask(UUID id) {
        return taskRepository.deleteTask(id);
    }
}
