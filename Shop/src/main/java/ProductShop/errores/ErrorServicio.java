/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductShop.errores;

/**
 *
 * @author Usuario
 */
public class ErrorServicio extends Exception {
    public ErrorServicio (String msn){
        super(msn);
    }
}
