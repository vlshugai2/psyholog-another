package vladyslav.shuhai.psyhology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vladyslav.shuhai.psyhology.dto.request.NewsletterRequest;
import vladyslav.shuhai.psyhology.dto.request.UserRequest;
import vladyslav.shuhai.psyhology.service.NewsletterService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsletterController {
    @Autowired
    private NewsletterService newsletterService;

    @PostMapping("/subscribe")
    public final void create(@Valid @RequestBody final NewsletterRequest request) throws Exception {
        newsletterService.create(request);
    }

}
