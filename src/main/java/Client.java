import java.util.ArrayList;
import java.util.List;

public class Client {
  private String mName;
  private String mDetails;
  private static List<Client> instances = new ArrayList<Client>();

  public Client(String name, String details) {
    mName = name;
    mDetails = details;
    instances.add(this);
  }

  public String getName() {
    return mName;
  }

  public String getDetails() {
    return mDetails;
  }

  public static List<Client> all() {
    return instances;
  }

}
