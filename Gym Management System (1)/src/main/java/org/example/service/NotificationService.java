package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Notification;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class NotificationService {

    private final String PATHNAME = "src/main/resources/notification.json";


    public Notification getById(int id) {
        return getNotifications().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    /**
     * Get notifications
     *
     * @return List of Notification
     */
    public List<Notification> getNotifications() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(PATHNAME), new TypeReference<List<Notification>>() {
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Notification> getSortedNotifications() {
        List<Notification> notifications = getNotifications();
        notifications.sort(Comparator.comparing(Notification::getUpdated).reversed());
        return notifications;
    }

    public int getNextIdCounter() {
        List<Notification> notifications = getNotifications();
        Notification notification = notifications.stream()
                .max(Comparator.comparingInt(Notification::getId)) // or .getId() for int
                .orElse(null);
        return notification != null ? notification.getId() + 1 : 1;
    }


    /**
     * Save new notification
     *
     * @param notification Notification
     */
    public void saveNewNotification(Notification notification) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Notification> notifications = getNotifications();
            notifications.add(notification);
            mapper.writeValue(new File(PATHNAME), notifications);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update new notification
     *
     * @param notification Notification
     */
    public void updateNotification(Notification notification) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Notification> notifications = getNotifications();
            clearOldNotification(notifications, notification.getId());
            notifications.add(notification);
            mapper.writeValue(new File(PATHNAME), notifications);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearOldNotification(List<Notification> notifications, int id) {
        notifications.removeIf(report -> report.getId() == id);
    }

    public void deleteNotification(int id) {
        List<Notification> notifications = getNotifications();
        clearOldNotification(notifications, id);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(PATHNAME), notifications);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}