package kr.ac.mjc.blog01;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Article saveArticle(Article article,User user){
        User dbUser=userRepository.findOneByEmail(user.getEmail());
        article.setWriter(dbUser);  //글의 작성자를 로그인된 사용자로 지정

        List<Integer> categoryIds=article.getCategoryIds();
        List<Category> categoryList=new ArrayList<Category>();
        for(int cateogryId : categoryIds){
            Category category=categoryRepository.findById(cateogryId).get();
            categoryList.add(category);
        }
        article.setCategoryList(categoryList);

        return articleRepository.save(article);
    }

    public void deleteArticle(int no){
        articleRepository.deleteById(no);
    }

    public Article getArticleItem(int no){
        return articleRepository.findById(no);
    }

   public List<Article> getArticleList(){
        return articleRepository.findAllByOrderByWriteDateDesc();
   }

   @Transactional
   public Article modifyArticle(int no, Article article){
        Article targetArticle=articleRepository.findById(no);
        targetArticle.setTitle(article.getTitle());
        targetArticle.setBody(article.getBody());
        return targetArticle;
   }



}
