package de.marcschumacher.test.axon;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

@Slf4j
public class ToDoItem extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String id;

    public ToDoItem() {
    }

    @CommandHandler
    public ToDoItem(CreateToDoItemCommand command) {
        apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()));
    }

    @CommandHandler
    public void markCompleted(MarkCompletedCommand command) throws Exception {
        log.info("Trying to mark task completed.");
        apply(new ToDoItemCreatedEvent(id, "Test"));
        apply(new ToDoItemCompletedEvent(id));
    }

    @EventHandler
    public void on(ToDoItemCreatedEvent event) {
        this.id = event.getTodoId();
    }
}