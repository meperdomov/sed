package co.com.simac.sed.cube.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.cube.model.Order;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query(value = "SELECT * FROM orden_produccion op   where  op.fecha_inicio >= :initialDate and op.fecha_inicio <= :finalDate and op.fin_con_error = 0", nativeQuery = true)
	public List<Order> findOrdersByRange(
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate);
	
	@Query(value = "SELECT count(*) FROM orden_produccion op WHERE op.finalizada = 1 and op.fin_con_error = 0",nativeQuery = true)
	public Integer getQuatityOrderSuccessful();
	
	@Query(value = "SELECT count(*) FROM orden_produccion op WHERE op.fin_con_error = 1",nativeQuery = true)
	public Integer getQuatityOrderError();
	
}