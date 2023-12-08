package MSACHAT.backend.controller;

import MSACHAT.backend.service.AuthService;
import MSACHAT.service.NotifService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notif")
public class NotifyController {
    private AuthService authService;
    private NotifService notifService;

    NotifyController(
            AuthService authService,
            NotifService notifService
    ) {
        this.authService=authService;
        this.notifService=notifService;
    }
    @GetMapping("/get")
    public List getNotifs(@RequestBody Object tokenInfo){
        return notifService.getNotifs(authService.getUserIdFromToken(tokenInfo.token,tokenInfo.secret));
    }
}
