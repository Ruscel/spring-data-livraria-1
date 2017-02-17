package br.edu.unichristus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {

	@Autowired
	private EditoraRepository repo;

	public void salvar(Editora e) {
		this.repo.save(e);
	}
	
	public Editora buscarPeloTitulo(String nome){
		return this.repo.findByNome(nome);
		
	}
	
	
}
