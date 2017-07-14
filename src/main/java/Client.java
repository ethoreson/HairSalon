import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private String name;
  private String details;
//  private static List<Client> instances = new ArrayList<Client>();
  private int id;

  public Client(String name, String details) {
    this.name = name;
    this.details = details;
//    instances.add(this);
//    id = instances.size();
  }

  public String getName() {
    return name;
  }

  public String getDetails() {
    return details;
  }

  public static List<Client> all() {
    String sql = "SELECT id, name, details FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static void clear() {
    instances.clear();
  }

  public int getId() {
    return id;
  }

  public static Client find(int id) {
//    return instances.get(id - 1);
  }

}
