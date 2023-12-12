package MSACHAT.backend.controller;

import MSACHAT.backend.dto.PageNumDto;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.NotifService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notif")
public class NotifController {
    private AuthService authService;
    private NotifService notifService;

    public NotifController(
            AuthService authService,
            NotifService notifService
    ) {
        this.authService = authService;
        this.notifService = notifService;
    }

    @GetMapping("/getbypagenum")
    public List getNotifs(
            @RequestHeader String token,
            @RequestBody PageNumDto pageNumDto
    ) {
        Integer pageNum = pageNumDto.getPageNum();
        return notifService.getNotifsByPageNum(authService.getUserIdFromToken(token), pageNum);
    }

    @GetMapping("/test")
    public String testConnection() {
        return "Connected!";
    }
}
