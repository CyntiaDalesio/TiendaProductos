/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductShop.Repository;

import ProductShop.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Usuario
 */

public interface PhotoRepository extends JpaRepository <Photo, String>{
}
