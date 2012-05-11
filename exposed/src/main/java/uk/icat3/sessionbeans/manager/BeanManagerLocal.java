package uk.icat3.sessionbeans.manager;

import java.util.List;

import javax.ejb.Local;

import uk.icat3.entity.EntityBaseBean;
import uk.icat3.entity.Facility;
import uk.icat3.exceptions.BadParameterException;
import uk.icat3.exceptions.IcatInternalException;
import uk.icat3.exceptions.InsufficientPrivilegesException;
import uk.icat3.exceptions.NoSuchObjectFoundException;
import uk.icat3.exceptions.ObjectAlreadyExistsException;
import uk.icat3.exceptions.SessionException;
import uk.icat3.exceptions.ValidationException;
import uk.icat3.manager.EntityInfo;

/**
 * This is the business interface for Manager enterprise bean.
 */
@Local
public interface BeanManagerLocal {

	Object create(String sessionId, EntityBaseBean bean) throws SessionException,
			InsufficientPrivilegesException, NoSuchObjectFoundException, ValidationException,
			ObjectAlreadyExistsException, IcatInternalException;

	void delete(String sessionId, EntityBaseBean bean) throws SessionException,
			InsufficientPrivilegesException, NoSuchObjectFoundException, ValidationException,
			IcatInternalException;

	void update(String sessionId, EntityBaseBean bean) throws SessionException,
			InsufficientPrivilegesException, NoSuchObjectFoundException, ValidationException,
			IcatInternalException;

	EntityBaseBean get(String sessionId, String query, Object primaryKey) throws SessionException,
			NoSuchObjectFoundException, InsufficientPrivilegesException, BadParameterException,
			IcatInternalException;

	List<?> search(String sessionId, String query) throws SessionException, IcatInternalException,
			BadParameterException, InsufficientPrivilegesException;

	void dummy(Facility facility);

	EntityInfo getEntityInfo(String beanName) throws BadParameterException, IcatInternalException;

	List<Object> createMany(String sessionId, List<EntityBaseBean> beans) throws SessionException,
			InsufficientPrivilegesException, NoSuchObjectFoundException, ValidationException,
			ObjectAlreadyExistsException, IcatInternalException;

}