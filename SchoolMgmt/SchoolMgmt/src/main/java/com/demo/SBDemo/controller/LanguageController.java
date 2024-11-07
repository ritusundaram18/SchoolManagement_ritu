package com.demo.SBDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.SBDemo.utils.AppConstants;

@Controller
@RequestMapping(path = AppConstants.APP_ROOT_API)
public class LanguageController {

	@RequestMapping(AppConstants.LOCALE_ROOT_API)
	public String getLanguage() {
		return "locale";
	}

}