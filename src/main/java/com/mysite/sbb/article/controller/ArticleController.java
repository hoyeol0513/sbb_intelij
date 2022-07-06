package com.mysite.sbb.article.controller;

import com.mysite.sbb.article.dao.ArticleRepository;
import com.mysite.sbb.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/list")
    @ResponseBody
    // 리스트 타입은 리스트 전체
    public List<Article> list(){
        return articleRepository.findAll();
    }
    @RequestMapping("/detail")
    @ResponseBody
    // optional 타입은 리스트 안에서 하나만
    // localhost에서 아이디를 물어볼거면 " ?변수명=변수값 "
    public Article showArticleDetail(@RequestParam long id){
        Optional<Article> article = articleRepository.findById(id);
        return article.orElse(null);
    }
    @RequestMapping("/doModify")
    @ResponseBody
    public Article showModify(long id, String title, String body){
        //get -> 조건에 맞는 데이터 가져오기
        Article article = articleRepository.findById(id).get();
        if(title != null){
            article.setTitle(title); //불러온 데이터 수정
        }
        if(body != null){
            article.setBody(body); //불러온 데이터 수정
        }
        // 수정된 정보를 db로 저장
        articleRepository.save(article);
        return article;
    }
    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(long id){
        articleRepository.deleteById(id); //id로 찾아서 삭제
        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }

}
