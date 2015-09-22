//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)CInfraestructure.java Created on 21/09/2015, 10:35:42 AM
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
package com.xyz.infraestructure;

import com.xyz.objnegocio.*;
import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class CInfraestructure
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class CInfraestructure {

    protected CInfraestructure() {
    }

    protected List<Empleado> importarData(String file) {
        List<Empleado> empleados = new ArrayList<>();
        try {
            CsvReader emImport = new CsvReader(file);
            emImport.readHeaders();
            while (emImport.readRecord()) {
                //Obtenemos los datos del archivo
                String idEmpleado = emImport.get(0);
                String nombre = emImport.get(1);
                String apPat = emImport.get(2);
                String apMat = emImport.get(3);
                String fechaIngreso = emImport.get(4);
                String tipoMov = emImport.get(5);
                String puesto = emImport.get(6);

                //Parseamos la fecha del archivo para el formato de mysql
                SimpleDateFormat formatFrom = new SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatFrom.parse(fechaIngreso);// 01/30/2015
                String mysqlString = formatTo.format(date);// 2015-30-01
                Date mysqlDate = formatTo.parse(mysqlString);

                //Creamos el empleado con los datos importados
                Empleado em = new Empleado();
                em.setIdEmpleado(Integer.parseInt(idEmpleado));
                em.setNombre(nombre);
                em.setApellidos(apPat + " " + apMat);
                em.setFechaIngreso(mysqlDate);
                em.setCurp("");
                em.setPuesto(puesto);
                if (!tipoMov.equalsIgnoreCase("BAJA")) {
                    em.setActivo(Boolean.TRUE);
                } else {
                    em.setActivo(Boolean.FALSE);
                }

                //Agregamos a la lista el empleado importado
                empleados.add(em);
            }
            //Cerramos la lectura del archivo
            emImport.close();
        } catch (ParseException ex) {
            Logger.getLogger(CInfraestructure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CInfraestructure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CInfraestructure.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Regresamos la lista de empleados obtenidos
        return empleados;
    }

    protected List<Movimiento> importarMovimientos(String file) {
        List<Movimiento> movimientos = new ArrayList<>();
        try {
            CsvReader movImport = new CsvReader(file);
            movImport.readHeaders();
            while (movImport.readRecord()) {
                //Obtenemos los datos del archivo
                String idEmpleado = movImport.get(0);
                String tipoMov = movImport.get(5);
                String puesto = movImport.get(6);
                Date mysqlDate = new Date();
                //Creamos el movimiento con los datos importados
                Empleado em = new Empleado(Integer.parseInt(idEmpleado));
                Movimiento mov = new Movimiento(0,tipoMov,"",puesto,mysqlDate,em);
                movimientos.add(mov);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CInfraestructure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CInfraestructure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movimientos;
    }
}
