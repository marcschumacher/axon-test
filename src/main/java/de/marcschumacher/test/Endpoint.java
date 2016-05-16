package de.marcschumacher.test;

import de.marcschumacher.test.axon.CreateToDoItemCommand;
import de.marcschumacher.test.axon.MarkCompletedCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Endpoint {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping("/")
    public String run() {
        final String itemId = UUID.randomUUID().toString();
        commandGateway.send(new CreateToDoItemCommand(itemId, "Need to do this"));
        commandGateway.send(new MarkCompletedCommand(itemId));
        return "done";
    }
}
