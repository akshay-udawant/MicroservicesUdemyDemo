package net.javaguides.departmentservice.exception;

public class DepartmentAlreadyExistsException extends RuntimeException {
    private String message;

    public DepartmentAlreadyExistsException(String message) {
        super(message);
    }
}
