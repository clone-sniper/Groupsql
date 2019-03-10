/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import DBsetup.connectDB;
import Model.Customer;
import java.sql.Connection;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
/**
 *
 * @author Eric Petersen
 */
public class GroupProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception 
    {
        new connectDB().createTable();
        
        //adding new people to database
        Connection conn = new connectDB().getConnection();
        Statement stmt = conn.createStatement();
        //stmt.execute("insert into Customer(name, Email, PhoneNumber) values ('bill', 'email', 'number' )");
        //stmt.execute("insert into Customer(name, Email, PhoneNumber) values ( 'George', 'email', 'number' )");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persist");
        EntityManager em = emf.createEntityManager();
        
        Customer cust = em.find(Customer.class, 2);
        
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        cust.setEmail("brown@gmail.com");
        trans.commit();
        em.close();
        
       // System.out.println(cust.getName());
        /*
        //   Testing to create a file that saves the data at the end and upon restart load that data back
        CustomerDB people = new CustomerDB();
        FileData list = new FileData();
        
        list.LoadData(people.getAll());    
        

        
        list.SaveData(people.getAll());
        */
    }
    
}
