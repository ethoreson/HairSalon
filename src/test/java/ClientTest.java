import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client newClient = new Client("Client1", "new client");
    assertEquals(true, newClient instanceof Client);
  }

  @Test
  public void Client_instantiatesWithName_String() {
    Client newClient = new Client("Client1", "new client");
    assertEquals("Client1", newClient.getName());
  }

  @Test
  public void Client_instantiatesWithDetails_String() {
    Client newClient = new Client("Client1", "new client");
    assertEquals("new client", newClient.getDetails());
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Client1", "new client");
    Client secondClient = new Client("Client2", "second client");
    assertEquals(true, Client.all().contains(firstClient));
    assertEquals(true, Client.all().contains(secondClient));
  }

  @Test
  public void clear_emptiesAllClientsFromArrayList_0() {
    Client firstClient = new Client("Client1", "new client");
    Client.clear();
    assertEquals(0, Client.all().size());
  }

  @Test
  public void getId_clientInstantiateWithAnID_1() {
    Client firstClient = new Client("Client1", "new client");
    assertEquals(1, firstClient.getId());
  }

  @Test
  public void find_returnsclientWithSameId_secondTask() {
    Client firstClient = new Client("Client1", "new client");
    Client secondClient = new Client("Client2", "second client");
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

}
