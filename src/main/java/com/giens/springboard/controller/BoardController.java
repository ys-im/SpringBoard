package com.giens.springboard.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.giens.springboard.service.Board.BoardService;
import com.giens.springboard.util.FileUtils;
import com.giens.springboard.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardService boardService;

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	/**
	 * @author 임예슬
	 * @date 2020.05.06
	 * @description 게시판 조회
	 * @reference AjaxController.java -> ajaxBoardList()	 	
	 */
	@RequestMapping(value = "/board.do", method = RequestMethod.GET)
	public String getListBoard() throws Exception {
		logger.info("board view");
		
		return "board/board";
	}
	
	/**
	 * @author 임예슬
	 * @date 2020.05.06
	 * @description 글쓰기 화면
	 * @reference 
	 */
	@RequestMapping(value = "/writeView.do", method = RequestMethod.GET)
	public String writeView() throws Exception {
		return "board/write";
	}
	
	/**
	 * @author 임예슬
	 * @date 2020.05.06
	 * @description 글쓰기 저장
	 * @reference 
	 */
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String addBoard(String title, String userID, String contents, int pBoardNo, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("add board");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("userID", userID);
		params.put("contents", contents);
		params.put("pBoardNo", pBoardNo);
		
		//게시판 글 등록
		boardService.addBoard(params);
		int boardNo = (int)params.get("boardNo");
		
		if(pBoardNo == 0) {
			boardService.updateBoardNew();
		}
				
		//게시판 글에 첨부파일 등록
		boardService.addBoardFile(boardNo, mpRequest);
		
		return "redirect:/board.do";
	}
	
	/** 
	 * @author 임예슬
	 * @date 2020.05.06
	 * @description 게시글 조회, 첨부파일 조회
	 * @reference AjaxController.java -> ajaxBoardDetail(int boardNo)
	 */
	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public String getBoard(int boardNo, Model model) throws Exception {
		logger.info("get board detail");
		
		List<Map<String, Object>> fileList = boardService.getBoardFileList(boardNo);
		model.addAttribute("fileList", fileList);
		
		return "board/boardDetail";
	}
	
	/** 
	 * @author 임예슬
	 * @date 2020.05.06
	 * @description 첨부파일 다운로드
	 * @reference FileUtils.java
	 */
	@RequestMapping(value = "/fileDown.do")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = boardService.getBoardFile(map);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String fileName = (String) resultMap.get("FILE_NAME");
		String filePath = (String) resultMap.get("FILE_PATH");
		
		//파일을 저장했던 위치에서 첨부파일을 읽어  byte[]형식으로 변환		
		File file = new File(filePath+storedFileName);
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(file);
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(fileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	/** 
	 * @author 임예슬
	 * @date 2020.05.06
	 * @description 게시글 수정을 위한 뷰
	 * @reference 
	 */
	@RequestMapping(value = "/editView.do", method = RequestMethod.GET)
	public String editView(int boardNo, Model model) throws Exception {
		logger.info("edit view");
		
		List<BoardVO> boardVO = boardService.getBoard(boardNo);
		model.addAttribute("edit", boardVO.get(0));	
		
		List<Map<String, Object>> fileList = boardService.getBoardFileList(boardNo);
		model.addAttribute("fileList", fileList);

		return "board/edit";
	}
	
	/**
	 * @author 임예슬
	 * @date 2020.05.07
	 * @description 게시글 수정을 위한 첨부파일 리스트
	 * @reference
	 */
	@GetMapping(value="/getAttachedList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<Map<String, Object>>> getBoardFileList(int boardNo) throws Exception {		
		return new ResponseEntity<>(boardService.getBoardFileList(boardNo), HttpStatus.OK);
	}
	
	/**
	 * @author 임예슬
	 * @date 2020.05.08
	 * @description 게시글 수정
	 * @reference
	 */
	@RequestMapping(value="/edit.do", method = RequestMethod.POST)
	public String editBoard(int boardNo, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("edit board");
		
		return "";
	}
	
	/**
	 * @author 임예슬
	 * @date 2020.05.08
	 * @description 게시글 삭제
	 * @reference
	 */
	@RequestMapping(value="/delete.do")
	public String deleteBoard(int boardNo) throws Exception {
		logger.info("delete board");
		return "redirect:/board.do";
	}
}
