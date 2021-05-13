package com.guli.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
public class TeacherAdminController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public R list() {

        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items", list).message("查询讲师列表成功");
    }

//    @DeleteMapping("{id}")
//    public boolean removeById(@PathVariable  String id) {
//        return teacherService.removeById(id);
//    }

    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "teacher id", required = true)
            @PathVariable String id) {
        teacherService.removeById(id);
        return R.ok();
    }

//    @ApiOperation(value="分页讲师列表")
//    @GetMapping("{page}/{limit}")
//    public R pageList(
//            @ApiParam(name = "page", value = "当前页码", required = true)
//            @PathVariable Long page,
//            @ApiParam(name = "limit", value = "每页记录数", required = true)
//            @PathVariable Long limit
//    ){
//        Page<Teacher> pageParam = new Page<>(page,limit);
//        teacherService.page(pageParam, null);
//        List<Teacher> records = pageParam.getRecords();
//        long total = pageParam.getTotal();
//        return R.ok().data("total", total).data("rows", records);
//    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象")
                    TeacherQuery teacherQuery) {

        Page<Teacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher) {
        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }

}
