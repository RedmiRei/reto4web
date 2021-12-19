/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ciclocuatro.retos.reto.service;

import com.ciclocuatro.retos.reto.model.User;
import com.ciclocuatro.retos.reto.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rei
 */
@Service
public class UserService {
    /**
     * Objeto de clase UserRepository.
     */
    @Autowired
    public UserRepository repositorio;

    /**
     * Metodo que obtiene usuario buscado por id.
     * @param id parametro.
     * @return usuario.
     */
    public Optional<User> getUser(int id) {
        return repositorio.getUser(id);
    }

    /**
     * Metodo Get para obtener todos los datos de los usuarios.
     * @return Lista de usuarios.
     */
    public List<User> listAll() {
        return repositorio.listAll();
    }

    /**
     * Metodo que valida email.
     * @param email recibe el correo.
     * @return true o false si existe o no el usuario.
     */
    public boolean emailExists(String email) {
        return repositorio.emailExists(email);
    }

    /**
     * Metodo GET para realizar consulta de validacion.
     * @param email recibe el correo.
     * @param password recibe la contrasena.
     * @return datos del usuario no nulos.
     */
    public User autenticateUser(String email, String password) {
        Optional<User> usuario = repositorio.autenticateUser(email, password);

        if (usuario.isEmpty()) {
            return new User();
        } else {
            return usuario.get();
        }
    }

    /**
     * Metodo Post para el ingreso de datos para el usuario.
     * @param user recibe datos.
     * @return datos del usuario ingresado.
     */
    public User create(User user) {
        Optional<User> userIdMaxiomo = repositorio.lastUserId();
        if (user.getId() == null) {
            if (userIdMaxiomo.isEmpty())
                user.setId(1);
            else
                user.setId(userIdMaxiomo.get().getId() + 1);
        }
        Optional<User> e = repositorio.getUser(user.getId());
        if (e.isEmpty()) {
            if (emailExists(user.getEmail()) == false) {
                return repositorio.create(user);
            } else {
                return user;
            }
        } else {
            return user;
        }   
    }

    /**
     * Metodo que sirve para actualizar registros.
     * @param user Parametro que permite obtener valores del usuario.
     * @return datos actualizados.
     */
    public User update(User user) {

        if (user.getId() != null) {
            Optional<User> userDb = repositorio.getUser(user.getId());
            if (!userDb.isEmpty()) {
                if (user.getIdentification() != null) {
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userDb.get().setName(user.getName());
                }
                if (user.getAddress() != null) {
                    userDb.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userDb.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userDb.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    userDb.get().setZone(user.getZone());
                }

                repositorio.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    /**
     * Metodo que permite eliminar registros.
     * @param userId parametro.
     * @return true o false.
     */
    public boolean delete(int userId) {
        Optional<User> usuario = getUser(userId);
        
        if (usuario.isEmpty()){
            return false;
        }else{
            repositorio.delete(usuario.get());
            return true;
        }
        /*
        Boolean aBoolean = getUser(userId).map(user -> {
            repositorio.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;

        */
    }
}
