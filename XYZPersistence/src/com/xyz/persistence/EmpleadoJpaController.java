//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)EmpleadoJpaController.java Created on 22/09/2015, 10:36:14 AM
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
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.xyz.objnegocio.Movimiento;
import com.xyz.exceptions.IllegalOrphanException;
import com.xyz.exceptions.NonexistentEntityException;
import com.xyz.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class EmpleadoJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class EmpleadoJpaController implements Serializable {

    private EntityManagerFactory emf = null;
    
    public EmpleadoJpaController() {
        emf = Persistence.createEntityManagerFactory("XYZPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) throws PreexistingEntityException, Exception {
        if (empleado.getMovimientoCollection() == null) {
            empleado.setMovimientoCollection(new ArrayList<Movimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Movimiento> attachedMovimientoCollection = new ArrayList<Movimiento>();
            for (Movimiento movimientoCollectionMovimientoToAttach : empleado.getMovimientoCollection()) {
                movimientoCollectionMovimientoToAttach = em.getReference(movimientoCollectionMovimientoToAttach.getClass(), movimientoCollectionMovimientoToAttach.getIdMovimiento());
                attachedMovimientoCollection.add(movimientoCollectionMovimientoToAttach);
            }
            empleado.setMovimientoCollection(attachedMovimientoCollection);
            em.persist(empleado);
            for (Movimiento movimientoCollectionMovimiento : empleado.getMovimientoCollection()) {
                Empleado oldIdEmpleadoOfMovimientoCollectionMovimiento = movimientoCollectionMovimiento.getIdEmpleado();
                movimientoCollectionMovimiento.setIdEmpleado(empleado);
                movimientoCollectionMovimiento = em.merge(movimientoCollectionMovimiento);
                if (oldIdEmpleadoOfMovimientoCollectionMovimiento != null) {
                    oldIdEmpleadoOfMovimientoCollectionMovimiento.getMovimientoCollection().remove(movimientoCollectionMovimiento);
                    oldIdEmpleadoOfMovimientoCollectionMovimiento = em.merge(oldIdEmpleadoOfMovimientoCollectionMovimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleado(empleado.getIdEmpleado()) != null) {
                throw new PreexistingEntityException("Empleado " + empleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdEmpleado());
            Collection<Movimiento> movimientoCollectionOld = persistentEmpleado.getMovimientoCollection();
            Collection<Movimiento> movimientoCollectionNew = empleado.getMovimientoCollection();
            List<String> illegalOrphanMessages = null;
            for (Movimiento movimientoCollectionOldMovimiento : movimientoCollectionOld) {
                if (!movimientoCollectionNew.contains(movimientoCollectionOldMovimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimiento " + movimientoCollectionOldMovimiento + " since its idEmpleado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Movimiento> attachedMovimientoCollectionNew = new ArrayList<Movimiento>();
            for (Movimiento movimientoCollectionNewMovimientoToAttach : movimientoCollectionNew) {
                movimientoCollectionNewMovimientoToAttach = em.getReference(movimientoCollectionNewMovimientoToAttach.getClass(), movimientoCollectionNewMovimientoToAttach.getIdMovimiento());
                attachedMovimientoCollectionNew.add(movimientoCollectionNewMovimientoToAttach);
            }
            movimientoCollectionNew = attachedMovimientoCollectionNew;
            empleado.setMovimientoCollection(movimientoCollectionNew);
            empleado = em.merge(empleado);
            for (Movimiento movimientoCollectionNewMovimiento : movimientoCollectionNew) {
                if (!movimientoCollectionOld.contains(movimientoCollectionNewMovimiento)) {
                    Empleado oldIdEmpleadoOfMovimientoCollectionNewMovimiento = movimientoCollectionNewMovimiento.getIdEmpleado();
                    movimientoCollectionNewMovimiento.setIdEmpleado(empleado);
                    movimientoCollectionNewMovimiento = em.merge(movimientoCollectionNewMovimiento);
                    if (oldIdEmpleadoOfMovimientoCollectionNewMovimiento != null && !oldIdEmpleadoOfMovimientoCollectionNewMovimiento.equals(empleado)) {
                        oldIdEmpleadoOfMovimientoCollectionNewMovimiento.getMovimientoCollection().remove(movimientoCollectionNewMovimiento);
                        oldIdEmpleadoOfMovimientoCollectionNewMovimiento = em.merge(oldIdEmpleadoOfMovimientoCollectionNewMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getIdEmpleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getIdEmpleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Movimiento> movimientoCollectionOrphanCheck = empleado.getMovimientoCollection();
            for (Movimiento movimientoCollectionOrphanCheckMovimiento : movimientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Movimiento " + movimientoCollectionOrphanCheckMovimiento + " in its movimientoCollection field has a non-nullable idEmpleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Empleado as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Empleado as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
