package MSACHAT.backend.controller;

import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.NotifDto;
import MSACHAT.backend.dto.PageNumDto;
import MSACHAT.backend.entity.NotifEntity;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.NotifService;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        if (pageNum == null || pageSize == null) {//RequestBody Info Insufficient 10001 Error
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        List<NotifEntity> notifs = notifService.getNotifsByPageNum(authService.getUserIdFromToken(token), pageNum, pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("notifs", notifs);
        returnResult.put("totalPages", notifService.countTotalPagesByPageSize(pageSize));
        return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }

    @GetMapping("/getbypagenumandpagesize/test")
    public ResponseEntity<Object> getNotifsTest(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        if (pageNum == null || pageSize == null) {//RequestBody Info Insufficient 10001 Error
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        List<NotifEntity> notifs = notifService.getNotifsByPageNum(1, pageNum, pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("notifs", notifs);
        returnResult.put("totalPages", notifService.countTotalPagesByPageSize(pageSize));
        returnResult.put("totalNotifs",notifService.countNotifNums());
        return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }

    @PostMapping("/isread")
    public ResponseEntity<Object> isRead(
            @RequestBody Integer notifId
            ){
        if(notifId==null){
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        NotifEntity notifEntity=notifService.getNotifById(notifId);
        notifService.isRead(notifEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public String testConnection() {
        return "Connected!";
    }
}
