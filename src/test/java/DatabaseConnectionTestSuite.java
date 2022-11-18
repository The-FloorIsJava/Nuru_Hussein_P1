import Com.Revature.ExpenseReimbursmentSoftware.Util.DatabaseConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTestSuite {

    @Test
    public void test_getConnection_returnValidConnection_givenProvidedCredentialsAreCorrect() {
        try(Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
            System.out.println(connection);
            Assert.assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
