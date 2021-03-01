package br.com.acqua.service.storage;

/**
 * @Author Jairo Nascimento
 * @Date 14/08/2020
 */

public class StorageFileNotFoundException extends StorageException {
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
