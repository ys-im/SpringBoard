/**
 * custom-common.js
 * 임예슬
 */
(function($){
	"use strict";
	
	$(document).ready(function(){		
		window.onunload = function(event){
			$.ajax({
				url : "/logout.do",
				success : function(){
					this.close();
				}
			});	
		}
		
	});	

//https://devsh.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%EB%A1%9C-%EA%B5%AC%ED%98%84%ED%95%9C-%EC%8B%A4%EC%8B%9C%EA%B0%84-%ED%83%80%EC%9E%84%EC%95%84%EC%9B%83-%EA%B8%B0%EB%8A%A5
})(jQuery);