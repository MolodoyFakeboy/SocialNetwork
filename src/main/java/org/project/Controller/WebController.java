package org.project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping( value = "/")
    public String firstView() {
        return "index";
    }

    @RequestMapping("rooms")
    public String secondView() {
        return "rooms";
    }

    @RequestMapping("about-us")
    public String thirdView() {
        return "about-us";
    }

    @RequestMapping("blog")
    public String fourthView() {
        return "blog";
    }

    @RequestMapping("blog-details")
    public String blogdetailsView() {
        return "blog-details";
    }

    @RequestMapping("contact")
    public String contactView() {
        return "contact";
    }

    @RequestMapping("room-details")
    public String roomsdetailsView() {
        return "room-details";
    }

    @RequestMapping("admin-panel")
    public String AdminView() {
        return "admin-panel";
    }

    @RequestMapping("admin-service-panel")
    public String adminServiceView() {
        return "admin-service-panel";
    }

    @RequestMapping("help")
    public String helpView(){
        return "Help";
    }

    @RequestMapping("admin-room-panel")
    public String adminRoomView(){
        return "admin-room-panel";
    }

    @RequestMapping("admin-guest-panel")
    public String adminGuestView(){
        return "admin-room-panel";
    }
}
