package co.com.simac.sed.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.board.dto.BoardDTO;
import co.com.simac.sed.board.model.Board;
import co.com.simac.sed.board.service.BoardService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@PreAuthorize("hasRole('ROLE_BOARD_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<BoardDTO> getAlarmDTOList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return boardService.getBoardPageDTO(page, size);
	}

	@PreAuthorize("hasRole('ROLE_BOARD_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<BoardDTO> getAlarm(@PathVariable("id") long id) {
		BoardDTO boardDTO = boardService.findById(id);
		if (boardDTO == null) {
			return new ResponseEntity<BoardDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BoardDTO>(boardDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BOARD_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(@Valid @RequestBody BoardDTO boardDTO, Errors errors) {
		if (!errors.hasErrors()) {
			Board board = boardService.save(boardDTO);
			return new ResponseEntity(board.getDTO(), HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_BOARD_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		boardService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
