package unoeste.fipp.ativooperante_be.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.ativooperante_be.entities.Denuncia;
import unoeste.fipp.ativooperante_be.entities.FeedBack;
@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack,Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM FeedBack f WHERE f.denuncia.id = :denunciaId")
    void deleteByDenunciaId(@Param("denunciaId") Long denunciaId);


}
