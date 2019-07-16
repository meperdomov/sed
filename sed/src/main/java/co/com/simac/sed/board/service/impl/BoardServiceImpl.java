package co.com.simac.sed.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.board.dao.BoardRepository;
import co.com.simac.sed.board.dto.BoardDTO;
import co.com.simac.sed.board.model.Board;
import co.com.simac.sed.board.service.BoardService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Override
	public PageDTO<BoardDTO> getBoardPageDTO(int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<Board> boardPage = boardRepository.findAll(pageable);
		return Utilities.toPageDTO(boardPage, BoardDTO.class);
	}

	@Override
	public List<Board> findAll() {
		return boardRepository.findAll();
	}

	@Override
	public Board save(BoardDTO boardDTO) {
		Board board = new Board();
		Utilities.toEntity(board, boardDTO);
		return boardRepository.save(board);
	}

	@Override
	public void delete(Long id) {
		boardRepository.delete(id);
	}

	@Override
	public BoardDTO findById(Long id) {
		Board board = boardRepository.findOne(id);
		BoardDTO dto = new BoardDTO();
		if (board != null) {
			dto = board.getDTO();
			return dto;
		}
		return null;
	}

}
