package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.NotificationDao;
import fr.istic.taa.jaxrs.domain.Notification;
import java.util.List;

public class NotificationService {
    private final NotificationDao notificationDao = new NotificationDao();

    public List<Notification> getAllNotifications() {
        return notificationDao.findAll();
    }

    public Notification getNotification(long id) {
        return notificationDao.findOne(id);
    }

    public void deleteNotification(long id) {
        notificationDao.deleteById(id);
    }
}