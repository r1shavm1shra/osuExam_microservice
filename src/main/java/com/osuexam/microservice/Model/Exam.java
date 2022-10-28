package com.osuexam.microservice.Model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private ExamPaper examPaperJSON;

    @OneToMany
    @JoinColumn(name = "answer_key_id_fk")
    List<AnswerKey> answerKey;

    @OneToOne
    @JoinColumn(name = "rooms_key_id_fk")
    Rooms rooms;

    @JsonBackReference(value="course")
    @ManyToOne
    @JoinColumn(name="course_id_fk")
    Course course;
}


@Getter
@Setter
class ExamPaper
{
    public String title;
    public List<String> instructions;
    public List<Question> questionList;
}

@Getter
@Setter
class Question
{
    public String questionId;
    public String type;
    public List<String> options;
}
