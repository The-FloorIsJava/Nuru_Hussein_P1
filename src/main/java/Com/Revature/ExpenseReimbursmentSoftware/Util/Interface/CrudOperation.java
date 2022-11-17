package Com.Revature.ExpenseReimbursmentSoftware.Util.Interface;

import java.util.List;

public interface CrudOperation<T> {


    //Create
    T create(T newObject);

    //Read
    List<T> getAll();
    T getById();

    //Update
    boolean updated(T updatedObject);

    //Delete
    boolean delete(int employeeId);
}
