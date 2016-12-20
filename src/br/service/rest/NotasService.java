package br.service.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.service.dao.NotaDao;
import br.service.entidade.Nota;

@Path("/notas") // caminho url para direcionar requisição
public class NotasService {
	private NotaDao notadao;
	
	private static final String CHARSET_UTF8 =";charset=utf-8"; // tratando traços e acentos pt-br
	
	@PostConstruct // trabalha como o metodo construtor
	private void init(){
		notadao = new NotaDao();
	}
	/*
	 * LISTANDO AS NOTAS
	 */
	@GET // metodo http que o serviço aceita 
	@Path("/list") // url caminho do serviço
	@Produces(MediaType.APPLICATION_JSON ) // oque vai ser produzido , tipos de dados que sera 
										// manipulado no serviço
	public List<Nota> listarnotas(){
		List<Nota> lista = null;
		try{
			lista = notadao.listaNotas();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	return lista;
	}
	/*
	 * ADICIONANDO UMA NOTA
	 */
	
	@POST // metodo http aceitado
	@Path("/add") // caminho url
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8) // oque vc vai receber nesse caso é um json
										 // caso esteja em formato diferente da erro ao inserir no BD
	@Produces(MediaType.TEXT_PLAIN) // resposta em texto plano chave e valor 
	public String addNota(Nota nota){
		String msg = "";
		System.out.println(nota.getTitulo());
		try{
			// adicionando uma nova nota com base no metodo da classe DAO
			notadao.addNota(nota);
			msg = "Nota Adicionada com succeso";
		}catch(Exception ex){
			msg = "Erro ao Adicionar Nota !! ";
					ex.printStackTrace();
		}
		return msg;
	}
	
	/*
	 * BUSCANDO DADOS POR ATRIBUTO IDENTIFICADOR (ID)
	 */
	
	@GET // tipo de requisição aceitada
	@Path("/get/{id}") // url com parametro de pesquisa que nessa situação é a ID da nota 
	@Consumes(MediaType.TEXT_PLAIN) // nesta situação esta recebendo um valor inteiro 
									// entao é passado um texto plano
	@Produces(MediaType.APPLICATION_JSON) // sera retornado uma nota que no caso é um JSON
	
	public Nota buscarPorId(@PathParam("id") int idNota){
		
		Nota nota = null;
		try{
			nota = notadao.buscarNotaPorId(idNota);
		}catch(Exception e){
			e.printStackTrace();
		}
		return nota;
	}
	
	/*
	 * EDITANDO NOTAS
	 */
	@PUT
	@Path("/edit/{id}") // url
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8) // tipo de dado que estou enviando 
	@Produces(MediaType.TEXT_PLAIN) // tipo que estou recebendo 
	public String editarNota(Nota nota,@PathParam("id") int idNota){
		String msg = "";
		System.out.println(nota.getTitulo());
		try{
			notadao.editarNota(nota,idNota);
			msg = "Nota editada com sucesso !!";
		}catch(Exception e){
			msg = "Erro ao editar Nota !";
					e.printStackTrace();
		}
		return msg;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerNota(@PathParam("id") int idNota) {
		String msg = "";
		
		try {
			notadao.removeNota(idNota);
			
			msg = "Nota removida com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover a nota!";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	
	
}
