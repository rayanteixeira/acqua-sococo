package br.com.acqua.service;

import br.com.acqua.entity.AvatarProd;
import br.com.acqua.entity.Produto;
import br.com.acqua.repository.ProdutoRepository;
import br.com.acqua.repository.filter.ProdutoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtosRepository;

    @Autowired
    private AvatarProdService avatarService;

    public void salvar(Produto produto) {

        try {
            if (StringUtils.isEmpty(produto.getId())) {
                produto.setDataCadastro(Date.valueOf(LocalDate.now()));
                produto.setEnabled(true);
            }
            produtosRepository.save(produto);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Código de barra já existente");
        }
    }

    public Page<Produto> findByPagination(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return produtosRepository.findByEnabledOrderByIdDesc(Boolean.TRUE, pageable);
    }

    public List<Produto> findAll() {
        return produtosRepository.findAll();
    }

    public List<Produto> findEnabled() {
        return produtosRepository.findByEnabledOrderByIdDesc(Boolean.TRUE);
    }

    public void delete(Long id) {
        produtosRepository.delete(id);
    }

    public Produto findById(Long id) {
        return produtosRepository.findOne(id);
    }

    public Produto findByCodigo(ProdutoFilter filtro) {
//        String codigo = filtro.getCodigo() == null ? "%" : filtro.getCodigo();
        return produtosRepository.findByCodigoDeBarras(filtro.getCodigo());
    }

    @Transactional(readOnly = false)
    public void update(Produto produto) {

        try {
            produtosRepository.save(produto);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("algo deu errado tente mais tarde!");
        }

    }

    public Produto findByAvatar(AvatarProd avatar) {
        return produtosRepository.findByAvatar(avatar);
    }

    public void updateEnable(Long id) {
        produtosRepository.updateEnable(false, id);
    }

}
