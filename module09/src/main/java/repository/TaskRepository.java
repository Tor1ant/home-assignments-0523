package repository;

import java.util.UUID;
import model.Task;

/**
 * A repository for managing tasks.
 */
public interface TaskRepository {

    /**
     * Retrieves a task based on the provided UUID.
     *
     * @param uuid The UUID of the task to retrieve.
     * @return The task with the specified UUID.
     */
    Task getTask(UUID uuid);

    /**
     * Saves a task to the data store.
     *
     * @param task The task to be saved.
     * @return The saved task.
     */
    Task saveTask(Task task);

    /**
     * Deletes a task from the data store, based on its UUID.
     *
     * @param uuid The UUID of the task to be deleted.
     * @return The deleted task, or null if the task does not exist.
     */
    Task deleteTask(UUID uuid);
}
