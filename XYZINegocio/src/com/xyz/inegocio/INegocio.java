//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)INegocio.java Created on 21/09/2015, 07:36:43 PM
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
package com.xyz.inegocio;

import com.xyz.exceptions.*;
import com.xyz.objnegocio.*;
import java.util.List;

/**
 *
 * @author Alan García
 */
public interface INegocio {
    Empleado alta(Empleado e) throws PreexistingEntityException, Exception;
    Empleado cambio(Empleado e) throws IllegalOrphanException, NonexistentEntityException, Exception;
    Empleado buscar(Empleado e);
    List<Empleado> listaEmpleados();
    
    Movimiento alta(Movimiento m) throws PreexistingEntityException, Exception;
    Movimiento cambio(Movimiento m) throws NonexistentEntityException, Exception;
    Movimiento buscar(Movimiento m);
    List<Movimiento> listaMovimientos();
    
    List<Empleado> importarData(String file);
    List<Movimiento> importarMovimientos(String file);
}
