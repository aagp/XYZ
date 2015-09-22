//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Pruebas.java Created on 21/09/2015, 12:27:23 PM
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
package com.xyz.infraestructure.pruebas;

import com.xyz.iinfraestructure.IInfraestructure;
import com.xyz.infraestructure.FInfraestructure;
import com.xyz.objnegocio.Empleado;
import com.xyz.objnegocio.Movimiento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        
        IInfraestructure fi = new FInfraestructure();
        String file = "C:\\\\Users\\\\aaGp\\\\Documents\\\\GitHub\\\\XYZ\\\\listadoSemana30.csv";

//        for (Movimiento movs : fi.importarMovimientos(file)) {
//            System.out.println(movs.getIdEmpleado().getIdEmpleado()
//                        + " " + movs.getTipoMov() + " "
//                        + movs.getPuestoAnterior() + " "
//                        + movs.getPuestoNuevo() + " "
//                        + movs.getFechaMov());
//        }
        
        for(Empleado em : fi.importarData(file)) {
            System.out.println(em.getIdEmpleado() + " "
                    + em.getNombre() + " "
                    + em.getApellidos() + " "
                    + em.getFechaIngreso() + " "
                    + em.getCurp() + " "
                    + em.getPuesto() + " "
                    + em.getActivo());
        }

//        Calendar calendario = GregorianCalendar.getInstance();
//        Date fecha = calendario.getTime();
//        System.out.println(fecha);
//        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(formatoDeFecha.format(fecha));
//
//        Date date = new Date();
//        date.getTime();
//        System.out.println(formatoDeFecha.format(date.getTime()));
//
//        System.out.println("------");
//        SimpleDateFormat formatFrom = new SimpleDateFormat("MM/dd/yyyy");
//        SimpleDateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd");
//        Date fe = formatFrom.parse("09/01/2015");// 01/30/2015
//        String mysqlString = formatTo.format(fe);// 2015-30-01
//        Date fee = formatTo.parse(mysqlString);
//        System.out.println(mysqlString);
//        System.out.println(fee);

    }
}
