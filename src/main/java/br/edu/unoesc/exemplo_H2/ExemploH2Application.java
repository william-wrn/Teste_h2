package br.edu.unoesc.exemplo_H2;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.unoesc.exemplo_H2.model.Livro;
import br.edu.unoesc.exemplo_H2.repository.LivroRepository;

@SpringBootApplication
public class ExemploH2Application {

	public static void main(String[] args) {
		SpringApplication.run(ExemploH2Application.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(LivroRepository repositorio) {
		return args -> {
			Livro l = new Livro(null,"O senhor dos pasteis",666,"Tolkien");
			repositorio.save(l);
			System.out.println(repositorio.findAll());
			
			repositorio.save(new Livro(null,"O Hobbit",42,"J.R.R.Tolkien"));
			Optional<Livro> p = repositorio.findById(26);
			if (p.isEmpty()) {
				System.out.println("Não encontrado");
			} else {
				System.out.println(p.get());
			}
			
			for (var livro: repositorio.findByAutorContainingIgnoreCase("j")) {
				System.out.println(livro);
			}
			
			for (var livro: repositorio.porQtdPaginas(300)) {
				System.out.println(livro);
			}
			
			for (var livro: repositorio.findByFiltro("senhor")) {
				System.out.println(livro);
			}
		};
	}
}
