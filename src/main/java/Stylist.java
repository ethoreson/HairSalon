import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String name;
  private String details;
//  private static List<Stylist> instances = new ArrayList<Stylist>();
  private int id;
//  private List<Client> mClients;

  public Stylist(String name, String details) {
    this.name = name;
    this.details = details;
//    instances.add(this);
//    mId = instances.size();
//    mClients = new ArrayList<Client>();
  }

  public String getName() {
    return name;
  }

  public String getDetails() {
    return details;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name, details FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public int getId() {
    return id;
  }

  public static Stylist find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM stylists WHERE id=:id;";
        Stylist stylist = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Stylist.class);
        return stylist;
      }
    }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylistId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Client.class);
    }
  }

@Override
public boolean equals(Object otherStylist) {
  if (!(otherStylist instanceof Stylist)) {
    return false;
  } else {
    Stylist newStylist = (Stylist) otherStylist;
    return this.getName().equals(newStylist.getName()) &&
           this.getId() == newStylist.getId();
  }
}

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, details) VALUES (:name, :details);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("details", this.details)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String name, String details) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name, details = :details WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("details", details)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM stylists WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
