package by.epamtc.lyskovkirill.restaurant.bean.contact;

import java.io.Serial;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * Java bean class which represents the work time for {@link Contact}.
 *
 * @author k1ly
 */
public class WorkTime implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private DayOfWeek dayOfWeek;

    private LocalTime from;

    private LocalTime to;

    public WorkTime() {
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkTime workTime = (WorkTime) o;
        return dayOfWeek == workTime.dayOfWeek && Objects.equals(from, workTime.from) && Objects.equals(to, workTime.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, from, to);
    }

    @Override
    public String toString() {
        return "WorkTime{" +
                "dayOfWeek=" + dayOfWeek +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
