//package com.guli.common.handler;
//
////public class GlobalExceptionHandler {
////}
//
//import com.guli.common.vo.R;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jdbc.BadSqlGrammarException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@ControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler
//    @ResponseBody
//    public R error(Exception e) {
//        e.printStackTrace();
//        return R.error();
//    }
//
//    @ExceptionHandler(BadSqlGrammarException.class)
//    @ResponseBody
//    public R error(BadSqlGrammarException e) {
//        log.error(e.getMessage());
//        e.printStackTrace();
//        return R.error().code(200003).message("sql syctax error");
//    }
//}

package com.guli.common.handler;


import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.util.ExceptionUtil;
import com.guli.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
//        log.error(e.getMessage());
        //e.printStackTrace();
        return R.error();
    }


    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e) {
//        log.error(e.getMessage());
//        e.printStackTrace();
//        return R.error().code(200003).message("sql syctax error");
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public R error(DataIntegrityViolationException e) {
//        log.error(e.getMessage());
//        e.printStackTrace();
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }


    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        log.error(e.getMessage());
//        e.printStackTrace();
//        log.error(ExceptionUtil.getMessage(e));
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}