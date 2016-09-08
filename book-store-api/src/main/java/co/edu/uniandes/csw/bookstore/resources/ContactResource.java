package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.bookstore.api.IContactLogic;
import co.edu.uniandes.csw.bookstore.dtos.detail.ContactDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.ContactEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: authors/{contactsId: \\d+}/contacts/
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

    @Inject private IContactLogic contactLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("authorsId") private Long authorsId;

   
    /**
     * Convierte una lista de ContactEntity a una lista de ContactDetailDTO
     *
     * @param entityList Lista de ContactEntity a convertir
     * @return Lista de ContactDetailDTO convertida
     * @generated
     */
    private List<ContactDetailDTO> listEntity2DTO(List<ContactEntity> entityList){
        List<ContactDetailDTO> list = new ArrayList<>();
        for (ContactEntity entity : entityList) {
            list.add(new ContactDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Contact asociados a un Author
     *
     * @return Colección de objetos de ContactDetailDTO
     * @generated
     */
    @GET
    public List<ContactDetailDTO> getContacts() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", contactLogic.countContacts());
            return listEntity2DTO(contactLogic.getContacts(page, maxRecords, authorsId));
        }
        return listEntity2DTO(contactLogic.getContacts(authorsId));
    }

    /**
     * Obtiene los datos de una instancia de Contact a partir de su ID asociado a un Author
     *
     * @param contactId Identificador de la instancia a consultar
     * @return Instancia de ContactDetailDTO con los datos del Contact consultado
     * @generated
     */
    @GET
    @Path("{contactId: \\d+}")
    public ContactDetailDTO getContact(@PathParam("contactId") Long contactId) {
        ContactEntity entity = contactLogic.getContact(contactId);
        if (entity.getAuthor() != null && !authorsId.equals(entity.getAuthor().getId())) {
            throw new WebApplicationException(404);
        }
        return new ContactDetailDTO(entity);
    }

    /**
     * Asocia un Contact existente a un Author
     *
     * @param dto Objeto de ContactDetailDTO con los datos nuevos
     * @return Objeto de ContactDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ContactDetailDTO createContact(ContactDetailDTO dto) {
        return new ContactDetailDTO(contactLogic.createContact(authorsId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Contact.
     *
     * @param contactId Identificador de la instancia de Contact a modificar
     * @param dto Instancia de ContactDetailDTO con los nuevos datos.
     * @return Instancia de ContactDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{contactId: \\d+}")
    public ContactDetailDTO updateContact(@PathParam("contactId") Long contactId, ContactDetailDTO dto) {
        ContactEntity entity = dto.toEntity();
        entity.setId(contactId);
        return new ContactDetailDTO(contactLogic.updateContact(authorsId, entity));
    }

    /**
     * Elimina una instancia de Contact de la base de datos.
     *
     * @param contactId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{contactId: \\d+}")
    public void deleteContact(@PathParam("contactId") Long contactId) {
        contactLogic.deleteContact(contactId);
    }
    
}
