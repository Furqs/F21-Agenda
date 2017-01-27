package br.com.caelum.tarefas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.caelum.tarefas.dao.JdbcTarefaDao;
import br.com.caelum.tarefas.modelo.Tarefa;

@Controller
public class TarefasController
{
	private JdbcTarefaDao dao;
	
	public TarefasController()
	{
		dao = new JdbcTarefaDao();
	}
	
	@RequestMapping("novaTarefa")
	public String form()
	{
		return "formulario";
	}
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result)
	{
		if(result.hasFieldErrors("descricao"))
		{
			return "formulario";
		}
		dao.adiciona(tarefa);
		return "adicionada";
	}
	@RequestMapping("listaTarefas")
	public String lista(Model model)
	{
		model.addAttribute("tarefas", dao.lista());
		return "lista";
		
	}
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa)
	{
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}
	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model)
	{
		
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "mostra";
	}
	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa)
	{
		dao.altera(tarefa);
		return "redirect:listaTarefas"; 
	}
	
	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model)
	{
		dao.finaliza(id);
		model.addAttribute("tarefa",dao.buscaPorId(id));
		return "finalizada";
		
	}
}
