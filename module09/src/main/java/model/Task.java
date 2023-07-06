package model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A class representing a task.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Task {

    private UUID id;
    private String owner;
    private String description;
}
