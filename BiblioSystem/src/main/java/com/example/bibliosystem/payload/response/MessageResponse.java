package com.example.bibliosystem.payload.response;


/**
 * Définition de la signature d'une réponse permettant de retourner un simple message
 * (en général un message de succès ou d'erreur)
 */
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
