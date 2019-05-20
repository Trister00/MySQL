import java.util.ArrayList;

public interface DAO<O> {
    boolean add(O o);
    boolean update(O o);
    boolean delete(O o);
    O get(int i);
    ArrayList<O> getAll();
}
