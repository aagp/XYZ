//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)CNegocio.java Created on 21/09/2015, 07:48:17 PM
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

import com.xyz.exceptions.IllegalOrphanException;
import com.xyz.exceptions.NonexistentEntityException;
import com.xyz.exceptions.PreexistingEntityException;
import com.xyz.iinfraestructure.IInfraestructure;
import com.xyz.infraestructure.FInfraestructure;
import com.xyz.ipersistence.IPersistence;
import com.xyz.objnegocio.Empleado;
import com.xyz.objnegocio.Movimiento;
import com.xyz.persistence.FPersistence;
import java.util.List;

/**
 * Class CNegocio
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class CNegocio {
    IPersistence pe;
    IInfraestructure in;

    protected CNegocio() {
        pe = new FPersistence();
        in = new FInfraestructure();
    }
    
    public Empleado alta(Empleado e) throws PreexistingEntityException, Exception {
        return pe.alta(e);
    }

    public Empleado cambio(Empleado e) throws IllegalOrphanException, NonexistentEntityException, Exception {
        return pe.cambio(e);
    }

    public Empleado buscar(Empleado e) {
        return pe.buscar(e);
    }

    public List<Empleado> listaEmpleados() {
        return pe.listaEmpleados();
    }

    public Movimiento alta(Movimiento m) throws PreexistingEntityException, Exception {
        return pe.alta(m);
    }

    public Movimiento cambio(Movimiento m) throws NonexistentEntityException, Exception {
        return pe.cambio(m);
    }

    public Movimiento buscar(Movimiento m) {
        return pe.buscar(m);
    }

    public List<Movimiento> listaMovimientos() {
        return pe.listaMovimientos();
    }

    public List<Empleado> importarData(String file) {
        return in.importarData(file);
    }

    public List<Movimiento> importarMovimientos(String file) {
        return in.importarMovimientos(file);
    }
    
}
