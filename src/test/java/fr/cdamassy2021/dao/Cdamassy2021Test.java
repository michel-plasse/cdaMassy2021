/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fr.cdamassy2021.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
/**
 * @author thoma
 */
public class Cdamassy2021Test {

    protected LocalDateTime instant;
    public Cdamassy2021Test() {
        instant = LocalDateTime.now();
    }
    
     @BeforeEach
    public void before() throws SQLException{
        System.out.println("Database reset");
        Database.reset(instant);
    }
}