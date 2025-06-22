package com.SpringExaminationSystem.model.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTemplate {
    private String chapterNo;
    private String difficulty;
    private int amount;
}