/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibnumbann77;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public void save(int n, int value) {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fibonacci?user=root&password=root");
                PreparedStatement stmt = con.prepareStatement("INSERT INTO numbers (n, value) values (?,?)")){
            stmt.setInt(1, n);
            stmt.setInt(2, value);
            stmt.executeUpdate();
        } catch (SQLException ex) {
                Logger.getLogger(FibonacciDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
