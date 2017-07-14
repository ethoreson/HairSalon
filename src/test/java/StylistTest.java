import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class StylistTest {

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
  public void Stylist_instantiatesCorrectly_true() {
    Stylist newStylist = new Stylist("Stylist1", "employed here for 3 years");
    assertEquals(true, newStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName() {
    Stylist newStylist = new Stylist("Stylist1", "employed here for 3 years");
    assertEquals("Stylist1", newStylist.getName());
  }

  @Test
  public void getDetails_stylistInstantiatesWithDetails() {
    Stylist newStylist = new Stylist("Stylist1", "employed here for 3 years");
    assertEquals("employed here for 3 years", newStylist.getDetails());
  }

  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Stylist1", "employed here for 3 years");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Stylist2", "specialty is highlights");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }

  @Test
  public void getId_stylistsInstantiateWithAnId_1() {
    Stylist testStylist = new Stylist("Stylist1", "employed here for 3 years");
    testStylist.save();
    assertTrue(testStylist.getId() > 0);
  }

  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("Stylist1", "employed here for 3 years");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Stylist2", "specialty is highlights");
    secondStylist.save();
    assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  }

  @Test
  public void getClients_initiallyReturnsEmptyList_ArrayList() {
    Stylist newStylist = new Stylist("Stylist1", "employed here for 3 years");
    assertEquals(0, newStylist.getClients().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Stylist1", "employed here for 3 years");
    Stylist secondStylist = new Stylist("Stylist1", "employed here for 3 years");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist firstStylist = new Stylist("Stylist1", "employed here for 3 years");
    firstStylist.save();
    assertTrue(Stylist.all().get(0).equals(firstStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Stylist1", "employed here for 3 years");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist myStylist = new Stylist("Stylist1", "employed here for 3 years");
    myStylist.save();
    Client myClient = new Client("Client1", "our favorite client", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }

  @Test
  public void getClients_retrievesALlClientsFromDatabase_clientsList() {
    Stylist myStylist = new Stylist("Stylist1", "employed here for 3 years");
    myStylist.save();
    Client firstClient = new Client("Client A", "needs a perm", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Client B", "needs a dye job", myStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }


}
