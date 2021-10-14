package kr.personal.blog.api.domain.posts;

import kr.personal.blog.api.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Posts extends BaseTimeEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) //데이타베이스의 시퀀스를 사용하여 기본키 생성 전략, 오라클/PostgreSQL/DB2/H2 디비에서 사용가능
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 DB에 위임 전략, MySQL/PostgreSQL/SQL Server/DB2 디비에서 사용가능
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
