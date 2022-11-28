package Com.Revature.ExpenseReimbursmentSoftware.Util.Interface;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface CrudOperation<T> {


    //Create
    T create(T newObject);

    //Read or get lists of all item
    List<T> getAll();

    //Access record by field
    T getByField(String ObjectField);

    //Update
    boolean updated(T updatedObject);

    //DELETE
    T delete(String getObjectByField);
}
