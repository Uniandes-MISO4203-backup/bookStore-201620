package co.edu.uniandes.csw.bookstore.api;

import co.edu.uniandes.csw.bookstore.entities.ContactEntity;
import java.util.List;

public interface IContactLogic {
    public int countContacts();
    public List<ContactEntity> getContacts(Long bookid);
    public List<ContactEntity> getContacts(Integer page, Integer maxRecords, Long bookid);
    public ContactEntity getContact(Long contactid);
    public ContactEntity createContact(Long bookid, ContactEntity entity);
    public ContactEntity updateContact(Long bookid, ContactEntity entity);
    public void deleteContact(Long id);
}