package is.hi.teymi9.gefins.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Módel klasi fyrir einkaskilaboð (Message)
 *
 * @author Einar
 * @version 1.0
 */

@Entity
@Table(name="messages")
public class Message {

    // unique auðkenni fyrir message
    @Id
    private UUID id;
    // notandanafn höfunds
    private String sender;
    // notandanafna viðtakanda
    private String recipient;
    // viðfangsefni skilaboða
    private String subject;
    // skilaboðin sjálf
    private String message;
    // dagsetning skilaboða
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;
    // er viðtakandi búinn að lesa skilaboðin?
    private boolean read;

    // Smiður
    public Message(String sender, String recipient, String subject, String message) {
        id = UUID.randomUUID();
        date = new Date();
        read = false;
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    // Tómur smiður
    public Message() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + sender + '\'' +
                ", to='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", read=" + read +
                '}';
    }
}

