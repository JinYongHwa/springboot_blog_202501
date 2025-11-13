package kr.ac.mjc.blog01;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no")
    private int no;         //글번호

    @Column(name="title",nullable = false)
    private String title;   //글제목

    @Column(name="body")
    private String body;    //글 내용

    @CreatedDate
    @Column(name="writeDate")
    private LocalDateTime writeDate;    //작성일

    @ManyToOne
    private User writer;           //글 작성자

    @ManyToMany
    @JoinTable(
            name="article_category",
            joinColumns = @JoinColumn(name="article_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Category> categoryList;

    private List<Integer> categoryIds;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getWriteDate() {
        return writeDate;
    }
    public String getFormattedWriteDate(){
        if(writeDate==null){
            return "";
        }
        return writeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setWriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }


    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
