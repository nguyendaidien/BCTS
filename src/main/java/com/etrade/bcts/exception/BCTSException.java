package com.etrade.bcts.exception;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@EnableWebMvc
@ControllerAdvice
public class BCTSException{
//	@ExceptionHandler(MultipartException.class)
//	public String handleException(MultipartException e, RedirectAttributes redirectAttributes) {
//		 System.out.println("-----------------MultipartException");
//		 redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
//	     return "redirect:/uploadStatus";
//
//	}
	@ExceptionHandler(value = MultipartException.class)
    public String handleMultipartException(Exception ex) {
		String message = null;
        if (ex instanceof MultipartException) {
            final MultipartException mEx = (MultipartException) ex;
            System.out.println("-----------------MultipartException");
            if (ex.getCause() instanceof FileUploadBase.FileSizeLimitExceededException) {
                final FileUploadBase.FileSizeLimitExceededException flEx = (FileUploadBase.FileSizeLimitExceededException) mEx.getCause();
                System.out.println("-----------------MultipartException1");
            } else if (ex.getCause() instanceof FileUploadBase.SizeLimitExceededException) {
                final FileUploadBase.SizeLimitExceededException flEx = (FileUploadBase.SizeLimitExceededException) mEx.getCause();
                
                System.out.println("-----------------MultipartException2");
            } else {
            	message = ex.getMessage();
                System.out.println("-----------------MultipartException3");
            }
        } else {
            System.out.println("-----------------MultipartException4");
        }
//        ModelMap model = new ModelMap();
//        model.addAttribute("message", message);
        return "redirect:/docs/add-document-err-code001";

    }
	
	@ExceptionHandler(FileNotFoundException.class)
	public String handleException(FileNotFoundException e) {
		 System.out.println("-----------------FileNotFoundException");
//		 redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
		 return "redirect:/add-document-err-code002";

	}
//	@ExceptionHandler(MaxUploadSizeExceededException.class)
//	public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc, 
//		      HttpServletRequest request,
//		      HttpServletResponse response) {
//		System.out.println("-----------------MaxUploadSizeExceededException");
//		        ModelAndView modelAndView = new ModelAndView("uploadStatus");
//		        modelAndView.getModel().put("message", "File too large!");
//		        return modelAndView;
//	}
//	@ExceptionHandler(MaxUploadSizeExceededException.class)
//	public String handleException(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes) {
//		
//		 redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
//	     return "redirect:/uploadStatus";
//
//	}
//	@ExceptionHandler(SizeLimitExceededException.class)
//	public String handleException(SizeLimitExceededException e, RedirectAttributes redirectAttributes) {
//		System.out.println("-----------------SizeLimitExceededException");
//		 redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
//	     return "redirect:/uploadStatus";
//
//	}
}
