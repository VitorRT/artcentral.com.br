package br.com.artcentral.mvc.system.generics;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public abstract class AbstractGenericCrudService<T, L, E, R extends JpaRepository<E, UUID>> implements GenericCrud<T, L, E> {
    @Autowired
    protected R repository;
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    //@Autowired
    //protected PasswordEncoder encoder;

    /**
     * Atributos que podem ser usados pelas classes de serviço
     * */

    protected boolean m_executaAposCriacao      = false;
    protected boolean m_executaAposAtualizacao  = false;
    protected boolean m_executaAposListagem     = false;
    protected boolean m_executaAposComParametro = false;
    protected boolean executaAposApagar         = false;
    protected boolean executaAposDetalhar       = false;

    protected boolean m_validaSenha = false;
    protected boolean m_validaValoresNulos = false;

    /**
     * Métodos executados automaticamente pela classe genérica.
     * */

    @Override
    @Transactional
    public T doCreate(Object payload) {
        validaValoresNulos(payload);
        E entity = createEntityFromPayload(payload);
        entity = repository.save(entity);

        if(m_executaAposCriacao) {
        	if(m_executaAposComParametro) 
        		doAfterCreateEntity(entity);
        	else
        		doAfterCreateEntity();
        }
        return convertToDto(entity);
    }

    @Override
    @Transactional
    public T doUpdateById(Object payload, UUID id) {
        validaValoresNulos(payload);
        E entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        updateEntityFromPayload(entity, payload);

        entity = repository.save(entity);

        if(m_executaAposAtualizacao) {
            doAfterUpdateEntity();
        }
        return convertToDto(entity);
    }

    @Override
    public L getSimpleList() {
        List<E> entities = repository.findAll();

        if(m_executaAposListagem) {
            doAfterListEntity();
        }
        return convertToListDto(entities);
    }

    @Override
    public T detailsById(UUID id) {
        E entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));

        if(executaAposDetalhar) {
            doAfterDetailsEntity();
        }
        return convertToDto(entity);
    }

    @Override
    public void doDeleteById(UUID id) {
        repository.deleteById(id);
        if(executaAposApagar) {
            doAfterDeleteEntity();
        }
    }

    /**
     * Métodos opcionais que a classe de serviço pode implementar
     * */

    protected void doAfterCreateEntity()  { }
    protected void doAfterCreateEntity(E entity)  { }
    protected void doAfterUpdateEntity()  { }
    protected void doAfterListEntity()    { }
    protected void doAfterDetailsEntity() { }
    protected void doAfterDeleteEntity()  { }

    public E findEntityById(UUID entityId) {
       E entity = repository.findById(entityId).orElseThrow(() -> new RuntimeException("Entity not found"));
       return entity;
    }
    
    public E findEntityByTag(String tag) {
    	return null;
    }
    
    public E convertoDtoToEntity(T dto) {
    	return null;
    }

    /**
     * Métodos implementados pela classe de serviço:
     * */

    protected abstract E createEntityFromPayload(Object payload);

    protected abstract void updateEntityFromPayload(E entity, Object payload);

    protected abstract T convertToDto(E entity);

    protected abstract L convertToListDto(List<E> entities);


    /**
     * Métodos privados da classe genérica
     * */

    private void validaValoresNulos(Object payload) {
        if(m_validaValoresNulos) {
            try {
                GenericValidator.validaValoresNulos(payload);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
