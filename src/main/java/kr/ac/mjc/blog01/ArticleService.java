package kr.ac.mjc.blog01;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article saveArticle(Article article){
        return articleRepository.save(article);
    }

    public void deleteArticle(int no){
        articleRepository.deleteById(no);
    }

    public Article getArticleItem(int no){
        return articleRepository.findById(no);
    }

   public List<Article> getArticleList(){
        return articleRepository.findAll();
   }

   @Transactional
   public Article modifyArticle(int no, Article article){
        Article targetArticle=articleRepository.findById(no);
        targetArticle.setTitle(article.getTitle());
        targetArticle.setBody(article.getBody());
        return targetArticle;
   }



}
