package br.com.artcentral.mvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.artcentral.mvc.system.routes.FilePath;

@Controller
@RequestMapping("/arts")
public class ArtWebController {
	private final FilePath fp = new FilePath();
	
	@GetMapping
	public ModelAndView renderArtListingPage() {
		ModelAndView mv = new ModelAndView(fp.artListingPageHtml);
		return mv;
	}
}
