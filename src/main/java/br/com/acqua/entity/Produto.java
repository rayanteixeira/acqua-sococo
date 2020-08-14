package br.com.acqua.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import br.com.acqua.entity.enuns.Categoria;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Jairo Sousa 06/01/2017
 */

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O NOME é obrigatorio")
	@Column(name = "nome", nullable = false)
	private String nome;

	@NotEmpty(message = "O CÓDIGO DE BARRAS é obrigatorio")
	@Column(name = "codigo_de_barras", nullable = false, unique = true)
	private String codigoDeBarras;

	@NotEmpty(message = "A DESCRIÇÃO é obrigatoria")
	@Column(name = "descricao")
	private String descricao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@OneToOne(cascade = { CascadeType.ALL, CascadeType.REMOVE }, optional = true)
	@JoinColumn(name = "avatar_id")
	public AvatarProd avatar;

	@OneToOne(cascade = { CascadeType.ALL, CascadeType.REMOVE }, optional = true)
	@JoinColumn(name = "layout_id")
	public LayoutProd layout;

	@Column(name = "imagem_content_type")
	private String imagemContentType;
	
	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "categoria")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@Column(name = "marca")
	private String marca;

	@Column(name = "volume")
	private String volume;

	@OneToMany(mappedBy = "produto", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Movimentacao> movimentacoes = new ArrayList<>();
	
	public boolean isAtivo() {
		return this.enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public AvatarProd getAvatar() {
		return avatar;
	}

	public void setAvatar(AvatarProd avatar) {
		this.avatar = avatar;
	}

	public String getImagemContentType() {
		return imagemContentType;
	}

	public void setImagemContentType(String imagemContentType) {
		this.imagemContentType = imagemContentType;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public LayoutProd getLayout() {
		return layout;
	}

	public void setLayout(LayoutProd layout) {
		this.layout = layout;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Produto produto = (Produto) o;
		if (produto.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), produto.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String  toString() {
		return "Produto{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", codigoDeBarras='" + codigoDeBarras + '\'' +
				", descricao='" + descricao + '\'' +
				", dataCadastro=" + dataCadastro +
				", avatar=" + avatar +
				", imagemContentType='" + imagemContentType + '\'' +
				", enabled=" + enabled +
				", categoria=" + categoria +
				", marca='" + marca + '\'' +
				", volume='" + volume + '\'' +
				'}';
	}
}
