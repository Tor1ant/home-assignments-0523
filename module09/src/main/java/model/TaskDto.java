package model;

/**
 * Represents a data transfer object for a task.
 *
 * @param owner The owner of the task.
 * @param description The description of the task.
 */
public record TaskDto(String owner, String description) {

}
