package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Notification;

public class NotificationDao extends AbstractJpaDao<Long, Notification> {
    public NotificationDao() {
        super(Notification.class);
    }
}