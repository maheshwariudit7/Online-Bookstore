package com.OnlineBookstore.OnlineBookStore.controller;

import com.OnlineBookstore.OnlineBookStore.Dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class HomeController {
    @Autowired
    private UserController userController;

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return "login";
        } else {
            return "redirect:/home";
        }

    }

    @GetMapping("/signup")
    public String loginOk() {

        return "signup";
    }


// to obtain filenames present in static folder as we need to send the image file names through the controller to
// home template to enable carousal as thymeleaf does not support carousal

    private static List<String> getFileNamesFromStaticFolder() throws IOException, URISyntaxException {

        // Use the ClassLoader to get the resources within the /static folder

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("static");

        if (resource != null) {
            try (Stream<Path> walk = Files.walk(Paths.get(resource.toURI()))) {
                // Filter out directories and get only filenames
                return walk.filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toList());
            }
        } else {
            throw new RuntimeException("Failed to locate /static folder.");
        }
    }

    @GetMapping("/home")
    public String getHome(HttpServletRequest request, Model model, @AuthenticationPrincipal UserDetails userDetails) throws IOException, URISyntaxException {

        List<String> imageUrls = getFileNamesFromStaticFolder();

        for (String img : imageUrls) {
            System.out.println(img);
        }

        model.addAttribute("request", request);

        UserDto user = userController.getUserByUsername(userDetails.getUsername()).get();
        model.addAttribute("userId", user.getUserid());
        model.addAttribute("imageUrls", imageUrls);
        return "home";
    }

    @GetMapping("/books")
    public String getBooks() {
        return "books";
    }


}
