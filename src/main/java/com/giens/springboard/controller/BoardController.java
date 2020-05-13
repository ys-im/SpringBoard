package com.giens.springboard.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
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
	public String addBoard(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("add board");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", boardVO.getTitle());
		params.put("userID", boardVO.getUserID());
		params.put("contents", boardVO.getContents());
		params.put("pBoardNo", boardVO.getpBoardNo());
		
		//게시판 글 등록
		boardService.addBoard(params);
		int boardNo = (int)params.get("boardNo");
		
		int pBoardNo = Integer.parseInt(boardVO.getpBoardNo());
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
	public String editBoard(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("edit board");	
		

		int boardNo = Integer.parseInt(boardVO.getBoardNo());
		//변경된 기존 첨부파일 리스트
		List<String> modifiedFileNoList = Arrays.asList(mpRequest.getParameter("attached").split(","));
		
		//기존에 첨부되있던 파일 리스트
		List<Map<String, Object>> savedfileList = boardService.getBoardFileList(boardNo);
		int fileCount = savedfileList.size();	
		
		//삭제할 첨부파일 번호 리스트 선언
		List<String> deleteFileNoList = new ArrayList<String>();
		for(int i = 0; i < fileCount; i++) {
			//기존 첨부파일 번호 넣어줌
			deleteFileNoList.add(savedfileList.get(i).get("FILE_NO").toString());
		}
		
		for(int i = 0; i < modifiedFileNoList.size(); i++) {
			if(deleteFileNoList.contains(modifiedFileNoList.get(i))) {
				//기존 첨부파일 번호 리스트에 변경된 첨부파일 리스트의 파일 번호가 존재한다면 삭제하지 말아야 하므로 삭제할 첨부파일 리스트에서 제거
				deleteFileNoList.remove(deleteFileNoList.indexOf(modifiedFileNoList.get(i)));
			}
		}
		//System.out.println("편집된 파일 번호 : "+modifiedFileNoList);
		//System.out.println("삭제할 파일 번호 : "+deleteFileNoList);
		
		//게시글 첨부파일 삭제
		boardService.deleteBoardFile(boardNo, deleteFileNoList);

		//게시글 새로운 첨부파일 업로드
		boardService.addBoardFile(boardNo, mpRequest);
		//게시글 수정
		boardService.editBoard(boardVO);
		return "redirect:/board.do";
	}
	
	/**
	 * @author 임예슬
	 * @date 2020.05.08
	 * @description 게시글 삭제
	 * @reference
	 */
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(int boardNo) throws Exception {
		logger.info("delete board");
		
		//게시글 삭제
		boardService.deleteBoard(boardNo);
		
		//게시글에 첨부된 첨부파일 삭제
		List<Map<String, Object>> fileList = boardService.getBoardFileList(boardNo);
		List<String> deleteFileNoList = new ArrayList<String>();
		for(int i = 0; i < fileList.size(); i++) {
			//기존 첨부파일 번호 넣어줌
			deleteFileNoList.add(fileList.get(i).get("FILE_NO").toString());
		}
		boardService.deleteBoardFile(boardNo, deleteFileNoList);
		return "redirect:/board.do";
	}
}
