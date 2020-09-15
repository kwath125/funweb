package com.example;

import org.springframework.context.annotation.Primary;

public class JapaneseChef implements Chef {

@Primary
@Override
public void doCook() {
	System.out.println("일본음식을 요리합니다.");
}

}
