/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibnumbann77;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class FibonacciDbRepository implements FibonacciRepository{
    
    public void initDatabase(){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=root")){
            PreparedStatement stmt = con.prepareStatement("CREATE DATABASE IF not exists fibonacci");
            stmt.executeUpdate();
            stmt = con.prepareStatement("USE fibonacci");
            stmt.execute();
            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS numbers (n integer primary key, value integer);");
            stmt.execute();
        } catch (SQLException ex) {
                Logger.getLogger(FibonacciDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save(FibPair pair) {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fibonacci?user=root&password=root");
                PreparedStatement stmt = con.prepareStatement("INSERT INTO numbers (n, value) values (?,?)")){
            stmt.setInt(1, pair.getN());
            stmt.setInt(2, pair.getValue());
            stmt.executeUpdate();
        } catch (SQLException ex) {
                Logger.getLogger(FibonacciDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<FibPair> load() {
        List<FibPair> pairs = new ArrayList<>();
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fibonacci?user=root&password=root");
                PreparedStatement stmt = con.prepareStatement("SELECT n, value FROM numbers")){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                FibPair pair = new FibPair(rs.getInt("n"), rs.getInt("value"));
                pairs.add(pair);
            }
        } catch (SQLException ex) {
                Logger.getLogger(FibonacciDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pairs;
    }
    
    
}
