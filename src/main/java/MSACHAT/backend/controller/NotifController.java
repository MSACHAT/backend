package MSACHAT.backend.controller;

import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.NewNotifDto;
import MSACHAT.backend.dto.PageNumDto;
import MSACHAT.backend.entity.NotifEntity;
import MSACHAT.backend.entity.NotifTagEntity;
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
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        if (pageNum == null || pageSize == null) {//RequestBody Info Insufficient 10001 Error
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        String token=authService.getTokenFromHeader(bearerToken);
        Integer userId= authService.getUserIdFromToken(token);
        System.out.println("UID:   "+userId);
        List<NotifEntity> notifs = notifService.getNotifsByPageNum(userId, pageNum, pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("notifs", notifs);
        returnResult.put("totalPages", notifService.countTotalPagesByPageSize(pageSize));
        NotifTagEntity notifTag= notifService.findNotifTagByUserId(userId);
        if(!notifs.isEmpty()) {
            if (notifTag == null) {
                notifService.newNotifTag(notifs.get(0).getTimeStamp(), userId);
            } else {
                System.out.println("assihasiudhas" + notifs.get(0).getTimeStamp());
                notifService.setNotifTag(notifs.get(0).getTimeStamp(), userId);
            }
        }
        returnResult.put("totalNotifs",notifService.countNotifNums());
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
        Integer userId=1;
        List<NotifEntity> notifs = notifService.getNotifsByPageNum(userId, pageNum, pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("notifs", notifs);
        returnResult.put("totalPages", notifService.countTotalPagesByPageSize(pageSize));
        NotifTagEntity notifTag= notifService.findNotifTagByUserId(userId);
        if(!notifs.isEmpty()) {
            if (notifTag == null) {
                notifService.newNotifTag(notifs.get(0).getTimeStamp(), userId);
            } else {
                System.out.println("assihasiudhas" + notifs.get(0).getTimeStamp());
                notifService.setNotifTag(notifs.get(0).getTimeStamp(), userId);
            }
        }
        returnResult.put("totalNotifs",notifService.countNotifNums());
        return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }

    @GetMapping("/countnewnotifs")
    public NewNotifDto countNewNotifs(@RequestHeader("Authorization") String bearerToken){
        String token=authService.getTokenFromHeader(bearerToken);
        Integer userId= authService.getUserIdFromToken(token);
        NotifTagEntity notifTag=notifService.findNotifTagByUserId(userId);
        if(notifTag==null){
            NewNotifDto newNotifDto=new NewNotifDto();
            newNotifDto.setNewNotifCounts(0);
            newNotifDto.setNotifTag(null);
            return newNotifDto;
        }
        else {
            NewNotifDto newNotifDto = new NewNotifDto();
            newNotifDto.setNewNotifCounts(notifService.countNewNotifs(userId, notifTag.getTimeStamp()));
            newNotifDto.setNotifTag(notifTag.getTimeStamp());
            return newNotifDto;
        }
    }

    @GetMapping("/countnewnotifs/test")
    public NewNotifDto countNewNotifs(@RequestParam Integer userId){
        NotifTagEntity notifTag=notifService.findNotifTagByUserId(userId);
        NewNotifDto newNotifDto=new NewNotifDto();
        newNotifDto.setNewNotifCounts(notifService.countNewNotifs(userId,notifTag.getTimeStamp()));
        newNotifDto.setNotifTag(notifTag.getTimeStamp());
        return newNotifDto;
    }

    @GetMapping("/test")
    public String testConnection(@RequestHeader("Authorization")String bearerToken) {
        String token=authService.getTokenFromHeader(bearerToken);
        Integer userId=authService.getUserIdFromToken(token);
        return "Connected!"+userId.toString();
    }
}
