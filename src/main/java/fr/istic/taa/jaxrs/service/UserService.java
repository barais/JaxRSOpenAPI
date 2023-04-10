package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.impl.UserDao;
import fr.istic.taa.jaxrs.domain.User;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();

    public UserService() {
    }
    public User getUser(Long id){
        return userDao.findOne(id);
    }
    public List<User> getUsers(){
        return userDao.findAll();
    }
    public void addUser(User user){
        userDao.save(user);
    }
    public void removeUser(Long id){
        userDao.deleteById(id);
    }

    public void updateUser(Long id, User user){
        if(userDao.findOne(id)==null){
            throw new RuntimeException("Aucun user trouvé");
        }
        else{
            User update = userDao.findOne(id);
            update.setNom(user.getNom());
            userDao.update(update);
        }
    }
}
