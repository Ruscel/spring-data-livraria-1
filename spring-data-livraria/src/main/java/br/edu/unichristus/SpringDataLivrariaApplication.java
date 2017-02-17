package br.edu.unichristus;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataLivrariaApplication implements CommandLineRunner {

	@Autowired
	private LivroService servicoLivros;

	@Autowired
	private EditoraService servicoEditoras;

	@Override
	public void run(String... args) throws ParseException {

		/* ********************** CRUD DE LIVROS ********************** */

		// Incluindo um novo livro
		Livro livro = new Livro("Java Como Programar", LocalDate.now(), 250, new BigDecimal("15.00"));
		this.servicoLivros.salvar(livro);

		// Buscando um livro pelo seu ID
		Livro livro1 = this.servicoLivros.buscarPeloID(1L);
		System.out.println(livro1);

		// Buscando vários livros pelos seus IDs (1, 3 e 5)
		List<Livro> livros2 = this.servicoLivros.buscarPelosIDs(new ArrayList<Long>() {
			{
				add(1L);
				add(3L);
				add(5L);
			}
		});
		livros2.forEach(l -> System.out.println(l));

		// Alterando o livro com o ID = 1
		Livro livro3 = this.servicoLivros.buscarPeloID(1L);
		System.out.println(livro3);
		livro3.setTitulo("Pro Spring");
		this.servicoLivros.salvar(livro3);
		System.out.println(livro3);

		// Fazendo uma nova inserção a partir de um livro já existente (id = 1)
		Livro livro4 = this.servicoLivros.buscarPeloID(1L);
		System.out.println(livro4);
		livro4.setTitulo("Pro Spring");
		livro4.setLivroID(100L);
		this.servicoLivros.salvar(livro4);
		System.out.println(livro4);

		// Removendo 2 livros por métodos diferentes
		this.servicoLivros.remover(1L);
		this.servicoLivros.remover(this.servicoLivros.buscarPeloID(2L));

		// Buscando um livro pelo título
		Livro livro5 = this.servicoLivros.buscarPeloTitulo("Guerra e Paz");
		System.out.println(livro5);

		// Buscando livros cujos títulos contenham uma string - usando like
		List<Livro> livros6 = this.servicoLivros.buscarPeloTituloLike("%de%");
		livros6.forEach(l -> System.out.println(l));

		// Buscando livros cujos títulos contenham uma string - usando Containing
		this.servicoLivros.buscarPeloTituloContendo("de").forEach(l -> System.out.println(l));

		// Buscando livros cujos títulos iniciem por uma string
		this.servicoLivros.buscarPeloTituloComecandoCom("um").forEach(l -> System.out.println(l));

		// Buscando livros cujos títulos terminem com uma string
		for (Livro l : this.servicoLivros.buscarPeloTituloTerminandoCom("Paz")) {
			System.out.println(l);
		}

		// Buscando livros pelo título ignorando a capitalização
		for (Livro l : this.servicoLivros.buscarPeloTituloIgnorandoCaixa("GUERRA E PAZ")) {
			System.out.println(l);
		}

		// Buscando livros que tenham um número exato de páginas
		for (Livro l : this.servicoLivros.buscarPeloNumeroDePaginas(300)) {
			System.out.println(l);
		}

		// Buscando livros que tenham mais páginas do que o número informado
		for (Livro l : this.servicoLivros.buscarPeloNumeroDePaginasMaiorQue(300)) {
			System.out.println(l);
		}

		// Buscando livros que tenham o número de páginas dentro do intervalo
		// informado
		for (Livro l : this.servicoLivros.buscarPeloNumeroDePaginasIntervalo(170, 300)) {
			System.out.println(l);
		}

		// Buscando livros cujos titulos contenham pelo menos uma das strings
		// informadas
		for (Livro l : this.servicoLivros.buscarPeloTituloContendo1ou2("de", "um")) {
			System.out.println(l);
		}

		// Buscando livros cujos titulos não sejam o título informado
		for (Livro l : this.servicoLivros.buscarPeloTituloDiferenteDe("Guerra e Paz")) {
			System.out.println(l);
		}

		// Buscando livros que contenham uma String e que tenham mais páginas
		// que o valor informado
		for (Livro l : this.servicoLivros.buscarPeloTituloContendoENumeroDePaginasMaiorQue("de", 200)) {
			System.out.println(l);
		}

		// Buscando livros publicados após uma data
		for (Livro l : this.servicoLivros.buscarPelaDataPublicacaoDepoisDe(LocalDate.of(1995, 1, 1))) {
			System.out.println(l);
		}

		// Buscando livros publicados antes de uma data
		for (Livro l : this.servicoLivros.buscarPelaDataPublicacaoAntesDe(LocalDate.of(1972, 12, 31))) {
			System.out.println(l);
		}

		// Buscando livros publicados entre duas datas
		for (Livro l : this.servicoLivros.buscarPelaDataPublicacaoEntre(LocalDate.of(1943, 1, 1),
				LocalDate.of(1955, 11, 15))) {
			System.out.println(l);
		}

		// Buscando livros em ordem crescente de título
		for (Livro l : this.servicoLivros.buscarPeloTituloContendoOrdemCrescente("de")) {
			System.out.println(l);
		}

		// Buscando livros em ordem decrescente de título
		for (Livro l : this.servicoLivros.buscarPeloTituloContendoOrdemDecrescente("de")) {
			System.out.println(l);
		}

		/* ********************** CRUD DE EDITORAS ********************** */

		// Incluindo uma nova editora
		Editora e = new Editora("Érica", "Rio de Janeiro", 1937);
		this.servicoEditoras.salvar(e);

		Editora e1=servicoEditoras.buscarPeloTitulo("Bookman");
		
		e1.setCidade("Porto Alegre");
		this.servicoEditoras.salvar(e1);
				
		System.out.println(e1);
		
	}
	

	public static void main(String[] args) {
		SpringApplication.run(SpringDataLivrariaApplication.class, args);
	}
}
