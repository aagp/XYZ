//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Prueba.java Created on 21/09/2015, 02:13:52 PM
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses>
 *
 * Copyright (C) 2015 Alan García. All rights reserved.
 */
//</editor-fold>

package com.xyz.pruebas;

import com.xyz.exceptions.PreexistingEntityException;
import com.xyz.iinfraestructure.IInfraestructure;
import com.xyz.infraestructure.FInfraestructure;
import com.xyz.objnegocio.Empleado;
import com.xyz.objnegocio.Movimiento;
import com.xyz.persistence.EmpleadoJpaController;
import com.xyz.persistence.MovimientoJpaController;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Prueba
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PreexistingEntityException, Exception {
        // TODO code application logic here
        
//        Date date = new Date();
//        Empleado e = new Empleado(1003,"Alan","Garcia",date,"",Boolean.TRUE);      
//        MovimientoJpaController mjc = new MovimientoJpaController();
//        Movimiento m = new Movimiento(0,"ALTA","SEPA","GERENTE",date,e);
//        mjc.create(m);
        
//        try {
//            ejc.create(e);
//        } catch (PreexistingEntityException ex) {
//            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        IInfraestructure fi = new FInfraestructure();
        IInfraestructure fi2 = new FInfraestructure();
        String file = "C:\\\\Users\\\\aaGp\\\\Documents\\\\GitHub\\\\XYZ\\\\listadoSemana30.csv";
        EmpleadoJpaController ejc = new EmpleadoJpaController();
        

        System.out.println("----------------------");        
        System.out.println("Empezando importación...");
        System.out.println("------------------------");
        for(Empleado em : fi.importarData(file)) {
            ejc.create(em);
        }
        System.out.println("----------------------");        
        System.out.println("Terminado empleados...");
        System.out.println("----------------------");

        
        MovimientoJpaController mjc = new MovimientoJpaController();
        System.out.println("------------------------"); 
        System.out.println("Empezando importación...");
        System.out.println("------------------------");        
        for(Movimiento mov : fi2.importarMovimientos(file)) {
            mjc.create(mov);
        }
        System.out.println("------------------------"); 
        System.out.println("Terminando movimientos...");
        System.out.println("----------------------");
        

    }

}
    