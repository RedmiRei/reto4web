/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ciclocuatro.retos.reto.controller;

import com.ciclocuatro.retos.reto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ciclocuatro.retos.reto.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Indica que UserController va ser un servicio.
 * @author rei
 */
@RestController
/**
 * Endpoint /api/user
 */
@RequestMapping("api/user")
/**
 * Permite realizar cualquier tipo de peticion sin restriccion.
 */
@CrossOrigin("*")
/**
 * Clase UserController
 */
public class UserController {

    /**
     * Objeto de clase userServices.
     */
    @Autowired
    private UserService servicio;

    /**
     * Metodo Get para obtener todos los datos de los usuarios.
     * @return Lista de usuarios.
     */
    @GetMapping("/all")
    public List<User> listAll() {
        return servicio.listAll();
    }

    /**
     * Metodo que obtiene usuario buscado por id.
     * @param id parametro.
     * @return usuario.
     */
    @GetMapping("/{id}")
    public Optional<User> getOrder(@PathVariable("id") int id){
        return servicio.getUser(id);
    }

    /**
     * Metodo Post para el ingreso de datos para el usuario.
     * @param user recibe datos.
     * @return datos del usuario registrado.
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return servicio.create(user);
    }

    /**
     * Metodo post para actualizar un usuario.
     * @param user datos del usuria para actualizar.
     * @return el usuario actualizado.
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user) {
        return servicio.update(user);
    }

    /**
     * Metodo para eliminar un usuario.
     * @param id del ususario a eliminar.
     * @return el id del usuario eliminado.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return servicio.delete(id);
    }

    /**
     * Metodo GET para verificar la existencia del usuario.
     * @param email recibe correo.
     * @return true o false si existe o no el usuario.
     */
    @GetMapping("/emailexist/{email}")
    public boolean emailExists(@PathVariable("email") String email) {
        return servicio.emailExists(email);
    }

    /**
     * Metodo GET para realizar consulta de validacion.
     * @param email recibe el correo.
     * @param password recibe la contrasena.
     * @return datos del usuario no nulos.
     */
    @GetMapping("/{email}/{password}")
    public User autenticateUser(@PathVariable("email") String email, @PathVariable("password") String password) {
        return servicio.autenticateUser(email, password);
    }

    /**
     * Fin de la clase UserController
     */
}
