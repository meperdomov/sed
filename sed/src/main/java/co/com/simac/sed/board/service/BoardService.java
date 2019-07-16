package co.com.simac.sed.board.service;

import java.util.List;

import co.com.simac.sed.board.dto.BoardDTO;
import co.com.simac.sed.board.model.Board;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Juan Camilo
 *
 */
public interface BoardService {

	public PageDTO<BoardDTO> getBoardPageDTO(int page, int size);

	public List<Board> findAll();

	public Board save(BoardDTO boardDTO);

	public void delete(Long id);

	public BoardDTO findById(Long id);
}
