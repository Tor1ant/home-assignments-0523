package mapper;


import java.util.UUID;
import model.Task;
import model.TaskDto;

/**
 * The TaskMapper class is responsible for converting TaskDto objects to Task objects.
 */
public class TaskMapper {

    public Task taskDtoToTask(TaskDto taskDto) {
        return new Task(UUID.randomUUID(), taskDto.owner(), taskDto.description());
    }
}
