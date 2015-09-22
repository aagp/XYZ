//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)FPersistence.java Created on 20/09/2015, 12:07:43 PM
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

package com.xyz.persistence;

import com.xyz.objnegocio.Empleado;
import com.xyz.objnegocio.Movimiento;
import com.xyz.exceptions.IllegalOrphanException;
import com.xyz.exceptions.NonexistentEntityException;
import com.xyz.exceptions.PreexistingEntityException;
import com.xyz.ipersistence.IPersistence;
import java.util.List;

/**
 * Class FPersistence
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class FPersistence implements IPersistence {
    
    CPersistence cp;
    
    public FPersistence() {
        cp = new CPersistence();
    }

    @Override
    public Empleado alta(Empleado e) throws PreexistingEntityException, Exception {
        return cp.alta(e);
    }

    @Override
    public Empleado baja(Empleado e) throws IllegalOrphanException, NonexistentEntityException, Exception {
        return cp.baja(e);
    }

    @Override
    public Empleado cambio(Empleado e) throws IllegalOrphanException, NonexistentEntityException, Exception {
        return cp.cambio(e);
    }

    @Override
    public Empleado buscar(Empleado e) {
        return cp.buscar(e);
    }

    @Override
    public List<Empleado> listaEmpleados() {
        return cp.listaEmpleados();
    }

    @Override
    public Movimiento alta(Movimiento m) throws PreexistingEntityException, Exception {
        return cp.alta(m);
    }

    @Override
    public Movimiento baja(Movimiento m) throws NonexistentEntityException, Exception {
        return cp.baja(m);
    }

    @Override
    public Movimiento cambio(Movimiento m) throws NonexistentEntityException, Exception {
        return cp.cambio(m);
    }

    @Override
    public Movimiento buscar(Movimiento m) {
        return cp.buscar(m);
    }

    @Override
    public List<Movimiento> listaMovimientos() {
        return cp.listaMovimientos();
    }

}
