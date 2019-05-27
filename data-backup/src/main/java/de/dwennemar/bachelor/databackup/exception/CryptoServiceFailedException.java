package de.dwennemar.bachelor.databackup.exception;

public class CryptoServiceFailedException extends Exception {
    public CryptoServiceFailedException() {
        super("CryptoService has failed!");
    }
}
