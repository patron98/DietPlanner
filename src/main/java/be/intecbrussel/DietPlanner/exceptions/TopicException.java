package be.intecbrussel.DietPlanner.exceptions;

public class TopicException extends RuntimeException {

    // we dont want to expose inside data -> exception
    public TopicException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public TopicException(String exMessage) {
        super(exMessage);
    }
}
