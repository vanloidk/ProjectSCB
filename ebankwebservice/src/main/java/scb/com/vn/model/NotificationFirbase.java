/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "NotificationFirbase")
public class NotificationFirbase {

    private String to;
    private notification notification;

    @XmlElement(name = "to", nillable = true)
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @XmlElement(name = "notification", nillable = true)
    public notification getNotification() {
        return notification;
    }

    public void setNotification(notification notification) {
        this.notification = notification;
    }

    public static class notification {

        private String title;
        private String body;
        private String click_action;
        private String icon;

        @XmlElement(name = "title", nillable = true)
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @XmlElement(name = "body", nillable = true)
        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @XmlElement(name = "click_action", nillable = true)
        public String getClick_action() {
            return click_action;
        }

        public void setClick_action(String click_action) {
            this.click_action = click_action;
        }

        @XmlElement(name = "icon", nillable = true)
        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }

}
