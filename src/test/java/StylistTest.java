import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class StylistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists *;";
      con.createQuery(sql).executeUpdate();
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
    Stylist secondStylist = new Stylist("Stylist2", "specialty is highlights");
    assertEquals(true, Stylist.all().contains(firstStylist));
    assertEquals(true, Stylist.all().contains(secondStylist));
  }

  @Test
  public void clear_emptiesAllStylistsFromList_0() {
    Stylist newStylist = new Stylist("Stylist1", "employed here for 3 years");
    Stylist.clear();
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void getId_stylistsInstantiateWithAnId_1() {
    Stylist newStylist = new Stylist("Stylist1", "employed here for 3 years");
    assertEquals(1, newStylist.getId());
  }

  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("Stylist1", "employed here for 3 years");
    Stylist secondStylist = new Stylist("Stylist2", "specialty is highlights");
    assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  }

  @Test
  public void getClients_initiallyReturnsEmptyList_ArrayList() {
    Stylist newStylist = new Stylist("Stylist1", "employed here for 3 years");
    assertEquals(0, newStylist.getClients().size());
  }

  @Test
  public void addClient_addsClientToStylist_true() {
    Stylist newStylist = new Stylist("Stylist1", "employed here for 3 years");
    Client newClient = new Client("Client1", "our first client");
    newStylist.addClient(newClient);
    assertTrue(newStylist.getClients().contains(newClient));
  }


}
