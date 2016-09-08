package co.edu.uniandes.csw.bookstore.ejbs;

import co.edu.uniandes.csw.bookstore.api.IContactLogic;
import co.edu.uniandes.csw.bookstore.entities.ContactEntity;
import co.edu.uniandes.csw.bookstore.persistence.ContactPersistence;
import co.edu.uniandes.csw.bookstore.api.IAuthorLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class ContactLogic implements IContactLogic {

    @Inject private ContactPersistence persistence;

    @Inject
    private IAuthorLogic authorLogic;

    /**
     * Obtiene el número de registros de Contact.
     *
     * @return Número de registros de Contact.
     * @generated
     */
    public int countContacts() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Contact que pertenecen a un Author.
     *
     * @param authorid id del Author el cual es padre de los Contacts.
     * @return Colección de objetos de ContactEntity.
     * @generated
     */
    @Override
    public List<ContactEntity> getContacts(Long authorid) {
        AuthorEntity author = authorLogic.getAuthor(authorid);
        return author.getContacts();
    }

    /**
     * Obtiene la lista de los registros de Contact que pertenecen a un Author indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param authorid id del Author el cual es padre de los Contacts.
     * @return Colección de objetos de ContactEntity.
     * @generated
     */
    @Override
    public List<ContactEntity> getContacts(Integer page, Integer maxRecords, Long authorid) {
        return persistence.findAll(page, maxRecords, authorid);
    }

    /**
     * Obtiene los datos de una instancia de Contact a partir de su ID.
     *
     * @pre La existencia del elemento padre Author se debe garantizar.
     * @param contactid) Identificador del Contact a consultar
     * @return Instancia de ContactEntity con los datos del Contact consultado.
     * @generated
     */
    @Override
    public ContactEntity getContact(Long contactid) {
        try {
            return persistence.find(contactid);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El Contact no existe");
        }
    }

    /**
     * Se encarga de crear un Contact en la base de datos.
     *
     * @param entity Objeto de ContactEntity con los datos nuevos
     * @param authorid id del Author el cual sera padre del nuevo Contact.
     * @return Objeto de ContactEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public ContactEntity createContact(Long authorid, ContactEntity entity) {
        AuthorEntity author = authorLogic.getAuthor(authorid);
        entity.setAuthor(author);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Contact.
     *
     * @param entity Instancia de ContactEntity con los nuevos datos.
     * @param authorid id del Author el cual sera padre del Contact actualizado.
     * @return Instancia de ContactEntity con los datos actualizados.
     * @generated
     */
    @Override
    public ContactEntity updateContact(Long authorid, ContactEntity entity) {
        AuthorEntity author = authorLogic.getAuthor(authorid);
        entity.setAuthor(author);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Contact de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param authorid id del Author el cual es padre del Contact.
     * @generated
     */
    @Override
    public void deleteContact(Long id) {
        ContactEntity old = getContact(id);
        persistence.delete(old.getId());
    }
  
}