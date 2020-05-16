package nl.vue.blocker.vueblocker.reservations.persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FutureReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column(name = "chair_row")
    private int row;
    @Column(name = "chair_column")
    private int column;
    @Column
    private int amountOfSeats;
    @Column
    private boolean reserveJacketSeat;
    @Column
    private boolean frontAndBackSeatsAllowed;

    @Override
    public String toString() {
        return "FutureReservation{" +
                "title='" + title + '\'' +
                ", row=" + row +
                ", column=" + column +
                ", amountOfSeats=" + amountOfSeats +
                ", reserveJacketSeat=" + reserveJacketSeat +
                ", frontAndBackSeatsAllowed=" + frontAndBackSeatsAllowed +
                '}';
    }
}
