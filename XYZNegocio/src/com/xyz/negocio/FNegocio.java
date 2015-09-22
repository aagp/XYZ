//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)FNegocio.java Created on 21/09/2015, 07:48:08 PM
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
package com.xyz.negocio;

import com.xyz.exceptions.*;
import com.xyz.inegocio.INegocio;
import com.xyz.objnegocio.Empleado;
import com.xyz.objnegocio.Movimiento;
import java.util.List;

/**
 * Class FNegocio
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class FNegocio implements INegocio {

    CNegocio cn;

    public FNegocio() {
        cn = new CNegocio();
    }

    @Override
    public Empleado alta(Empleado e) throws PreexistingEntityException, Exception {
        return cn.alta(e);
    }

    @Override
    public Empleado cambio(Empleado e) throws IllegalOrphanException, NonexistentEntityException, Exception {
        return cn.cambio(e);
    }

    @Override
    public Empleado buscar(Empleado e) {
        return cn.buscar(e);
    }

    @Override
    public List<Empleado> listaEmpleados() {
        return cn.listaEmpleados();
    }

    @Override
    public Movimiento alta(Movimiento m) throws PreexistingEntityException, Exception {
        return cn.alta(m);
    }

    @Override
    public Movimiento cambio(Movimiento m) throws NonexistentEntityException, Exception {
        return cn.cambio(m);
    }

    @Override
    public Movimiento buscar(Movimiento m) {
        return cn.buscar(m);
    }

    @Override
    public List<Movimiento> listaMovimientos() {
        return cn.listaMovimientos();
    }

    @Override
    public List<Empleado> importarData(String file) {
        return cn.importarData(file);
    }

    @Override
    public List<Movimiento> importarMovimientos(String file) {
        return cn.importarMovimientos(file);
    }
}
