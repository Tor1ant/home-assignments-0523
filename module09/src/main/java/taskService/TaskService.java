package taskService;

import java.util.UUID;
import model.Task;
import model.TaskDto;

public interface TaskService {

    Task getTask(UUID var1);

    Task postTask(TaskDto var1);

    Task deleteTask(UUID var1);
}
