package br.com.wlucas.controleestoque.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

/**
 * Classe responsável pelo mapeamento dos valores das entidades em DTOs e
 * viceversa.
 * 
 * @author Weverton Trindade, 11/11/2022
 *
 */
public class DozerMapper {

	private DozerBeanMapper mapper;
	
	private static DozerMapper INSTANCE;
	
	public DozerMapper() {
		mapper = new DozerBeanMapper();
		configureMapper("dozer_mapping.xml");
	}
	
	public static DozerMapper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DozerMapper();
		}
		
		return INSTANCE;
	}
	
	/**
	 * Método responsável pela configuração de mapeamento entre classes
	 * com atributos com nomes diferentes.
	 * @param mappingFiles Caminhos para os arquivos xml.
	 */
	private void configureMapper(String... mappingFiles) {
	    mapper.setMappingFiles(Arrays.asList(mappingFiles));
	}

	/**
	 * Método responsável pela conversão de um objeto em outro.
	 * 
	 * @param <Origin>      Classe do objeto de origem dos dados.
	 * @param <Destination> Classe do objeto de destino dos dados.
	 * @param origin        Objeto de origem dos dados.
	 * @param destination   Objeto de destino dos dados.
	 * @return Um objeto da classe de destino com os dados do objeto de origem.
	 */
	public <Origin, Destination> Destination parseObject(Origin origin, Class<Destination> destination) {
		return mapper.map(origin, destination);
	}

	/**
	 * Método responsável pela conversão de uma lista de objetos.
	 * 
	 * @param <Origin>      Classe do objeto de origem dos dados.
	 * @param <Destination> Classe do objeto de destino dos dados.
	 * @param origin        Objeto de origem dos dados.
	 * @param destination   Objeto de destino dos dados.
	 * @return Uma lista de objetos da classe de destino com os dados da lista de
	 *         objetos de origem.
	 */
	public <Origin, Destination> List<Destination> parseListObjects(List<Origin> origin,
			Class<Destination> destination) {
		List<Destination> destinationObjects = new ArrayList<Destination>();

		origin.stream().forEach(o -> destinationObjects.add(mapper.map(o, destination)));
			
		return destinationObjects;
	}
}
