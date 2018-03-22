package top.zywork.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join-table")
public class JoinTableController {

    @GetMapping("page")
    public String singleTable() {
        return "join_table";
    }

}
