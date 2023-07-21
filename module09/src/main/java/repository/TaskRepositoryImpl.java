package repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import model.Task;

public class TaskRepositoryImpl implements TaskRepository {

    private final Map<UUID, Task> taskMap = new HashMap<>();

    public Task getTask(UUID id) {
        return taskMap.get(id);
    }

    public Task saveTask(Task task) {
        taskMap.put(task.getId(), task);
        return task;
    }

    public Task deleteTask(UUID id) {
        Task task = taskMap.get(id);
        taskMap.remove(id);
        return task;
    }
}
