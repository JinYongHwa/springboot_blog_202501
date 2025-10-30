package kr.ac.mjc.blog01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<Response> saveArticle(@RequestBody Article article){
        Response response=new Response();
        Article savedArticle=articleService.saveArticle(article);
        response.setSuccess(true);
        response.setArticle(article);
        response.setMessage("글 작성이 완료되었습니다");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/article/{no}")
    public ResponseEntity<Response> deleteArticle(@PathVariable(name="no") int no){
        Response response=new Response();
        articleService.deleteArticle(no);
        response.setSuccess(true);
        response.setMessage("글이 삭제되었습니다");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/article/{no}")
    public ResponseEntity<Response> getArticleItem(@PathVariable(name="no") int no){
        Response response=new Response();
        Article article=articleService.getArticleItem(no);
        response.setSuccess(true);
        response.setArticle(article);
        return ResponseEntity.ok(response);
    }
}
