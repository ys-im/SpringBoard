package com.giens.springboard.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
//private static final String filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치
	@Value("#{property[file_root]}")
	private String filePath;	
	
	public List<Map<String, Object>> parseInsertFileInfo(int boardNo, 
			MultipartHttpServletRequest mpRequest) throws Exception{
		String root_path = filePath+"\\";//mpRequest.getSession().getServletContext().getRealPath("/resources/file/");		
		//String root_path = mpRequest.getContextPath()+"/resources/file/";
		Calendar calendar = new GregorianCalendar(Locale.KOREA);		
		String year = Integer.toString(calendar.get(Calendar.YEAR));
		String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		//String attach_path = year+"\\"+month+"\\";
		String attach_path = year+"\\"+month+"\\";
		String filePath = root_path + attach_path;	
		filePath = filePath.replaceAll(Matcher.quoteReplacement(File.separator), "/");
		
		/*;
			Iterator은 데이터들의 집합체? 에서 컬렉션으로부터 정보를 얻어올 수 있는 인터페이스입니다.
			List나 배열은 순차적으로 데이터의 접근이 가능하지만, Map등의 클래스들은 순차적으로 접근할 수가 없습니다.
			Iterator을 이용하여 Map에 있는 데이터들을 while문을 이용하여 순차적으로 접근합니다.
		*/
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String originalFileNameNoExt = null;
		String storedFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null;
	
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date now = new Date();
			String strDate = sdf.format(now);
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				originalFileNameNoExt = originalFileName.substring(0, originalFileName.lastIndexOf("."));
				storedFileName = originalFileNameNoExt + "_" + strDate + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("BOARD_NO", boardNo);
				listMap.put("FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				listMap.put("FILE_PATH", filePath);
				list.add(listMap);
			}
		}
		return list;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
