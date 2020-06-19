package com.giens.springboard.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giens.springboard.service.Board.BoardService;
import com.giens.springboard.util.FileUtils;
import com.giens.springboard.vo.BoardVO;
import com.giens.springboard.vo.SearchCriteria;
import com.giens.springboard.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
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
	public String getListBoard(HttpSession session) throws Exception {
		logger.info("board view");	
		
		UserVO userVO = (UserVO) session.getAttribute("user");
		if(userVO == null) {
			return "redirect:/loginView.do";
		}else {
			return "board/board";
		}
	}
	
	/**
	 * @author 임예슬
	 * @date 2020.05.06
	 * @description 글쓰기 화면
	 * @reference 
	 */
	@RequestMapping(value = "/writeView.do", method = RequestMethod.GET)
	public String writeView(int pBoardNo, String title, Model model, HttpSession session) throws Exception {		
		
		UserVO userVO = (UserVO) session.getAttribute("user");
		if(userVO == null) {
			return"redirect:/loginView.do";
		}else {
			int originNo = 0;
			int groupSeq = 0;
			int groupLayer = 0;
			int replyCnt = 0;
			if(pBoardNo > 0) {
				BoardVO vo = boardService.getBoard(pBoardNo).get(0);
				originNo = Integer.parseInt(vo.getOriginNo());
				groupSeq = Integer.parseInt(vo.getGroupSeq());
				groupLayer = Integer.parseInt(vo.getGroupLayer())+1;
				replyCnt = Integer.parseInt(vo.getReplyCnt());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			if(title != "") {
				title = "  Re : "+title;
			}
			map.put("title", title);
			map.put("originNo", originNo);
			map.put("groupSeq", groupSeq);
			map.put("groupLayer", groupLayer);
			map.put("pBoardNo", pBoardNo);
			map.put("replyCnt", replyCnt);		
			
			model.addAttribute("writeInfo", map);
			
			return "board/write";
		}
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
		params.put("boardVO", boardVO);		
		
		//게시판 글 등록
		
		boardService.addBoard(params);
		int boardNo = (int)params.get("boardNo");
		
		int pBoardNo = Integer.parseInt(boardVO.getpBoardNo());
		if(pBoardNo == 0) {
			boardService.updateBoardNew();
		}else {			
			int replyCount = Integer.parseInt(boardVO.getReplyCnt());
			Map<String, Object> updateReplyParams = new HashMap<String, Object>();
			updateReplyParams.put("boardNo", boardNo);
			updateReplyParams.put("pBoardNo", pBoardNo);
			updateReplyParams.put("replyCount",replyCount);
			boardService.updateBoardReply(updateReplyParams);
		}
				
		//게시판 글에 첨부파일 등록
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(boardNo, mpRequest);
		if(list.size() > 0) {
			boardService.addBoardFile(list);
		}
		
		return "redirect:/board.do";
	}
	
	/** 
	 * @author 임예슬
	 * @date 2020.05.06
	 * @description 게시글 조회, 첨부파일 조회
	 * @reference AjaxController.java -> ajaxBoardDetail(int boardNo)
	 */
	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public String getBoard(int boardNo, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria, 
							Model model, HttpSession session) throws Exception {
		logger.info("get board detail");
		UserVO userVO = (UserVO) session.getAttribute("user");
		if(userVO == null) {
			return "redirect:/loginView.do";
		}else {
			boardService.updateBoardHit(boardNo);
			List<Map<String, Object>> fileList = boardService.getBoardFileList(boardNo);
			model.addAttribute("fileList", fileList);
			model.addAttribute("searchCriteria", searchCriteria);
			
			return "board/boardDetail";
		}
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
	public String editView(int boardNo, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria, 
							Model model, HttpSession session) throws Exception {
		logger.info("edit view");
		UserVO userVO = (UserVO) session.getAttribute("user");
		if(userVO == null) {
			return "redirect:/loginView.do";
		}else {
			List<BoardVO> boardVO = boardService.getBoard(boardNo);
			model.addAttribute("edit", boardVO.get(0));	
			
			List<Map<String, Object>> fileList = boardService.getBoardFileList(boardNo);
			model.addAttribute("fileList", fileList);
			model.addAttribute("searchCriteria", searchCriteria);
			
			return "board/edit";
		}
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
	public String editBoard(BoardVO boardVO, MultipartHttpServletRequest mpRequest, 
			 @ModelAttribute("searchCriteria") SearchCriteria searchCriteria, RedirectAttributes rttr) throws Exception {
		
		logger.info("edit board");			

		int boardNo = Integer.parseInt(boardVO.getBoardNo());
		//변경된 기존 첨부파일 리스트
		List<String> modifiedFileNoList = Arrays.asList(mpRequest.getParameter("attached").split(","));
		
		if(modifiedFileNoList.contains("")) {
			//기존에 첨부되있던 파일 리스트
			List<Map<String, Object>> savedfileList = boardService.getBoardFileList(boardNo);
			int fileCount = savedfileList.size();	
			
			//삭제할 첨부파일 번호 리스트 선언
			List<Map<String, String>> deleteFileList = new ArrayList<Map<String, String>>();
			for(int i = 0; i < fileCount; i++) {
				String fileNo = savedfileList.get(i).get("FILE_NO").toString();
				String filePathInfo = savedfileList.get(i).get("FILE_PATH").toString() 
										+ savedfileList.get(i).get("STORED_FILE_NAME").toString();
				Map<String, String> map = new HashMap<String, String>();
				map.put("fileNo", fileNo);
				map.put("filePathInfo", filePathInfo);
				//기존 첨부파일 번호 넣어줌
				deleteFileList.add(map);
			}
					
			List<String> deleteFileNoList = new ArrayList<String>();
			for(int i = 0; i < deleteFileList.size(); i++) {
				deleteFileNoList.add(deleteFileList.get(i).get("fileNo"));
			}
			//System.out.println(deleteFileList);
			//System.out.println(deleteFileNoList);
			
			for(int i = 0; i < modifiedFileNoList.size(); i++) {
				if(deleteFileNoList.contains(modifiedFileNoList.get(i))) {
					//기존 첨부파일 번호 리스트에 변경된 첨부파일 리스트의 파일 번호가 존재한다면 삭제하지 말아야 하므로 삭제할 첨부파일 리스트에서 제거
					int idx = deleteFileNoList.indexOf(modifiedFileNoList.get(i));
					deleteFileNoList.remove(idx);
					deleteFileList.remove(idx);
				}
			}
			
			//System.out.println("편집된 파일 번호 : "+modifiedFileNoList);
			//System.out.println("삭제할 파일번호 리스트 : "+deleteFileNoList);
			//System.out.println("삭제할 파일 리스트 : "+deleteFileList);
			
			//게시글 첨부파일 삭제
			boardService.deleteBoardFile(deleteFileList);
		}

		//게시글 새로운 첨부파일 업로드
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(boardNo, mpRequest);
		if(list.size()>0) {
			boardService.addBoardFile(list);
		}
		//게시글 수정
		boardService.editBoard(boardVO);
		
		rttr.addAttribute("page", searchCriteria.getPage());
		rttr.addAttribute("perPageNum", searchCriteria.getPerPageNum());
		rttr.addAttribute("searchType", searchCriteria.getSearchType());
		rttr.addAttribute("keyword", searchCriteria.getKeyword());
		
		return "redirect:/boardDetail.do?boardNo="+boardNo;
	}
	
	/**
	 * @author 임예슬
	 * @date 2020.05.08
	 * @description 게시글 삭제
	 * @reference
	 */
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(int boardNo, 
			 @ModelAttribute("searchCriteria") SearchCriteria searchCriteria, 
			 Model model, HttpSession session) throws Exception {
		
		logger.info("delete board");
		UserVO userVO = (UserVO) session.getAttribute("user");
		if(userVO == null) {
			return "redirect:/loginView.do";
		}else {
			//게시글 삭제
			boardService.deleteBoard(boardNo);
	
			List<Map<String, Object>> fileList = boardService.getBoardFileList(boardNo);
			
			//게시글에 첨부된 첨부파일 삭제 DB
			List<Map<String, String>> deleteFileList = new ArrayList<Map<String, String>>();
			for(int i = 0; i < fileList.size(); i++) {
				String fileNo = fileList.get(i).get("FILE_NO").toString();
				String filePathInfo = fileList.get(i).get("FILE_PATH").toString() 
										+ fileList.get(i).get("STORED_FILE_NAME").toString();
				Map<String, String> map = new HashMap<String, String>();
				map.put("fileNo", fileNo);
				map.put("filePathInfo", filePathInfo);
				//기존 첨부파일 번호 넣어줌
				deleteFileList.add(map);
			}
			boardService.deleteBoardFile(deleteFileList);
			
			//게시글에 첨부된 첨부파일 삭제 Directory
			int size = deleteFileList.size();
			for(int i = 0; i < size; i++) {
				String fileInfo = deleteFileList.get(i).get("filePathInfo");
				File file = new File(fileInfo); //파일 실물 디렉토리
				if(file.exists()) {
					//파일 경로 + 저장된 파일명 까지 가져왔으므로 file.isDirectory() 생략
					file.delete();
				}
			}	
			
			model.addAttribute("page", searchCriteria.getPage());
			model.addAttribute("perPageNum", searchCriteria.getPerPageNum());
			model.addAttribute("searchType", searchCriteria.getSearchType());
			model.addAttribute("keyword", searchCriteria.getKeyword());
			
			return "redirect:/board.do";
		}
	}
}
