package br.com.acqua.service.storage;

/**
 * @Author Jairo Nascimento
 * @Date 14/08/2020
 */

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
