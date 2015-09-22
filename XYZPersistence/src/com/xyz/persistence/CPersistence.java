//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)CPersistence.java Created on 20/09/2015, 12:07:34 PM
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

import com.xyz.objnegocio.Movimiento;
import com.xyz.objnegocio.Empleado;
import com.xyz.exceptions.*;
import java.util.List;

/**
 * Class CPersistence
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class CPersistence {

    EmpleadoJpaController ejc;
    MovimientoJpaController mjc;
    
    protected CPersistence() {
        ejc = new EmpleadoJpaController();
        mjc = new MovimientoJpaController();
    }
    
    protected Empleado alta(Empleado e) throws PreexistingEntityException, Exception {
        ejc.create(e);
        return e;
    }
    
    protected Empleado baja(Empleado e) throws IllegalOrphanException, NonexistentEntityException {
        ejc.destroy(e.getIdEmpleado());
        return e;
    }
    
    protected Empleado cambio(Empleado e) throws IllegalOrphanException, NonexistentEntityException, Exception {
        ejc.edit(e);
        return e;
    }
    
    protected Empleado buscar(Empleado e) {
        return ejc.findEmpleado(e.getIdEmpleado());
    }
    
    protected List<Empleado> listaEmpleados() {
        return ejc.findEmpleadoEntities();
    }
    
    protected Movimiento alta(Movimiento m) throws PreexistingEntityException, Exception {
        mjc.create(m);
        return m;
    }
    
    protected Movimiento baja(Movimiento m) throws NonexistentEntityException {
        mjc.destroy(m.getIdMovimiento());
        return m;
    }
    
    protected Movimiento cambio(Movimiento m) throws NonexistentEntityException, Exception {
        mjc.edit(m);
        return m;
    }
    
    protected Movimiento buscar(Movimiento m) {
        return mjc.findMovimiento(m.getIdMovimiento());
    }
    
    protected List<Movimiento> listaMovimientos() {
        return mjc.findMovimientoEntities();
    }
    
}
