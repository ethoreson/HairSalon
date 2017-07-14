import java.util.List;
import java.util.ArrayList;

public class Stylist {
  private String mName;
  private String mDetails;
  private static List<Stylist> instances = new ArrayList<Stylist>();
  private int mId;
  private List<Client> mClients;

  public Stylist(String name, String details) {
    mName = name;
    mDetails = details;
    instances.add(this);
    mId = instances.size();
    mClients = new ArrayList<Client>();
  }

  public String getName() {
    return mName;
  }

  public String getDetails() {
    return mDetails;
  }

  public static List<Stylist> all() {
    return instances;
  }

  public static void clear() {
    instances.clear();
  }

  public int getId() {
    return mId;
  }

  public static Stylist find(int id) {
    return instances.get(id - 1);
  }

  public List<Client> getClients() {
    return mClients;
  }

  public void addClient(Client client) {
    mClients.add(client);
  }

}
