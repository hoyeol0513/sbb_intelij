package com.mysite.sbb.article.controller;

import com.mysite.sbb.article.dao.ArticleRepository;
import com.mysite.sbb.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    //crud (create(추가), read(조회), update(수정), delete(삭제)) : 기본적인 게시물 프로젝트의 기능
    @Autowired
    private ArticleRepository articleRepository;

    //article 전체 조회(read)
    @RequestMapping("/list")
    @ResponseBody
    // 리스트 타입은 리스트 전체
    public List<Article> list(){
        return articleRepository.findAll();
    }

    //article 단건 조회()
    @RequestMapping("/list/")
    @ResponseBody
    public List<Article> showArticleBy(String title, String body){
        if(title != null && body != null){

        }
        else if(title != null && body == null){

        }
        else if(title == null && body != null){

        }
    }
    //article 수정 (update)
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
        article.setUpdateDate(LocalDateTime.now()); //수정 날짜 갱신
        // 수정된 정보를 db로 저장
        articleRepository.save(article);
        return article;
    }

    //article 삭제(delete)
    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(long id){
        Optional<Article> article = articleRepository.findById(id);

        if(articleRepository.existsById(id) == false) {
            return "%d 번 게시물은 이미 삭제되었거나 없는 게시물입니다.".formatted(id);
        }
        articleRepository.deleteById(id); //id로 찾아서 삭제
        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }

    @RequestMapping("/findByTitle")
    @ResponseBody
    public List<Article> findArticleByTitle(String title){
        List<Article> articles = articleRepository.findByTitle(title);
        return articles;
    }
}
