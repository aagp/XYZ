//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Pruebas.java Created on 21/09/2015, 08:39:38 PM
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
package com.xyz.negocio.pruebas;

import com.xyz.exceptions.PreexistingEntityException;
import com.xyz.inegocio.INegocio;
import com.xyz.negocio.FNegocio;
import com.xyz.objnegocio.Empleado;
import com.xyz.objnegocio.Movimiento;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Pruebas
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        INegocio cn = new FNegocio();
        String file = "C:\\\\Users\\\\aaGp\\\\Documents\\\\GitHub\\\\XYZ\\\\listadoSemana30.csv";

        System.out.println("----------------------");
        System.out.println("Empezando importación...");
        System.out.println("------------------------");
        for (Empleado em : cn.importarData(file)) {
            try {
                cn.alta(em);
            } catch (PreexistingEntityException ex) {
                System.out.println("Empleado existente");
            } catch (Exception ex) {
                Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("----------------------");
        System.out.println("Terminado empleados...");
        System.out.println("----------------------");


        System.out.println("------------------------");
        System.out.println("Empezando importación...");
        System.out.println("------------------------");
        for (Movimiento mov : cn.importarMovimientos(file)) {
            try {
                cn.alta(mov);
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("------------------------");
        System.out.println("Terminando movimientos...");
        System.out.println("----------------------");


//        for(Empleado e : cn.listaEmpleados()) {
//            System.out.println(e);
//        }

    }
}
//    public Empleado(int idEmpleado, String nombre, String apellidos, Date fechaIngreso, String curp, Boolean activo) {
//        this.idEmpleado = idEmpleado;
//        this.nombre = nombre;
//        this.apellidos = apellidos;
//        this.fechaIngreso = fechaIngreso;
//        this.curp = curp;
//        this.activo = activo;
//    }
//
//    public Movimiento(Integer idMovimiento, String tipoMov, String puestoAnterior, String puestoNuevo, Date fechaMov, Empleado idEmpleado) {
//        this.idMovimiento = idMovimiento;
//        this.tipoMov = tipoMov;
//        this.puestoAnterior = puestoAnterior;
//        this.puestoNuevo = puestoNuevo;
//        this.fechaMov = fechaMov;
//        this.idEmpleado = idEmpleado;
//    }