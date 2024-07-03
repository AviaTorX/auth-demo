package org.mine.authdemo.about;

import org.mine.authdemo.users.dtos.UserResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/about")
public class AboutController {

    @GetMapping("")
    public String about() {
        return "It is about page";
    }

    @GetMapping("/private")
    public String privatePage(@AuthenticationPrincipal UserResponseDto user) {
        return "It is private page" + user.getUsername();
    }
}
