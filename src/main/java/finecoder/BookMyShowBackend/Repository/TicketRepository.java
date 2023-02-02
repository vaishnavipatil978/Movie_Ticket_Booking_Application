package finecoder.BookMyShowBackend.Repository;

import finecoder.BookMyShowBackend.Models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {
}
