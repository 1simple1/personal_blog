package com.simplem.personal_blog.vo;

/**
 * ClassName: FooterNewBlog
 * Package: com.simplem.personal_blog.vo
 * Description：
 * Author: simpleM
 * Date: 2020/10/24 20:43
 */
public class IndexBlog {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "FooterNewBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
