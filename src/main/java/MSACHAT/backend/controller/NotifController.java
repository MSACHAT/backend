package MSACHAT.backend.controller;

import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.PageNumDto;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.NotifService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/getbypagenumandpagesize")
    public ResponseEntity<Object> getNotifs(
            @RequestHeader String token,
            @RequestBody PageNumDto pageNumDto
    ) {
        if (pageNumDto.getPageNum()==null||pageNumDto.getPageSize()==null) {//RequestBody Info Insufficient 10001 Error
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        List notifs = notifService.getNotifsByPageNum(authService.getUserIdFromToken(token), pageNumDto.getPageNum(),pageNumDto.getPageSize());
        return new ResponseEntity<>(notifs,HttpStatus.OK);
    }

    @GetMapping("/test")
    public String testConnection() {
        return "Connected!";
    }
}
