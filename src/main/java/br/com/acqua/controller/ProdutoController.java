package br.com.acqua.controller;

import br.com.acqua.entity.AvatarProd;
import br.com.acqua.entity.Produto;
import br.com.acqua.entity.enuns.Categoria;
import br.com.acqua.entity.paginator.Pager;
import br.com.acqua.service.AvatarProdService;
import br.com.acqua.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private static final int BUTTONS_TO_SHOW = 15;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 20;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    private static final String CADASTRO_VIEW = "produto/produto-cadastro";
    private static final String ATUALIZAR_VIEW = "produto/produto-atualizar";
    private static final String PRODUTOS_VIEW = "redirect:/produtos";

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private AvatarProdService avatarService;

    @GetMapping
    public ModelAndView showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page) {

        ModelAndView modelAndView = new ModelAndView("produto/produtos");

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Produto> produtos = produtoService.findByPagination(evalPage, evalPageSize);
        Pager pager = new Pager(produtos.getTotalPages(), produtos.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("produtos", produtos);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView view = new ModelAndView(CADASTRO_VIEW);
        view.addObject("produto", new Produto());
        return view;
    }

    @PostMapping
    public String salvar(@Validated Produto produto, Errors erros, RedirectAttributes attributes,
                         @RequestParam(value = "file", required = false) MultipartFile file) {

        if (erros.hasErrors()) {
            return CADASTRO_VIEW;
        }

        if (!file.isEmpty()) {
            AvatarProd avatar = avatarService.getAvatarByUpload(file);
            produto.setAvatar(avatar);
        }

        try {
            produtoService.salvar(produto);
            attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
            return "redirect:/produtos";

        } catch (IllegalArgumentException e) {
            erros.rejectValue("Erro no cadastro", null, e.getMessage());
            return CADASTRO_VIEW;
        }
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView perfil(@PathVariable("id") Produto produto) {
        ModelAndView view = new ModelAndView();
        view.setViewName("produto/produto-detalhes");
        view.addObject("produto", produto);
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView preeditar(@PathVariable("id") Produto produto) {
        ModelAndView view = new ModelAndView(ATUALIZAR_VIEW);
        view.addObject(produto);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView editar(@Validated @ModelAttribute("produto") Produto produto,
                               Errors erros, RedirectAttributes attributes,
                               @RequestParam(value = "file", required = false) MultipartFile file) {

        ModelAndView view = new ModelAndView(PRODUTOS_VIEW);

        if (erros.hasErrors()) {
            return new ModelAndView(CADASTRO_VIEW);
        }

        if (!file.isEmpty()) {
            AvatarProd avatar = avatarService.getAvatarByUpload(file);
            avatar.setId(produto.getAvatar().getId());
            produto.setAvatar(avatar);
        }

        try {
            produtoService.update(produto);
            attributes.addFlashAttribute("mensagem", "Produto atualizado com sucesso!");

            return view;
        } catch (IllegalArgumentException e) {
            erros.rejectValue("Erro no cadastro", null, e.getMessage());
            return view;
        }

    }

    @DeleteMapping("{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
        System.out.println("delete");
        produtoService.delete(id);
        attributes.addFlashAttribute("mensagem", "Produto excluido com sucesso!");
        return "redirect:/produtos";
    }

    @PostMapping("/enabled/{id}")
    public String updateEnabled(@PathVariable("id") Long id, RedirectAttributes attributes) {
        System.out.println("post");
        produtoService.updateEnable(id);
        attributes.addFlashAttribute("mensagem", "Produto excluido com sucesso!");
        return "redirect:/produtos";
    }

    @ModelAttribute("categorias")
    public List<Categoria> todoStatusTitulo() {
        return Arrays.asList(Categoria.values());
    }

}