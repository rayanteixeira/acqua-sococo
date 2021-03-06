package br.com.acqua.service;

import br.com.acqua.dto.MovimentacaoMesAnoDTO;
import br.com.acqua.entity.AvatarProd;
import br.com.acqua.entity.Movimentacao;
import br.com.acqua.entity.Produto;
import br.com.acqua.entity.Usuario;
import br.com.acqua.repository.AvatarProdRepository;
import br.com.acqua.repository.MovimentacaoRepository;
import br.com.acqua.repository.ProdutoRepository;
import br.com.acqua.repository.UserRepository;
import br.com.acqua.repository.filter.MovimentacaoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UserRepository userRepository;

    public void salvar(Movimentacao movimentacao, String userName) {

        Usuario user = userRepository.findByUsername(userName);

        try {
            if (movimentacao.getDataHora() == null) {
                movimentacao.setDataHora(new Date(System.currentTimeMillis()));
            }
            /**
             * Segurança para não substituir usuario no upodate
             */
            if (movimentacao.getUsuario() == null) movimentacao.setUsuario(user);

//            /**
//             * Caso a movimentação não tenha Avatar
//             */
//            if (movimentacao.getId() != null) {
//                if (movimentacao.getAvatar() == null) {
//                    if (movimentacao.getProduto().getAvatar() != null) {
//						AvatarProd avatar = getNovoAvatar(movimentacao.getProduto().getAvatar());
//                    	movimentacao.setAvatar(avatar);
//					}
//                }
//            }

            /**
             * Setar o avatar de Produto para a movimentação
             */
            if (StringUtils.isEmpty(movimentacao.getId())) {
                if (movimentacao.getAvatar() == null) {
                    if (movimentacao.getProduto().getAvatar() != null) {
                        AvatarProd avatar = getNovoAvatar(movimentacao.getProduto().getAvatar());
                        movimentacao.setAvatar(avatar);
                    }
                }
            };

            movimentacaoRepository.save(movimentacao);

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Falha cadastar movimentação");
        }
    }

    private AvatarProd getNovoAvatar(AvatarProd avatarProd) {
        AvatarProd avatar = new AvatarProd();
        avatar.setAvatar(avatarProd.getAvatar());
        avatar.setTipo(avatarProd.getTipo());
        avatar.setTitulo(avatarProd.getTitulo());
        return avatar;
    }

    // Ta pegando so pelo ano de 2017
    public List<MovimentacaoMesAnoDTO> getCountMovimentacoesByMesAno() {
        MovimentacaoMesAnoDTO objeto;
        List<MovimentacaoMesAnoDTO> projetosAtivos = new ArrayList<>();
        //Integer ano = 2017;

        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy");
        String ano = formatador.format(data);

        HashMap<Integer, String> mesPorNumero = new HashMap<>();
        mesPorNumero.put(1, "01");
        mesPorNumero.put(2, "02");
        mesPorNumero.put(3, "03");
        mesPorNumero.put(4, "04");
        mesPorNumero.put(5, "05");
        mesPorNumero.put(6, "06");
        mesPorNumero.put(7, "07");
        mesPorNumero.put(8, "08");
        mesPorNumero.put(9, "09");
        mesPorNumero.put(10, "10");
        mesPorNumero.put(11, "11");
        mesPorNumero.put(12, "12");

        for (int i = 1; i < 13; i++) {

            objeto = new MovimentacaoMesAnoDTO();
            Long quantidadeMovimentacoes = movimentacaoRepository.countByMovimentacoesFromMesAno(i, Integer.valueOf(ano.toString()));

            objeto.setMes(ano + "-" + mesPorNumero.get(i));
            objeto.setQuantidadeMovimentacoes(quantidadeMovimentacoes);
            objeto.setAno(Calendar.YEAR + "");
            projetosAtivos.add(objeto);

        }

        return projetosAtivos;

    }

    public Page<Movimentacao> findByPagination(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return movimentacaoRepository.findAllByOrderByIdDesc(pageable);
    }

    public static Date converterDateFim(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 23);
        c.add(Calendar.MINUTE, 59);
        return c.getTime();
    }

    public List<Movimentacao> listar() {

        return movimentacaoRepository.findTop10ByOrderByIdDesc();

    }

    public List<Movimentacao> findAll() {
        return movimentacaoRepository.findAll();
    }

    public Page<Movimentacao> pesquisar(MovimentacaoFilter filter, int page, int size) throws Throwable {

        filter = atualizarFilter(filter);

        Pageable pageable = new PageRequest(page, size);

        return movimentacaoRepository.filtrar(filter, pageable);
    }

    private MovimentacaoFilter atualizarFilter(MovimentacaoFilter filter) {

        Produto produto = produtoRepository.findByCodigoDeBarras(filter.getCodigo());

        filter.setProduto(produto);

        filter.setFim(filter.getFim() == null ? null : converterDateFim(filter.getFim()));

        Usuario usuario = userRepository.findByNome(filter.getNomeUsuario().toLowerCase());

        filter.setUsuario(usuario);

        return filter;
    }

    public void excluir(Long id) {
        movimentacaoRepository.delete(id);
    }

    public Movimentacao buscar(Long id) {
        return movimentacaoRepository.findOne(id);
    }

}
