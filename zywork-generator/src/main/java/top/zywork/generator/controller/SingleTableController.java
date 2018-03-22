package top.zywork.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/single-table")
public class SingleTableController {

    @GetMapping("page")
    public String singleTable() {
        return "single_table";
    }

}
