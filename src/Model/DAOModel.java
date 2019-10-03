/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public interface DAOModel {
    public Object get(long id);// as Id use Long instead of Integer, Ids can be very large numbers, Int can be not enough

    public ArrayList<Object> getList(String query); //for retrieving more than one element 

    public void remove(Long id);

    public void update(Object entity); // remember that updated record should have already id inside, you can add assert inside

    public void create(Object entity); // assert that id is null
}
