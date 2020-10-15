package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidades.Items;

public class ItemsData {

    List<Items> lista = new ArrayList<Items>();
    int id = 0;
    Connection cn = Conn.connectSQLite();

    public void create(Items crear) {
        //p.setId(++id);
        //lis.add(p);
        String sql = "INSERT INTO ITEMS(Nombre, Categoria, Familia, Precio) "
                + " Valores(?,?,?,?) ";
        int id = 0;
        int r =0;
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(++id, crear.getNombre());
            ps.setString(++id, crear.getCategoria());
            ps.setString(++id, crear.getFamilia());
            ps.setInt(++id, crear.getPrecio());
            r = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("crear.res=" + r);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public List<Items> list(String filter) {
        List<Items> lista2 = new ArrayList<Items>();
        String sql = "SELECCIONAR items ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Items ITEMS= new Items();
                ITEMS.setId(rs.getInt("id"));
                ITEMS.setNombre(rs.getString("Nombre"));
                ITEMS.setCategoria(rs.getString("Categoria"));
                ITEMS.setFamilia(rs.getString("Familia"));
                ITEMS.setPrecio(rs.getInt("Precio"));
                lista2.add(ITEMS);                
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return lista2;
    }

    public Items get(int id) {
        Items p =new Items();
        String sql = "SELECT * FROM persons WHERE id = "+id+" ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("Nombre"));
                p.setCategoria(rs.getString("Categoria"));
                p.setFamilia(rs.getString("Familia"));  
                p.setPrecio(rs.getInt("Precio"));             
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return p;
    }

    // update(Person, int): void (o update(Person): void)
    public void update(Items ACTUALIZAR) {
        String sql = "UPDATE persons SET "
                + "Nombre=?, "
                + "Categoria=?, "
                + "Familia=? "
                + "Precio=? "
                + "DONDE id=?";
        int i = 0;
        int res =0;
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(++i, ACTUALIZAR.getNombre());
            ps.setString(++i, ACTUALIZAR.getCategoria());
            ps.setString(++i, ACTUALIZAR.getFamilia());
            ps.setInt(++i, ACTUALIZAR.getPrecio());
            ps.setInt(++i, ACTUALIZAR.getId());
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("actualizar.res=" + res);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM persons WHERE id = ?";
        int res =0;
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id );
            res = ps.executeUpdate();// 0 no o 1 si commit
            System.out.println("eliminar.res=" + res);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
}
