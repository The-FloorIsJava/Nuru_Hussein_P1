package Com.Revature.ExpenseReimbursmentSoftware.Util.Interface;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface CrudOperation<T, Q> {


    //Create
    T create(T newObject);

    //Read or get lists of all item
    List<T> getAll();

    //Access record by field
    T getByField(String ObjectField, Q value);

    //Update
    boolean updated(T updatedObject);

    //DELETE
    void delete(T getObjectByField);
}
