//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)FInfraestructure.java Created on 21/09/2015, 10:35:52 AM
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

import com.xyz.iinfraestructure.IInfraestructure;
import com.xyz.objnegocio.Empleado;
import com.xyz.objnegocio.Movimiento;
import java.util.List;

/**
 * Class FInfraestructure
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class FInfraestructure implements IInfraestructure {
    
    CInfraestructure ci;
    
    public FInfraestructure() {
        ci = new CInfraestructure();
    }

    @Override
    public List<Empleado> importarData(String file) {
        return ci.importarData(file);
    }
    
    @Override
    public List<Movimiento> importarMovimientos(String file) {
        return ci.importarMovimientos(file);
    }

}
