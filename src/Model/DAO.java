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
public interface DAO<T> {
    
    public boolean create(T dato);
    
    public T read(Long id);
    
    public boolean update(T dato);
    
    public boolean delete(Long id);
    
}
