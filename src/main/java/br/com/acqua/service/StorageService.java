package br.com.acqua.service;

import br.com.acqua.entity.LayoutProd;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @Author Jairo Nascimento
 * @Date 14/08/2020
 */

public interface StorageService {

    LayoutProd store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void delete(String filename);

    void init();
}
