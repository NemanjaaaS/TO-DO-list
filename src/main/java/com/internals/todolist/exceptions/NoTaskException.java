package com.internals.todolist.exceptions;

public class NoTaskException extends Exception{

    public NoTaskException(){
        super("Task does not exist");
    }
}
