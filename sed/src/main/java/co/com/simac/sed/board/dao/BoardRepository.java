package co.com.simac.sed.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.simac.sed.board.model.Board;

/**
 * 
 * @author Juan Camilo
 *
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

}
