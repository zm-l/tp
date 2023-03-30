package seedu.address.model.task.exceptions;

/**
 * Signals that the operation will result requesting unavailable todo.
 */
public class TodoNotFoundException extends RuntimeException {

    /**
     * Creates an instance of TodoNotFoundException.
     */
    public TodoNotFoundException() {
        super("Todo not found!");
    }
}