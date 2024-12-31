import com.onlinebanking.dao.CustomerDAO;  // Make sure to import the correct DAO
import com.onlinebanking.model.Customer;   // Import the Customer model class
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    private CustomerDAO customerDAO;

    @SuppressWarnings("deprecation")
	@Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);  // Initializes mocks

        // Initialize the DAO with the mock connection
        customerDAO = new CustomerDAO(mockConnection);

        // Mock common behaviors for PreparedStatement and ResultSet
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.createStatement()).thenReturn(mockPreparedStatement);
    }

    @Test
    public void testSaveCustomer() throws SQLException {
        // Prepare test customer object
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setAddress("123 Main St");
        customer.setDateOfBirth(java.time.LocalDate.of(1990, 1, 1));

        String sql = "INSERT INTO Customers (first_name, last_name, email, phone_number, address, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";

        // Mocking PreparedStatement to set parameters
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        // Call the method
        customerDAO.save(customer);

        // Verifying that the PreparedStatement executeUpdate method was called
        verify(mockPreparedStatement).setString(1, "John");
        verify(mockPreparedStatement).setString(2, "Doe");
        verify(mockPreparedStatement).setString(3, "john.doe@example.com");
        verify(mockPreparedStatement).setString(4, "1234567890");
        verify(mockPreparedStatement).setString(5, "123 Main St");
        verify(mockPreparedStatement).setDate(6, java.sql.Date.valueOf("1990-01-01"));
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testGetCustomerById() throws SQLException {
        // Mock the ResultSet to simulate the database response
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);  // Simulate one row in the result set
        when(mockResultSet.getInt("customer_id")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("John");
        when(mockResultSet.getString("last_name")).thenReturn("Doe");
        when(mockResultSet.getString("email")).thenReturn("john.doe@example.com");
        when(mockResultSet.getString("phone_number")).thenReturn("1234567890");
        when(mockResultSet.getString("address")).thenReturn("123 Main St");
        when(mockResultSet.getDate("date_of_birth")).thenReturn(Date.valueOf("1990-01-01"));
        
        // Prepare the SQL statement for the "getById" method
        String sql = "SELECT * FROM Customers WHERE customer_id = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);  // Return the mock result set when executeQuery() is called

        // Call the method
        Customer customer = customerDAO.getById(1);

        // Assert that the customer object is not null and its properties are correct
        assertNotNull(customer);
        assertEquals(1, customer.getCustomerId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals(java.time.LocalDate.of(1990, 1, 1), customer.getDateOfBirth());
    }


    @Test
    public void testGetAllCustomers() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false); // 2 rows

        when(mockResultSet.getInt("customer_id")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getString("first_name")).thenReturn("John").thenReturn("Jane");
        when(mockResultSet.getString("last_name")).thenReturn("Doe").thenReturn("Smith");
        when(mockResultSet.getString("email")).thenReturn("john.doe@example.com").thenReturn("jane.smith@example.com");

        when(mockConnection.createStatement()).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Call the method
        List<Customer> customers = customerDAO.getAll();

        assertNotNull(customers);
        assertEquals(2, customers.size());
        assertEquals("John", customers.get(0).getFirstName());
        assertEquals("Jane", customers.get(1).getFirstName());
    }

    @Test
    public void testUpdateCustomer() throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setAddress("123 Main St");
        customer.setDateOfBirth(java.time.LocalDate.of(1990, 1, 1));

        String sql = "UPDATE Customers SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ?, date_of_birth = ? WHERE customer_id = ?";

        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        // Call the method
        customerDAO.update(customer);

        // Verifying that the PreparedStatement executeUpdate method was called
        verify(mockPreparedStatement).setInt(7, 1);  // customer ID
        verify(mockPreparedStatement).setString(1, "John");
        verify(mockPreparedStatement).setString(2, "Doe");
        verify(mockPreparedStatement).setString(3, "john.doe@example.com");
        verify(mockPreparedStatement).setString(4, "1234567890");
        verify(mockPreparedStatement).setString(5, "123 Main St");
        verify(mockPreparedStatement).setDate(6, java.sql.Date.valueOf("1990-01-01"));
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteCustomer() throws SQLException {
        String sql = "DELETE FROM Customers WHERE customer_id = ?";

        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        // Call delete method
        customerDAO.delete(1);  // Calling delete method with customer ID 1

        // Verifying that the PreparedStatement executeUpdate method was called
        verify(mockPreparedStatement).setInt(1, 1);  // customer ID
        verify(mockPreparedStatement).executeUpdate();
    }
}
