package com.example.coach.api;

public class ResponseApi<T> {
    private int code;
    private String message;
    private T result;

    /**
     * Getter pour le code renvoyé par la réponse de l'Api
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter pour le message renvoyé par la réponse de l'Api
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Getter pour le résultat renvoyé par la réponse de l'Api. Peut être de types variables.
     * @return
     */
    public T getResult() {
        return result;
    }
}
