package kr.ac.mjc.blog01;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public ModelAndView main(HttpSession session){
        List<Article> articleList=articleService.getArticleList();
        ModelAndView mav=new ModelAndView();
        User user= (User) session.getAttribute("loginUser");
        System.out.println(user.getEmail());
        if(user!=null){
            mav.addObject("loginUser",user);
        }
        mav.addObject("articleList",articleList);
        mav.setViewName("main");
        return mav;
    }


    @GetMapping("/view/article/write")
    public String write(){
        return "write";
    }

    @GetMapping("/view/article/{no}")
    public ModelAndView viewItem(@PathVariable(name="no") int no){
        ModelAndView mav=new ModelAndView();
        Article article=articleService.getArticleItem(no);
        mav.addObject("article",article);
        mav.setViewName("item");
        return mav;
    }

    @GetMapping("/view/article/delete/{no}")
    public String deleteArticle(@PathVariable int no){
        articleService.deleteArticle(no);
        return "redirect:/";
    }

    @PostMapping("/article/write/proc")
    public ModelAndView writeArticle(@ModelAttribute Article article){
        Article savedArticle=articleService.saveArticle(article);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("redirect:/view/article/"+savedArticle.getNo());
        return mav;
    }


    @GetMapping("/view/article/modify/{no}")
    public ModelAndView modifyView(@PathVariable int no){
        Article article=articleService.getArticleItem(no);
        ModelAndView mav=new ModelAndView();
        mav.addObject("article",article);
        mav.setViewName("modify");
        return mav;
    }

    @PostMapping("/article/modify/proc")
    public String modifyProc(@ModelAttribute Article article){
        Article changedArticle=articleService.modifyArticle(article.getNo(),article);
        return "redirect:/view/article/"+changedArticle.getNo();
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
