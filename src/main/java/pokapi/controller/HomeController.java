package pokapi.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/")
@Api(value = "Home", tags = "home")
public class HomeController {

    @GetMapping(value = "/")
    public void homeRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/app/");
    }
}
