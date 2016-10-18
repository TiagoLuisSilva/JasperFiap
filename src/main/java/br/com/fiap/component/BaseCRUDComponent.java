package br.com.fiap.component;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.exceptions.ValidarException;

/**
 * Component base onde deixa os métodos que devem sempre existir para um CRUD generico.
 * 
 * @author Paulo Porto
 *
 * @param <T>
 */
//Realiza o rollback no banco caso aconteca qualquer exception , para que não fico com dados inconsistentes, do tipo deu baixa no estoque mas não efetuou o pedido por problema de contraint
@Transactional(rollbackFor = Exception.class)
public abstract class BaseCRUDComponent<T> {
	static Logger log = Logger.getLogger(BaseCRUDComponent.class.getName());
			
    @PersistenceContext
    protected EntityManager entityManager;
    
    private CrudRepository<T, Long> DAO;
    
    public void setBaseRepository(CrudRepository<T, Long> dao) {
    	this.DAO = dao;
	}
    
    public List<T> buscarTodos() throws ValidarException{
    	try {
    		List<T> lista = (List<T>) DAO.findAll();
    		return lista ;
		} catch (Exception e) {
			throw new ValidarException(e.getMessage());
		}
	}
    
    public T buscarPorId(Long id) throws ValidarException{
    	try {
    		T object = DAO.findOne(id);

    		return object;
		} catch (Exception e) {
			throw new ValidarException(e.getMessage());
		}
    }
    
	
    public T persistir(T object) throws ValidarException{
    	validarCadastro(object);

    	try {
    		return DAO.save(object);	 
		} catch (Exception e) {
			log.error("Erro: " + e.getMessage()); e.printStackTrace();
			throw  new ValidarException(e.getMessage());
		}
    }
    
    public void atualizar(T object) throws ValidarException{
    	try {
    		DAO.save(object);	 
		} catch (Exception e) {
			log.error("Erro: " + e.getMessage()); e.printStackTrace();
			throw  new ValidarException(e.getMessage());
		}
    }

    public void remover(Long id)throws ValidarException {
    	try {
    		T object = DAO.findOne(id);
    		DAO.delete(object);
		} catch (Exception e) {
			throw new ValidarException(e.getMessage());
		}
    }
    
    protected abstract void validarCadastro(T object) throws ValidarException;
}
