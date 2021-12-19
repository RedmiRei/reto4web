/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ciclocuatro.retos.reto.service;

import com.ciclocuatro.retos.reto.model.Productos;
import com.ciclocuatro.retos.reto.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rei
 */
@Service
public class ProductosService {
    /**
     * Objeto de clase ProductoRepository.
     */
    @Autowired
    private ProductoRepository clotheRepository;

    /**
     * Metodo Get para obtener todos los datos de los productos.
     * @return Lista de productos.
     */
    public List<Productos> getAll() {
        return clotheRepository.getAll();
    }

    /**
     * Metodo que obtiene producto buscado por id.
     * @param id parametro.
     * @return producto.
     */
    public Optional<Productos> getClothe(int id) {
        return clotheRepository.getClothe(id);
    }

    /**
     * Metodo Post para el ingreso de datos de un producto.
     * @param accesory recibe datos.
     * @return datos del producto ingresado.
     */
    public Productos create(Productos accesory) {
        if (accesory.getBrand() == null) {
            return accesory;
        } else {
            return clotheRepository.create(accesory);
        }
    }

    /**
     * Metodo que sirve para actualizar un producto.
     * @param accesory Parametro que permite obtener valores de los productos.
     * @return datos actualizados.
     */
    public Productos update(Productos accesory) {

         if (accesory.getId() != null) {
            Optional<Productos> accesoryDb = clotheRepository.getClothe(accesory.getId());
            if (!accesoryDb.isEmpty()) {
                
                if (accesory.getBrand()!= null) {
                    accesoryDb.get().setBrand(accesory.getBrand());
                }
                if (accesory.getCategory() != null) {
                    accesoryDb.get().setCategory(accesory.getCategory());
                }
                
                if (accesory.getDescription() != null) {
                    accesoryDb.get().setDescription(accesory.getDescription());
                }
                if (accesory.getPrice() != 0.0) {
                    accesoryDb.get().setPrice(accesory.getPrice());
                }
                if (accesory.getQuantity() != 0) {
                    accesoryDb.get().setQuantity(accesory.getQuantity());
                }
                if (accesory.getPhotography() != null) {
                    accesoryDb.get().setPhotography(accesory.getPhotography());
                }
                accesoryDb.get().setAvailability(accesory.isAvailability());
                clotheRepository.update(accesoryDb.get());
                return accesoryDb.get();
            } else {
                return accesory;
            }
        } else {
            return accesory;
        }
    }

    /**
     * Metodo que permite eliminar registros.
     * @param id parametro.
     * @return true o false.
     */
    public boolean delete(int id) {
        Boolean aBoolean = getClothe(id).map(accesory -> {
            clotheRepository.delete(accesory);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
