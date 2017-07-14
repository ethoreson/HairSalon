import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist newStylist = new Stylist("Stylist1", "employed here for 3 years");
    assertEquals(true, newStylist instanceof Stylist);
  }

}
