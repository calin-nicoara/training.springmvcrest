package ro.cni.training.springmvcrest.rest;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("Object not found!");
    }
}
