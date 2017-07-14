import org.sql2o.*;
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
      String deleteClientsQuery = "DELETE FROM clients *;";
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteClientsQuery).executeUpdate();
      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client newClient = new Client("Client1", "new client", 1);
    assertEquals(true, newClient instanceof Client);
  }

  @Test
  public void Client_instantiatesWithName_String() {
    Client newClient = new Client("Client1", "new client", 1);
    assertEquals("Client1", newClient.getName());
  }

  @Test
  public void Client_instantiatesWithDetails_String() {
    Client newClient = new Client("Client1", "new client", 1);
    assertEquals("new client", newClient.getDetails());
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Client1", "new client", 1);
    firstClient.save();
    Client secondClient = new Client("Client2", "second client", 1);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  // @Test
  // public void clear_emptiesAllClientsFromArrayList_0() {
  //   Client firstClient = new Client("Client1", "new client", 1);
  //   Client.clear();
  //   assertEquals(0, Client.all().size());
  // }

  @Test
  public void getId_clientInstantiateWithAnID() {
    Client firstClient = new Client("Client1", "new client", 1);
    firstClient.save();
    assertTrue(firstClient.getId() > 0);
  }

  @Test
  public void find_returnsclientWithSameId_secondTask() {
    Client firstClient = new Client("Client1", "new client", 1);
    firstClient.save();
    Client secondClient = new Client("Client2", "second client", 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void equals_returnsTrueIfDetailsAretheSame() {
    Client firstClient = new Client("Client1", "new client", 1);
    Client secondClient = new Client("Client1", "new client", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    Client firstClient = new Client("Client1", "new client", 1);
    firstClient.save();
    assertTrue(Client.all().get(0).equals(firstClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client firstClient = new Client("Client1", "new client", 1);
    firstClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(firstClient.getId(), savedClient.getId());
  }

  @Test
  public void find_returnsNullWhenNoClientFound_null() {
    assertTrue(Client.find(999) == null);
  }

  @Test
  public void update_updatesTaskDescription_true() {
    Client firstClient = new Client("Client1", "new client", 1);
    firstClient.save();
    firstClient.update("Client1", "favorite client", 1);
    assertEquals("favorite client", Client.find(firstClient.getId()).getDetails());
  }

}
