package by.epamtc.lyskovkirill.restaurant.bean;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * Java bean class which represents the review.
 *
 * @author k1ly
 */
public class Review implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String comment;

    private Integer grade;

    private Timestamp date;

    private User user;

    public Review() {
    }

    public Review(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && Objects.equals(comment, review.comment) && Objects.equals(grade, review.grade) && Objects.equals(date, review.date) && Objects.equals(user, review.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, grade, date, user);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", grade=" + grade +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
