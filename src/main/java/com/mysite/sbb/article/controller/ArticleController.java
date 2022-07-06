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
        //get -> 수정
        Article article = articleRepository.findById(id).get();
        if(title != null){
            article.setTitle(title);
        }
        if(body != null){
            article.setBody(body);
        }
        // 수정된 정보를 db로 저장
        articleRepository.save(article);
        return article;
    }


}
