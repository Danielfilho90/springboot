package school.cesar.next.project.springboot.exception.abstracts;

public abstract class NextException extends Exception {

    protected NextException(final String message) {
        super(message);
    }

}
